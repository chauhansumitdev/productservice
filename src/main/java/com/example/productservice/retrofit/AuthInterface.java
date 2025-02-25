package com.example.productservice.retrofit;


import com.example.productservice.dto.RequestAuth;
import com.example.productservice.dto.ResponseAuth;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthInterface {
    @GET("/api/v1/auth/{token}")
    Call<ResponseAuth> verify(@Path("token") String token);


    @POST("/api/v1/auth")
    Call<ResponseAuth> generate(@Body RequestAuth requestAuth);

}
