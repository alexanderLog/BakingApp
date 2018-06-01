package com.example.alogvinenko.bakingapp.retrofit;

import android.content.Context;
import com.example.alogvinenko.bakingapp.apiobjects.Recipe;
import java.lang.ref.WeakReference;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCall {

  private final ApiCallBack<List<Recipe>> apiCallback;

  private ApiInterface mService;

  private WeakReference<Context> weakContext;

  private int errorCode;

  public ApiCall(Context context, ApiCallBack<List<Recipe>> apiCallback) {
    this.apiCallback = apiCallback;
    this.weakContext = new WeakReference<>(context);
    this.mService = ApiClient.getService();
  }

  public void getRecipes() {
    mService.getInfo().enqueue(new Callback <List<Recipe>>() {
      @Override
      public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
        if (response.isSuccessful()) {
          apiCallback.onCallFinished(response.body());
        }
      }

      @Override
      public void onFailure(Call<List<Recipe>>  call, Throwable t) {
        System.out.println(t);
      }
    });
  }
}
