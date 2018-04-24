package com.yevhenii.kpi.readmore.api.google.model;

public class ImageLinks {
    private String thumbnail;

    private String extraLarge;

    private String smallThumbnail;

    private String small;

    private String large;

    private String medium;

    public String getThumbnail ()
    {
        return thumbnail;
    }

    public void setThumbnail (String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getExtraLarge ()
    {
        return extraLarge;
    }

    public void setExtraLarge (String extraLarge)
    {
        this.extraLarge = extraLarge;
    }

    public String getSmallThumbnail ()
    {
        return smallThumbnail;
    }

    public void setSmallThumbnail (String smallThumbnail)
    {
        this.smallThumbnail = smallThumbnail;
    }

    public String getSmall ()
    {
        return small;
    }

    public void setSmall (String small)
    {
        this.small = small;
    }

    public String getLarge ()
    {
        return large;
    }

    public void setLarge (String large)
    {
        this.large = large;
    }

    public String getMedium ()
    {
        return medium;
    }

    public void setMedium (String medium)
    {
        this.medium = medium;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [thumbnail = "+thumbnail+", extraLarge = "+extraLarge+", smallThumbnail = "+smallThumbnail+", small = "+small+", large = "+large+", medium = "+medium+"]";
    }
}
