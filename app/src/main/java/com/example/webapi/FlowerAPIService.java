package com.example.webapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlowerAPIService {


    @GET("feeds/flowers.json")
    Call<List<FlowerMainResponse>>getFlowerResponse();
}
