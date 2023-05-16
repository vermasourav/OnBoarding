package com.verma.android.onboarding.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BoardingConfig {

    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private String previous;
    @SerializedName("skip")
    @Expose
    private String skip;
    @SerializedName("onboards")
    @Expose
    private List<Onboard> onboards;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public BoardingConfig withHeader(String header) {
        setHeader(header);
        return this;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public BoardingConfig withNext(String next) {
        setNext(next);
        return this;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public BoardingConfig withPrevious(String previous) {
        setPrevious(previous);
        return this;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    public BoardingConfig withSkip(String skip) {
        setSkip(skip);
        return this;
    }

    public List<Onboard> getOnboards() {
        return onboards;
    }

    public void setOnboards(List<Onboard> onboards) {
        this.onboards = onboards;
    }

    public BoardingConfig withOnboards(List<Onboard> onboards) {
        setOnboards(onboards);
        return this;
    }

}