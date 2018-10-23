package com.example.sauxt.codingclinic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {

    @GET("users")
    Call<List<GitUsers>> getGitHubUsers();

}
