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
import com.example.ryan.weixindemo.util.LogUtil;

/**
 * Created by ryan on 2/20/16.
 */
public class DialogFragmentContainer extends DialogFragment {
    public static String DIALOG_TYPE = "DIALOG_TYPE";
    private TextView mDialog_msg;

    public static enum DialogType {
        DIALOG_NORMAL,
        DAILOG_COUNT,
        DAILOG_PROGRESS;
    }

    public static DialogFragmentContainer newInstance(DialogFragmentContainer.DialogType dialogType) {
        DialogFragmentContainer frag = new DialogFragmentContainer();
        Bundle args = new Bundle();
        args.putString(DIALOG_TYPE, dialogType.name());
        frag.setArguments(args);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String type = (String) getArguments().get(DIALOG_TYPE);
        if (type == null) return null;
        View view = null;
        if (type.equals(DialogType.DIALOG_NORMAL.name())) {
            view = inflater.inflate(R.layout.view_dialog_normal, null);
        } else if (type.equals(DialogType.DAILOG_COUNT.name())) {
            view = inflater.inflate(R.layout.view_dialog_counter, null);
        } else if (type.equals(DialogType.DAILOG_PROGRESS.name())) {
            view = inflater.inflate(R.layout.view_dialog_progress, null);
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
        mDialog_msg = (TextView) view.findViewById(R.id.dialog_msg);
    }

    public void setDialogMsg(String msg) {
        if (mDialog_msg != null) {
            mDialog_msg.setText(msg);
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
}
