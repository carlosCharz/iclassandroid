package com.wedevol.smartclass.navigation;

/** Created by Paolo on 12/8/2016.*/
class NavDrawerItem {
    private String title;
    private int resourceId;
    private boolean withImage;
    private boolean lastItemwithImage;

    public NavDrawerItem() {

    }

    /**
     * Constructor
     *
     * @param title the title of the navigation drawer item (what the user see in text
     * @param withImage if the item has an image
     * @param lastItemwithImage if it is the final item of a series and a separator is required
     * @param resourceId the id of the image
     *
     * */
    NavDrawerItem(String title, boolean withImage, boolean lastItemwithImage, int resourceId) {
        this.title = title;
        this.withImage = withImage;
        this.resourceId = resourceId;
        this.lastItemwithImage = lastItemwithImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public boolean isWithImage() {
        return withImage;
    }

    public void setWithImage(boolean withImage) {
        this.withImage = withImage;
    }

    public boolean isLastItemwithImage() {
        return lastItemwithImage;
    }

    public void setLastItemwithImage(boolean lastItemwithImage) {
        this.lastItemwithImage = lastItemwithImage;
    }
}