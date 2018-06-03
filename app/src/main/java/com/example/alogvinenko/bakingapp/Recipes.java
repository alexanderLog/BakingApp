package com.example.alogvinenko.bakingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.alogvinenko.bakingapp.adapters.RecipeListAdapter;
import com.example.alogvinenko.bakingapp.apiobjects.Recipe;
import com.example.alogvinenko.bakingapp.retrofit.ApiCall;
import com.example.alogvinenko.bakingapp.retrofit.ApiCallBack;
import java.util.ArrayList;
import java.util.List;

public class Recipes extends AppCompatActivity implements
    RecipeListAdapter.OrderAdapterOnClickerHandler {

  private ApiCall loginCall;

  private List<Recipe> recipes;

  private RecyclerView recyclerView;

  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.recycleview);
    progressBar = findViewById(R.id.progressbar);
    LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    setUpAdapter();
  }

  private void setUpAdapter() {
    loginCall = new ApiCall(this, new ApiCallBack<List<Recipe>>() {
      @Override
      public void onCallFinished(List<Recipe> result) {
        recipes = result;
        attachRecyclerView(result);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

      }
    });
    loginCall.getRecipes();
  }

  public void attachRecyclerView(List<Recipe> recipes) {
    RecipeListAdapter recipeListAdapter = new RecipeListAdapter(getApplicationContext(), recipes, this);
    recyclerView.setAdapter(recipeListAdapter);
  }

  @Override
  public void onClick(int position) {
    Intent recipeDetail = new Intent(this, RecipeStepDetail.class);
    recipeDetail.putParcelableArrayListExtra("ingredients",
        (ArrayList<? extends Parcelable>) recipes.get(position).getIngredients());
    recipeDetail.putParcelableArrayListExtra("steps",
        (ArrayList<? extends Parcelable>) recipes.get(position).getSteps());

    startActivity(recipeDetail);
  }
}

