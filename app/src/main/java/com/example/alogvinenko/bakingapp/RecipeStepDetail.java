package com.example.alogvinenko.bakingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.alogvinenko.bakingapp.apiobjects.Ingredient;
import com.example.alogvinenko.bakingapp.apiobjects.Recipe;
import com.example.alogvinenko.bakingapp.apiobjects.Step;
import com.example.alogvinenko.bakingapp.fragments.RecipeListFragment;
import java.util.ArrayList;
import java.util.List;

public class RecipeStepDetail extends AppCompatActivity {

  private List<Ingredient> ingredients;

  private List<Step> steps;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_step_detail);
    ingredients = getIntent().getParcelableArrayListExtra("ingredients");
    steps = getIntent().getParcelableArrayListExtra("steps");


    RecipeListFragment recipeListFragment = new RecipeListFragment();

    Bundle args = new Bundle();
    args.putParcelableArrayList("ingredients", (ArrayList<? extends Parcelable>) ingredients);
    args.putParcelableArrayList("steps", (ArrayList<? extends Parcelable>) steps);
    recipeListFragment.setArguments(args);

    // Add the fragment to its container using a FragmentManager and a Transaction
    FragmentManager fragmentManager = getSupportFragmentManager();

    fragmentManager.beginTransaction()
        .add(R.id.recipe_list_fragment, recipeListFragment)
        .commit();

  }
}
