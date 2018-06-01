package com.example.alogvinenko.bakingapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.alogvinenko.bakingapp.R;
import com.example.alogvinenko.bakingapp.RecipeStepDetail;
import com.example.alogvinenko.bakingapp.Recipes;
import com.example.alogvinenko.bakingapp.adapters.RecipeListAdapter;
import com.example.alogvinenko.bakingapp.apiobjects.Ingredient;
import com.example.alogvinenko.bakingapp.apiobjects.Recipe;
import com.example.alogvinenko.bakingapp.retrofit.ApiCall;
import com.example.alogvinenko.bakingapp.retrofit.ApiCallBack;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends Fragment implements
    RecipeListAdapter.OrderAdapterOnClickerHandler {

  private ApiCall loginCall;

  private List<Recipe> recipes;

  private RecyclerView recyclerView;

  private ProgressBar progressBar;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.recipe_list_fragment, container, false);
    recyclerView = rootView.findViewById(R.id.recycleview);
    progressBar = rootView.findViewById(R.id.progressbar);
    LayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
    setUpAdapter();

    return rootView;
  }

  private void setUpAdapter() {
    loginCall = new ApiCall(getContext(), new ApiCallBack<List<Recipe>>() {
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
    RecipeListAdapter recipeListAdapter = new RecipeListAdapter(getContext(), recipes, this);
    recyclerView.setAdapter(recipeListAdapter);
  }

  @Override
  public void onClick(int position) {
    Intent recipeDetail = new Intent(getContext(), RecipeStepDetail.class);
    recipeDetail.putParcelableArrayListExtra("ingredients",
        (ArrayList<? extends Parcelable>) recipes.get(position).getIngredients());
    recipeDetail.putParcelableArrayListExtra("steps",
        (ArrayList<? extends Parcelable>) recipes.get(position).getSteps());

    startActivity(recipeDetail);
  }
}
