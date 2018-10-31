package com.example.sauxt.codingclinic.Data.Network;

public class ApiUtils {
    public static final String BASE_URL = "https://api.github.com/";

    public static GitHubService getGitHubService() {
        return RetrofitClient.getClient(BASE_URL).create(GitHubService.class);
    }

}
