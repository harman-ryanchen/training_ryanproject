package com.example.ryan.weixindemo.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.ryan.weixindemo.R;
import com.example.ryan.weixindemo.infoobject.DialogFragmentInfo;
import com.example.ryan.weixindemo.util.LogUtil;


/**
 * Created by ryan on 2/20/16.
 */
public class DialogFragmentContainer extends DialogFragment implements View.OnClickListener {
    public static String DIALOG_TYPE = "DIALOG_TYPE";
    private TextView okTv, cancelTv,msgText_tv,titleText_tv;



    public static DialogFragmentContainer newInstance(DialogFragmentInfo info) {
        DialogFragmentContainer frag = new DialogFragmentContainer();
        Bundle args = new Bundle();
        args.putSerializable(DIALOG_TYPE,info);
        frag.setArguments(args);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogFragmentInfo info = (DialogFragmentInfo) getArguments().get(DIALOG_TYPE);
        if (info == null) return null;
        View view =null;
        if (info.getCustomContainer()==null){
            view = inflater.inflate(R.layout.view_dialog_normal,null);
        }else{
            view = info.getCustomContainer();
        }
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        msgText_tv = (TextView) view.findViewById(R.id.dialog_msg);
        okTv = (TextView) view.findViewById(R.id.ok_tv);
        cancelTv = (TextView) view.findViewById(R.id.cancel_tv);
        titleText_tv = (TextView) view.findViewById(R.id.title);
        msgText_tv = (TextView) view.findViewById(R.id.dialog_msg);

        if (okTv!=null){
            okTv.setOnClickListener(this);
        } if (okTv!=cancelTv){
            okTv.setOnClickListener(this);
        }
    }

    public void setDialogMsg(String msg) {
        if (msgText_tv != null) {
            msgText_tv.setText(msg);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.l();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.l();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.l();
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.l();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_tv:
                break;
            case R.id.cancel_tv:
                dismiss();
                break;
        }
    }
}
