package com.example.sauxt.codingclinic.Data.Network;

import com.example.sauxt.codingclinic.Data.Entity.GitUsers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {

    @GET("users")
    Call<List<GitUsers>> getGitHubUsers();

}
