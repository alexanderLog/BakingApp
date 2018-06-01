package com.example.alogvinenko.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.alogvinenko.bakingapp.R;
import com.example.alogvinenko.bakingapp.apiobjects.Recipe;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.w3c.dom.Text;

public class RecipeListAdapter extends
    RecyclerView.Adapter<RecipeListAdapter.RecipeListAdapterViewHolder> {

  private List<Recipe> recipes;

  private Context mContext;

  private final OrderAdapterOnClickerHandler mClickListener;

  public interface OrderAdapterOnClickerHandler {

    void onClick(int position);
  }

  public RecipeListAdapter(Context context, List<Recipe> recipes, OrderAdapterOnClickerHandler
      mClickListener) {
    this.mClickListener = mClickListener;
    this.recipes = recipes;
    this.mContext = context;
  }

  @NonNull
  @Override
  public RecipeListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.item_main_recipe, parent, false);
    return new RecipeListAdapterViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecipeListAdapterViewHolder holder, int position) {
    if (recipes.get(position) == null) {
      return;
    }
    holder.recipeName.setText(recipes.get(position).getName());
  }

  @Override
  public int getItemCount() {
    return recipes.size();
  }

  public class RecipeListAdapterViewHolder extends RecyclerView.ViewHolder implements
      OnClickListener {

    private TextView recipeName;

    private RecipeListAdapterViewHolder(View itemView) {
      super(itemView);
      recipeName = itemView.findViewById(R.id.recipe_name);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      int clickedPosition = getAdapterPosition();
      mClickListener.onClick(clickedPosition);
    }
  }
}
