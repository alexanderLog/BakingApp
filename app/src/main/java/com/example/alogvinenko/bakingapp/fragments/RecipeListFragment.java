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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.alogvinenko.bakingapp.R;
import com.example.alogvinenko.bakingapp.RecipeStepDetail;
import com.example.alogvinenko.bakingapp.Recipes;
import com.example.alogvinenko.bakingapp.adapters.RecipeListAdapter;
import com.example.alogvinenko.bakingapp.apiobjects.Ingredient;
import com.example.alogvinenko.bakingapp.apiobjects.Recipe;
import com.example.alogvinenko.bakingapp.apiobjects.Step;
import com.example.alogvinenko.bakingapp.retrofit.ApiCall;
import com.example.alogvinenko.bakingapp.retrofit.ApiCallBack;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends Fragment {

  private ApiCall loginCall;

  private List<Recipe> recipes;

  private RecyclerView recyclerView;

  private ProgressBar progressBar;

  private List<Ingredient> ingredients;

  private List<Step> steps;

  private TextView ingerientsList;

  private ListView listView;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
    ingerientsList = rootView.findViewById(R.id.ingredients_list);
    listView = rootView.findViewById(R.id.steps);
    this.ingredients = getArguments().getParcelableArrayList("ingredients");
    this.steps = getArguments().getParcelableArrayList("steps");
    populateIngredients();
    populateSteps();
    return rootView;
  }

  public void populateIngredients(){
    StringBuilder message = new StringBuilder();
    for(int i = 0;i<ingredients.size();i++){
      message.append(ingredients.get(i).getQuantity()).append(" ")
          .append(ingredients.get(i).getMeasure()).append(" of ")
          .append(ingredients.get(i).getIngredient()).append(" \n");
    }
    ingerientsList.setText(message);
  }

  public void populateSteps(){

    final ArrayList<String> list = new ArrayList<String>();
    for (int i = 0; i < steps.size(); ++i) {
      list.add(steps.get(i).getShortDescription());
    }
    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
        android.R.layout.simple_list_item_1, android.R.id.text1, list);

    listView.setAdapter(adapter);
  }
}
