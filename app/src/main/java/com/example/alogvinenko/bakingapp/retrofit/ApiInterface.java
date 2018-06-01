package com.example.alogvinenko.bakingapp.retrofit;

import com.example.alogvinenko.bakingapp.apiobjects.Recipe;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface {

  @GET("topher/2017/May/59121517_baking/baking.json")
  Call<List<Recipe>> getInfo();
}
