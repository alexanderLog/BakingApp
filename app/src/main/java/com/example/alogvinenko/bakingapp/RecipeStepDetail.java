package com.example.alogvinenko.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.alogvinenko.bakingapp.apiobjects.Ingredient;
import com.example.alogvinenko.bakingapp.apiobjects.Recipe;
import com.example.alogvinenko.bakingapp.apiobjects.Step;
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
  }
}
