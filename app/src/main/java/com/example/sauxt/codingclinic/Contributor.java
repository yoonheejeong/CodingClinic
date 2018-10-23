package com.example.sauxt.codingclinic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributor {

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    @SerializedName("contributions")
    @Expose
    private int contributions;

    public String getLogin() {
        return login;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public int getContributions() {
        return contributions;
    }

    @Override
    public String toString() {
        return login;
    }

}


