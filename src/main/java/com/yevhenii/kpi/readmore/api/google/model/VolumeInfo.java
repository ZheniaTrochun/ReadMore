package com.yevhenii.kpi.readmore.api.google.model;

import java.util.Arrays;

public class VolumeInfo {
    private String pageCount;

    private String averageRating;

    private String infoLink;

    private String mainCategory;

    private String printType;

    private String publisher;

    private String[] authors;

    private String canonicalVolumeLink;

    private String title;

    private String ratingsCount;

    private String description;

    private ImageLinks imageLinks;

    private String contentVersion;

    private String[] categories;

    private String language;

    private String publishedDate;

    public String getPageCount ()
    {
        return pageCount;
    }

    public void setPageCount (String pageCount)
    {
        this.pageCount = pageCount;
    }

    public String getAverageRating ()
    {
        return averageRating;
    }

    public void setAverageRating (String averageRating)
    {
        this.averageRating = averageRating;
    }

    public String getInfoLink ()
    {
        return infoLink;
    }

    public void setInfoLink (String infoLink)
    {
        this.infoLink = infoLink;
    }

    public String getMainCategory ()
    {
        return mainCategory;
    }

    public void setMainCategory (String mainCategory)
    {
        this.mainCategory = mainCategory;
    }

    public String getPrintType ()
    {
        return printType;
    }

    public void setPrintType (String printType)
    {
        this.printType = printType;
    }

    public String getPublisher ()
    {
        return publisher;
    }

    public void setPublisher (String publisher)
    {
        this.publisher = publisher;
    }

    public String[] getAuthors ()
    {
        return authors;
    }

    public void setAuthors (String[] authors)
    {
        this.authors = authors;
    }

    public String getCanonicalVolumeLink ()
    {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink (String canonicalVolumeLink)
    {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getRatingsCount ()
    {
        return ratingsCount;
    }

    public void setRatingsCount (String ratingsCount)
    {
        this.ratingsCount = ratingsCount;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public ImageLinks getImageLinks ()
    {
        return imageLinks;
    }

    public void setImageLinks (ImageLinks imageLinks)
    {
        this.imageLinks = imageLinks;
    }

    public String getContentVersion ()
    {
        return contentVersion;
    }

    public void setContentVersion (String contentVersion)
    {
        this.contentVersion = contentVersion;
    }

    public String[] getCategories ()
    {
        return categories;
    }

    public void setCategories (String[] categories)
    {
        this.categories = categories;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getPublishedDate ()
    {
        return publishedDate;
    }

    public void setPublishedDate (String publishedDate)
    {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "pageCount='" + pageCount + '\'' +
                ", averageRating='" + averageRating + '\'' +
                ", infoLink='" + infoLink + '\'' +
                ", mainCategory='" + mainCategory + '\'' +
                ", printType='" + printType + '\'' +
                ", publisher='" + publisher + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", canonicalVolumeLink='" + canonicalVolumeLink + '\'' +
                ", title='" + title + '\'' +
                ", ratingsCount='" + ratingsCount + '\'' +
                ", description='" + description + '\'' +
                ", imageLinks=" + imageLinks +
                ", contentVersion='" + contentVersion + '\'' +
                ", categories=" + Arrays.toString(categories) +
                ", language='" + language + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                '}';
    }
}
