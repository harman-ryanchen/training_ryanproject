package com.example.ryan.weixindemo.fragment.tabfragment;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.bean.ImageFloderBean;
import com.example.ryan.weixindemo.common.AppConfig;
import com.example.ryan.weixindemo.common.ArgumentKeys;
import com.example.ryan.weixindemo.common.FragmentsType;
import com.example.ryan.weixindemo.util.DebugLog;
import com.example.ryan.weixindemo.util.util.ImageFetcher;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ryan on 12/30/15.
 */
public class ChatsListFragment extends BaseFragment {

    private HashMap<String, List<String>> mGruopMap = new HashMap<String, List<String>>();
    private GridView mGridView;
    private Context mContext;
    private List<ImageFloderBean> imageFloderBeens;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DebugLog.d("");
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        this.mContext = getActivity();
        mGridView = (GridView) view.findViewById(R.id.photoe_wall);
        getFloderImage();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> childList = mGruopMap.get(imageFloderBeens.get(i).getFolderName());
//                ChildPictureFragment.newInstance(childList);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ArgumentKeys.IMAGE_URILS.name(), new ArrayList<String>(childList));
                getCallback().nextPage(FragmentsType.PICTURE_FRAGMENT, bundle);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void getFloderImage() {
        DebugLog.d("");
        mGruopMap.clear();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = getActivity().getContentResolver();

                //只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

                if (mCursor == null) {
                    return null;
                }

                while (mCursor.moveToNext()) {
                    //获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    //获取该图片的父路径名
                    String parentName = new File(path).getParentFile().getName();


                    //根据父路径名将图片放入到mGruopMap中
                    if (!mGruopMap.containsKey(parentName)) {
                        List<String> chileList = new ArrayList<String>();
                        chileList.add(path);
                        mGruopMap.put(parentName, chileList);
                    } else {
                        mGruopMap.get(parentName).add(path);
                    }
                }

                //通知Handler扫描图片完成
                mCursor.close();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                imageFloderBeens = subGroupOfImage(mGruopMap);
                mGridView.setAdapter(new FloderPicAdapter(mContext, imageFloderBeens));
            }
        }.execute();


    }

    /**
     * 组装分组界面GridView的数据源，因为我们扫描手机的时候将图片信息放在HashMap中
     * 所以需要遍历HashMap将数据组装成List
     *
     * @param mGruopMap
     * @return
     */
    private List<ImageFloderBean> subGroupOfImage(HashMap<String, List<String>> mGruopMap) {
        if (mGruopMap.size() == 0) {
            return null;
        }
        List<ImageFloderBean> list = new ArrayList<ImageFloderBean>();

        Iterator<Map.Entry<String, List<String>>> it = mGruopMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<String>> entry = it.next();
            ImageFloderBean mImageBean = new ImageFloderBean();
            String key = entry.getKey();
            List<String> value = entry.getValue();

            mImageBean.setFolderName(key);
            mImageBean.setImageCounts(value.size());
            mImageBean.setTopImagePath(value.get(0));//获取该组的第一张图片

            list.add(mImageBean);
        }

        return list;
    }

    public class FloderPicAdapter extends BaseAdapter {
        private Context mContext;
        private List<ImageFloderBean> imageFloderBeans;
        private Point mPoint = new Point(0, 0);//用来封装ImageView的宽和高的对象

        public FloderPicAdapter(Context context, List<ImageFloderBean> imageFloderBeans) {
            this.imageFloderBeans = imageFloderBeans;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return imageFloderBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return imageFloderBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.imagefloder_item, null);
                viewHolder.imageCount = (TextView) convertView.findViewById(R.id.group_count);
                viewHolder.title = (TextView) convertView.findViewById(R.id.group_title);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.group_image);
                mPoint.set(100, 100);//设置图片宽高做图片缩放参考
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                viewHolder.icon.setImageResource(R.drawable.friends_sends_pictures_no);
            }
            ImageFloderBean floderBean = imageFloderBeans.get(position);
            String path = floderBean.getTopImagePath();
            viewHolder.imageCount.setText(floderBean.getImageCounts() + "");
            viewHolder.title.setText(floderBean.getFolderName());
            //给ImageView设置路径Tag,这是异步加载图片的小技巧
            viewHolder.icon.setTag(path);

//            LocalImageLoader.getInstance().loadNativeImage(path, mPoint, AppConfig.SMALL_IMAGE_TAG, viewHolder.icon);
            // The ImageFetcher takes care of loading images into our ImageView children asynchronously
            ImageFetcher mImageFetcher = new ImageFetcher(getActivity(), 100);
            mImageFetcher.loadImage(path,viewHolder.icon, AppConfig.SMALL_IMAGE_TAG);
            return convertView;
        }
    }

    private static class ViewHolder {
        private TextView imageCount, title;
        private ImageView icon;
    }

}
