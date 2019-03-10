package com.example.ronitshrivastava.foodapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

public class NutrientsActivity extends AppCompatActivity {
    private String TAG="mytest";
    private ProgressDialog progressDialog;
    private ListView listView;
    private static final String[] NUTRIENTS_KEYS={"ENERC_KCAL", "FAT","FASAT","FAMS","FAPU","CHOCDF","FIBTG","SUGAR","PROCNT",
            "NA","CA", "MG","K","FE","ZN","P", "VITA_RAE","VITC","THIA", "RIBF","NIA","VITB6A","FOLDFE", "FOLFD","TOCPHA","VITK1"};
    HashMap<String ,String> nutrients;
    String searchFoodStr;
    EditText foodItem;
    TextView calories;
    TextView totalWeight;
    LinearLayout linearLayout;
    RadioGroup smallGroup;
    RadioGroup glassGroup;
    RadioGroup oneGroup;
    RadioButton small,large,medium;
    RadioButton glass,cup,bowl;
    RadioButton one,two;
    TextView nutritionalValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrients);
        nutrients=new HashMap<>();
        foodItem=(EditText)findViewById(R.id.foodItem);
        calories=(TextView)findViewById(R.id.calories);
        totalWeight=(TextView)findViewById(R.id.totalWeight);
        nutritionalValues=(TextView)findViewById(R.id.nutritionalValues);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutRecord);
        /*smallGroup=(RadioGroup) findViewById(R.id.smallGroup);
        glassGroup=(RadioGroup)findViewById(R.id.glassGroup);
        oneGroup=(RadioGroup)findViewById(R.id.oneGroup);
        small=(RadioButton) findViewById(R.id.small);
        large=(RadioButton)findViewById(R.id.large);
        medium=(RadioButton)findViewById(R.id.medium);
        glass=(RadioButton)findViewById(R.id.glass);
        cup=(RadioButton)findViewById(R.id.cup);
        bowl=(RadioButton)findViewById(R.id.bowl);
        one=(RadioButton)findViewById(R.id.one);
        two=(RadioButton)findViewById(R.id.two);*/
    }
    private class GetContacts extends AsyncTask<Void,Void,Void> {
        String searchFood;
        public GetContacts(String searchFood){
            this.searchFood=searchFood;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //This creates the progress bar which shows loading please wait
            progressDialog=new ProgressDialog(NutrientsActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Loading...Please Wait");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh=new HttpHandler();
            String jsonStr=sh.makeServiceCall(searchFood);
            Log.e(TAG,"Response from url:"+jsonStr);
            if(jsonStr!=null)
            {
                try{
                    //HashMap<String,String>nutrients=new HashMap<>();
                    JSONObject jsonObject=new JSONObject(jsonStr);
                    String calories=jsonObject.getString("calories");
                    nutrients.put("calories","Calories: "+calories+"kcal");
                    String totalWeight=jsonObject.getString("totalWeight");
                    nutrients.put("totalWeight","Total Weight: "+totalWeight+"g");
                    //JSONArray dietLabels=jsonObject.getJSONArray("dietLabels");
                    //JSONObject label1=dietLabels.getJSONObject(0);
                    // String lbl1=label1.toString();
                    JSONObject totalNutrients=jsonObject.getJSONObject("totalNutrients");
                    for (int i = 0; i < 26; i++) {
                        try {
                            JSONObject obj = totalNutrients.getJSONObject(NUTRIENTS_KEYS[i]);
                            String label = obj.getString("label");
                            //nutrients.put(NUTRIENTS_KEYS[i]+"label",label);
                            String quantityStr = obj.getString("quantity");
                            double quantity = Double.parseDouble(quantityStr);
                            quantity = Double.parseDouble(new DecimalFormat("###.##").format(quantity));
                            //nutrients.put(NUTRIENTS_KEYS[i]+"quantity",quantity);
                            String unit = obj.getString("unit");
                            nutrients.put(NUTRIENTS_KEYS[i] + "unit", label + ": " + quantity + "" + unit);
                        }
                        catch (JSONException e){
                            continue;
                        }
                    }

                } catch (JSONException e) {
                    Log.e(TAG,"Exception "+ e.getMessage());
                    Toast.makeText(getApplicationContext(),"Error: Some wrong keyword must have entered! ",Toast.LENGTH_SHORT).show();

                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Database is not active.\nTry after some time or check internet connection.",Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(progressDialog.isShowing())
                progressDialog.dismiss();
            try {
                Iterator i = nutrients.keySet().iterator();
                nutritionalValues.setVisibility(View.VISIBLE);
                linearLayout.removeAllViews();
                while (i.hasNext()) {
                    String key = i.next().toString();
                    String textContent = nutrients.get(key);
                    if(key.equals("calories"))
                    {
                        calories.setText(textContent);
                        calories.setTextSize(20);
                        calories.setTextColor(getResources().getColor(R.color.colorAccent));
                        calories.setVisibility(View.VISIBLE);
                        calories.setPadding(80, 15, 0, 10);
                        continue;
                    }
                    if(key.equals("totalWeight"))
                    {
                        totalWeight.setText(textContent);
                        totalWeight.setTextSize(20);
                        totalWeight.setTextColor(getResources().getColor(R.color.colorAccent));
                        totalWeight.setVisibility(View.VISIBLE);
                        totalWeight.setPadding(62, 15, 0, 10);
                        continue;
                    }
                    TextView textView = new TextView(getApplicationContext());
                    textView.setText(textContent);
                    textView.setTextSize(20);
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textView.setPadding(30, 10, 0, 10);
                    linearLayout.addView(textView);

                }
            }catch (Exception e)
            {
                Log.e(TAG,"Exception "+ e.getMessage());
                //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void clickedGet(View view) {
        /*String one_two,glass_cup_bowl;
        String small_medium_large;
        if(!oneGroup.isSelected())
            one_two="1";
        else
        {
            if(one.isSelected())
                one_two="1";
            else
                one_two="2";
        }
        if(!glassGroup.isSelected())
            glass_cup_bowl="";
        else
        {
            if (glass.isSelected())
                glass_cup_bowl="glass";
            else if(cup.isSelected())
                glass_cup_bowl="cup";
            else
                glass_cup_bowl="bowl";
        }
        if (!smallGroup.isSelected())
            small_medium_large="small";
        else {
            if (small.isSelected())
                small_medium_large = "small";
            else if(medium.isSelected())
                small_medium_large="medium";
            else
                small_medium_large="large";
        }*/
        searchFoodStr=foodItem.getText().toString();
        if(searchFoodStr.equals(""))
        {
            resetMethod();
            Toast.makeText(getApplicationContext(),"Oops! vacant not allowed.",Toast.LENGTH_SHORT).show();
            return;
        }
        searchFoodStr=searchFoodStr.replace(" ","%20");
        //searchFoodStr="1%20"+searchFoodStr;
        //searchFoodStr=one_two+"%20"+small_medium_large+"%20"+foodItem.getText().toString().trim()+"%20"+glass_cup_bowl;
        new GetContacts(searchFoodStr).execute();
    }
    public void resetMethod(){
        foodItem.setText(null);
        calories.setVisibility(View.INVISIBLE);
        totalWeight.setVisibility(View.INVISIBLE);
        nutritionalValues.setVisibility(View.INVISIBLE);
        linearLayout.removeAllViews();
    }
    public void ResetAll(View view)
    {
        resetMethod();
    }
}
