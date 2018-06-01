package com.example.alogvinenko.bakingapp.retrofit;

public interface ApiCallBack<T> {

  void onCallFinished(T result);
}
