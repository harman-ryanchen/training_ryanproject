package designPatern;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by ryan on 3/30/16.
 */
public class XiJinPing {
    private static XiJinPing xiJinPing;

    private Context mCtx;
    private TextView mTextView;

    private XiJinPing(Context context) {

        this.mCtx = context;

    }
    public static XiJinPing getInstance(Context context){
        if (xiJinPing==null){
            xiJinPing = new XiJinPing(context);
        }
        return xiJinPing;
    }

    public void setRetainedTextView(TextView tv){
        this.mTextView = tv;
        mTextView.setText(mCtx.getString(android.R.string.ok));
    }

}
