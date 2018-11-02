package com.bean;

public class Format {
    private Integer formatid;

    private String formattyoe;

    public Integer getFormatid() {
        return formatid;
    }

    public void setFormatid(Integer formatid) {
        this.formatid = formatid;
    }

    public String getFormattyoe() {
        return formattyoe;
    }

    public void setFormattyoe(String formattyoe) {
        this.formattyoe = formattyoe == null ? null : formattyoe.trim();
    }
}