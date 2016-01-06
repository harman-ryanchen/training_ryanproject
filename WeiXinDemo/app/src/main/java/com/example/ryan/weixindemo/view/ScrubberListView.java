package com.example.ryan.weixindemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by cl-dev5 on 2015-07-02.
 */
public class ScrubberListView extends ListView {
    private boolean mIsFastScrollEnabled = false;
    private IndexScroller mScroller = null;
    private GestureDetector mGestureDetector = null;

    private boolean disableScrubber = false;

    public ScrubberListView(Context context) {
        super(context);
    }

    public ScrubberListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrubberListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFastScrollEnabled() {
        return mIsFastScrollEnabled;
    }

    public void setIndexScrollerListener(IndexScroller.IndexScrollerListener indexScrollerListener) {
        mScroller.setIndexScrollerListener(indexScrollerListener);
    }

    /**
     * Method disables Scrubbing Behaviour
     * MUST BE CALLED BEFORE ADAPTER SET.
     * If ScrubberListView has been disabled it cannot enabled again.
     */
    public void disableScrubber() {
        if(this.getAdapter()!=null){
            throw new RuntimeException("Cannot disable now, Adapter has already been set");
        }
        disableScrubber = true;
    }

    @Override
    public void setFastScrollEnabled(boolean enabled) {
        if(disableScrubber){
            super.setFastScrollEnabled(enabled);
            return;
        }
        mIsFastScrollEnabled = enabled;
        if (mIsFastScrollEnabled) {
            if (mScroller == null) {
                mScroller = new IndexScroller(getContext(), this);
            }
        } else {
            if (mScroller != null) {
                mScroller.hide();
                mScroller = null;
            }
        }
    }

    public void hideScrubber() {
        disableScrubber = true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(disableScrubber) {return;}
        // Overlay index bar
        if (mScroller != null)
            mScroller.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(disableScrubber) {return super.onTouchEvent(ev);}
            // Intercept ListView's touch event
        if (mScroller != null && mScroller.onTouchEvent(ev))
            return true;

        if (mGestureDetector == null) {
            mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2,
                                       float velocityX, float velocityY) {
                    // If fling happens, index bar shows
                    if (mScroller != null)
                        mScroller.show();
                    return super.onFling(e1, e2, velocityX, velocityY);
                }

            });
        }
        mGestureDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(disableScrubber) {return super.onInterceptTouchEvent(ev);}
        if(mScroller.contains(ev.getX(), ev.getY()))
            return true;

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        if (mScroller != null)
            mScroller.setAdapter(adapter);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(disableScrubber) {return;}
        if (mScroller != null)
            mScroller.onSizeChanged(w, h, oldw, oldh);
    }

}
