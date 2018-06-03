package com.example.alogvinenko.bakingapp.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.example.alogvinenko.bakingapp.R;
import com.example.alogvinenko.bakingapp.apiobjects.Step;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Logvinenko on 2018-06-03.
 */

public class StepsAdapter extends ArrayAdapter<Step> {

  private List<Step> steps;

  private Context context;

  public StepsAdapter(ArrayList<Step> steps, Context context) {
    super(context, R.layout.row_item, data);
    this.dataSet = data;
    this.mContext=context;

  }

}
