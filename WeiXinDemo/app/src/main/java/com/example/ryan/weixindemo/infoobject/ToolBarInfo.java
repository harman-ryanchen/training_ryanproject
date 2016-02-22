package com.example.ryan.weixindemo.infoobject;

/**
 * Created by ryan on 12/27/15.
 */
public class ToolBarInfo {
    private String titleTextContent;
    private int toolbarLogo;
    private int mNavigationIcon_color;
    private int menu = 0;

    public int getNavigationIcon_color() {
        return mNavigationIcon_color;
    }

    public String getTitleTextContent() {
        return titleTextContent;
    }

    public int getToolbarLogo() {
        return toolbarLogo;
    }

    public int getMenu() {
        return menu;
    }

    public static class Builder {
        ToolBarInfo mToolBarInfo;


        public Builder() {
            mToolBarInfo = new ToolBarInfo();
        }


        public Builder setToolBarContentText(String textContent) {
            mToolBarInfo.titleTextContent = textContent;
            return this;
        }
        public Builder setToolBarContentLogo(int logo) {
            mToolBarInfo.toolbarLogo = logo;
            return this;
        }
        public Builder setNavigationIconColor(int color) {
            mToolBarInfo.mNavigationIcon_color = color;
            return this;
        }
        public Builder setOptionMenu(int menu) {
            mToolBarInfo.menu = menu;
            return this;
        }




        /**
         * @throws RuntimeException if title text and logo are both set. same with twoLayerTextTitle
         *                          as well as if tag is null
         */
        public ToolBarInfo build() {
            /*if (mToolBarInfo.tag == null){
                throw new RuntimeException("ToolbarInfo Tag Not set. Which service is this?");
            }*/
            return mToolBarInfo;
        }
    }
}
