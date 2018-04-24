package com.yevhenii.kpi.readmore.api.google.model;

public class GoogleBookResponse {

    private String id;

    private String etag;

    private VolumeInfo volumeInfo;

    private String selfLink;

    private String kind;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public VolumeInfo getVolumeInfo ()
    {
        return volumeInfo;
    }

    public void setVolumeInfo (VolumeInfo volumeInfo)
    {
        this.volumeInfo = volumeInfo;
    }

    public String getSelfLink ()
    {
        return selfLink;
    }

    public void setSelfLink (String selfLink)
    {
        this.selfLink = selfLink;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "GoogleBookResponse{" +
                "id='" + id + '\'' +
                ", etag='" + etag + '\'' +
                ", volumeInfo=" + volumeInfo +
                ", selfLink='" + selfLink + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}

