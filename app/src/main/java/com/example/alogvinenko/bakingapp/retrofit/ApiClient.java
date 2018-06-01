package com.example.alogvinenko.bakingapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiClient {

  private static Retrofit retrofit;

  static {
    String baseUrl = "https://d17h27t6h515a5.cloudfront.net/";
    retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  static ApiInterface getService() {
    return getClient().create(ApiInterface.class);
  }

  static Retrofit getClient() {
    return retrofit;
  }
}
