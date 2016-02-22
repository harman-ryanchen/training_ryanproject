package com.example.ryan.weixindemo.infoobject;

import android.view.View;

import java.io.Serializable;

/**
 * Created by ryan on 12/27/15.
 */
public class DialogFragmentInfo implements Serializable {

    public static enum DialogType {
        DIALOG_NORMAL,
        DAILOG_COUNT,
        DAILOG_PROGRESS;
    }

    private String titleText;
    private String msgText;
    private DialogType dialogType;
    private View customContainer;

    public String getTitleText() {
        return titleText;
    }

    public String getMsgText() {
        return msgText;
    }

    public DialogType getDialogType() {
        return dialogType;
    }

    public View getCustomContainer() {
        return customContainer;
    }

    public static class Builder {
        DialogFragmentInfo dialogInfo;


        public Builder() {

            dialogInfo = new DialogFragmentInfo();
        }


        public Builder setTitleText(String textContent) {
            dialogInfo.titleText = textContent;
            return this;
        }
        public Builder setMsgText(String textContent) {
            dialogInfo.msgText = textContent;
            return this;
        }
        public Builder setDialogType(DialogType type) {
            dialogInfo.dialogType = type;
            return this;
        }
        public Builder setConstomContianer(View v) {
            dialogInfo.customContainer = v;
            return this;
        }




        /**
         * @throws RuntimeException if title text and logo are both set. same with twoLayerTextTitle
         *                          as well as if tag is null
         */
        public DialogFragmentInfo build() {
            /*if (mToolBarInfo.tag == null){
                throw new RuntimeException("ToolbarInfo Tag Not set. Which service is this?");
            }*/
            return dialogInfo;
        }
    }
}
