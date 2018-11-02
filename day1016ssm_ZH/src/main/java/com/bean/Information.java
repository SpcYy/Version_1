package com.bean;

import java.util.Date;

public class Information {
    private Integer informationid;

    private String informationname;

    private Integer typeid;

    private Date date;

    private String filetype;

    private String uploader;

    private String filename;

    private Infotype infotype;

    private Format format;

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }



    public Infotype getInfotype() {
        return infotype;
    }

    public void setInfotype(Infotype infotype) {
        this.infotype = infotype;
    }

    public Integer getInformationid() {
        return informationid;
    }

    public void setInformationid(Integer informationid) {
        this.informationid = informationid;
    }

    public String getInformationname() {
        return informationname;
    }

    public void setInformationname(String informationname) {
        this.informationname = informationname == null ? null : informationname.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }
}