package com.example.mvp_sample2.model;

public class ResultFormat {
    private String hash;
    private String url;
    private String orgUrl;

    public ResultFormat(String hash, String url, String orgUrl) {
        this.hash = hash;
        this.url = url;
        this.orgUrl = orgUrl;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrgUrl() {
        return orgUrl;
    }

    public void setOrgUrl(String orgUrl) {
        this.orgUrl = orgUrl;
    }
}
