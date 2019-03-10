package com.example.ronitshrivastava.foodapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

public class DietActivity extends AppCompatActivity {
    Spinner spinnerBreakfast1;
    ProgressDialog progressDialog;
    TextView calories;
    Double foodItemKcal;
    ImageButton fooditemBtn1,fooditemBtn2,fooditemBtn3,fooditemBtn4,fooditemBtn5;
    EditText fooditem1,fooditem2,fooditem3,fooditem4,fooditem5;
    String foodItemKcalStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        fooditemBtn1=(ImageButton)findViewById(R.id.fooditemBtn1);
        fooditemBtn2=(ImageButton)findViewById(R.id.fooditemBtn2);
        fooditemBtn3=(ImageButton)findViewById(R.id.fooditemBtn3);
        fooditemBtn4=(ImageButton)findViewById(R.id.fooditemBtn4);
        fooditemBtn5=(ImageButton)findViewById(R.id.fooditemBtn5);
        fooditem1=(EditText)findViewById(R.id.fooditem1);
        fooditem2=(EditText)findViewById(R.id.fooditem2);
        fooditem3=(EditText)findViewById(R.id.fooditem3);
        fooditem4=(EditText)findViewById(R.id.fooditem4);
        fooditem5=(EditText)findViewById(R.id.fooditem5);
        calories=(TextView)findViewById(R.id.mealCaloriesKcal);
        foodItemKcal= new Double(0.0);
        spinnerBreakfast1 = (Spinner) findViewById(R.id.spinnerBreakfast1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SpinnerBreakfast1Items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBreakfast1.setAdapter(adapter);
        spinnerBreakfast1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void resetDiet(View view){
        calories.setText("");
        fooditem1.setText("");
        fooditem2.setText("");
        fooditem3.setText("");
        fooditem4.setText("");
        fooditem5.setText("");
        fooditemBtn1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        fooditemBtn2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        fooditemBtn3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        fooditemBtn4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        fooditemBtn5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    public void calculateMeal(View view) {
        if (foodItemKcal==0.0)
            Toast.makeText(getApplicationContext(),"Error: Some wrong keyword must have entered! ",Toast.LENGTH_SHORT).show();
        calories.setText(foodItemKcal.toString());
        calories.setTextSize(20);
        fooditemBtn1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        fooditemBtn2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        fooditemBtn3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        fooditemBtn4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        fooditemBtn5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        calories.setTextColor(getResources().getColor(R.color.colorAccent));
        foodItemKcal=0.0;
    }

    public void onFooditem1Clicked(View view) {
        fooditemBtn1.setBackgroundColor(getResources().getColor(R.color.red));
        String foodItemStr = fooditem1.getText().toString();
        if (foodItemStr.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Vacant not allowed.",Toast.LENGTH_SHORT).show();
            return;
        }
        foodItemStr = foodItemStr.replace(" ", "%20");
        //foodItemStr = "1%20" + foodItemStr;
        new GetNutrients(foodItemStr).execute();
    }

    public void onFooditem2Clicked(View view) {
        fooditemBtn2.setBackgroundColor(getResources().getColor(R.color.red));
        String foodItemStr = fooditem2.getText().toString();
        if (foodItemStr.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Vacant not allowed.",Toast.LENGTH_SHORT).show();
            return;
        }
        foodItemStr = foodItemStr.replace(" ", "%20");
        //foodItemStr = "1%20" + foodItemStr;
        new GetNutrients(foodItemStr).execute();
    }

    public void onFooditem3Clicked(View view) {
        fooditemBtn3.setBackgroundColor(getResources().getColor(R.color.red));
        String foodItemStr = fooditem3.getText().toString();
        if (foodItemStr.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Vacant not allowed.",Toast.LENGTH_SHORT).show();
            return;
        }
        foodItemStr = foodItemStr.replace(" ", "%20");
        //foodItemStr = "1%20" + foodItemStr;
        new GetNutrients(foodItemStr).execute();
    }

    public void onFooditem4Clicked(View view) {
        fooditemBtn4.setBackgroundColor(getResources().getColor(R.color.red));
        String foodItemStr = fooditem4.getText().toString();
        if (foodItemStr.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Vacant not allowed.",Toast.LENGTH_SHORT).show();
            return;
        }
        foodItemStr = foodItemStr.replace(" ", "%20");
        //foodItemStr = "1%20" + foodItemStr;
        new GetNutrients(foodItemStr).execute();
    }

    public void onFooditem5Clicked(View view) {
        fooditemBtn5.setBackgroundColor(getResources().getColor(R.color.red));
        String foodItemStr = fooditem5.getText().toString();
        if (foodItemStr.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Vacant not allowed.",Toast.LENGTH_SHORT).show();
            return;
        }
        foodItemStr = foodItemStr.replace(" ", "%20");
        //foodItemStr = "1%20" + foodItemStr;
        new GetNutrients(foodItemStr).execute();
    }

    private class GetNutrients extends AsyncTask<Void,Void,Void> {
        String searchFood;
        public GetNutrients(String searchFood){
            this.searchFood=searchFood;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //This creates the progress bar which shows loading please wait
            progressDialog=new ProgressDialog(DietActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Loading...Please Wait");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh=new HttpHandler();
            String jsonStr=sh.makeServiceCall(searchFood);
            Log.e("mytest","Response from url:"+jsonStr);
            if(jsonStr!=null)
            {
                try{
                    JSONObject jsonObject=new JSONObject(jsonStr);
                    String calories=jsonObject.getString("calories");
                    foodItemKcalStr=calories;
                } catch (JSONException e) {
                    Log.e("mytest","Exception "+ e.getMessage());
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
                Double kcal=Double.parseDouble(foodItemKcalStr);
                if (kcal==0.0)
                    Toast.makeText(getApplicationContext(),"Error: Some wrong keyword must have entered! ",Toast.LENGTH_SHORT).show();
                foodItemKcal+=kcal;
            }catch (Exception e)
            {
                Log.e("mytest","Exception "+ e.getMessage());
                //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }
    }

}

