package com.project.roy.cobabelajar.data.remote;

import com.google.gson.JsonObject;
import com.project.roy.cobabelajar.data.model.Car;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by roy on 1/2/2018.
 */

public interface Api {

    @GET("cars")
    Call<JsonObject> getCarAll();

    @POST("cars")
    Call<JsonObject> carAdd(@Body Car params);

    @DELETE("cars/{id}")
    Call<JsonObject> carDelete(@Path("id") int id);
}
