package com.example.ronitshrivastava.foodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class BMI_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText minputWeight;
    EditText minputHeight;
    Spinner mspinnerWeight;
    Spinner mspinnerHeight;
    Button mCalculateButton;
    TextView mBMIValue;
    TextView mBMITag;
    LinearLayout mBMIClassification;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_);
        minputWeight = (EditText) findViewById(R.id.inputWeight);
        minputHeight = (EditText) findViewById(R.id.inputHeight);
        mspinnerWeight = (Spinner) findViewById(R.id.spinnerWeight);
        mspinnerHeight = (Spinner) findViewById(R.id.spinnerHeight);
        mCalculateButton = (Button) findViewById(R.id.CalculateButton);
        mBMITag = (TextView) findViewById(R.id.BMITag);
        mBMIValue = (TextView) findViewById(R.id.BMIValue);
        mBMIClassification = (LinearLayout) findViewById(R.id.BMIClassification);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SpinnerwtItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinnerWeight.setAdapter(adapter);
        mspinnerWeight.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.SpinnerhtItems, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinnerHeight.setAdapter(adapter2);
        mspinnerHeight.setOnItemSelectedListener(this);
    }

    public void Calculate(View view) {
        if (vacant()) {
            Double weight = Double.parseDouble(minputWeight.getText().toString());
            Double height = Double.parseDouble(minputHeight.getText().toString());
            Double result = 0.0;
            if (mspinnerWeight.getSelectedItem().toString().equals("kgs") && mspinnerHeight.getSelectedItem().toString().equals("cm")) {
                result = weight / ((height / 100) * (height / 100));
            } else if (mspinnerWeight.getSelectedItem().toString().equals("kgs") && mspinnerHeight.getSelectedItem().toString().equals("in")) {
                result = weight / (((height * 2.54) / 100) * ((height * 2.54) / 100));
            } else if (mspinnerWeight.getSelectedItem().toString().equals("lbs") && mspinnerHeight.getSelectedItem().toString().equals("cm")) {
                result = (weight / 2.2) / ((height / 100) * (height / 100));
            } else if (mspinnerWeight.getSelectedItem().toString().equals("lbs") && mspinnerHeight.getSelectedItem().toString().equals("in")) {
                result = (weight * 703) / (height * height);
            }
            if (result < 16.0) {
                mBMITag.setText("Very Severely Underweight");
            } else if (result >= 16.0 && result < 17.0) {
                mBMITag.setText("Severely Underweight");
            } else if (result >= 17.0 && result < 18.5) {
                mBMITag.setText("Underweight");
            } else if (result >= 18.5 && result < 25.0) {
                mBMITag.setText("Normal");
            } else if (result >= 25.0 && result < 30.0) {
                mBMITag.setText("Overweight");
            } else if (result >= 30.0 && result < 35.0) {
                mBMITag.setText("Obese Class I");
            } else if (result >= 35.0 && result < 40.0) {
                mBMITag.setText("Obese Class II");
            } else if (result > 40.0) {
                mBMITag.setText("Obese Class III");
            }
            result = Double.parseDouble(new DecimalFormat("###.##").format(result));
            mBMIValue.setText(result.toString());
            mBMIClassification.setVisibility(View.VISIBLE);
        }
    }

    public void Reset(View view) {
        minputWeight.setText(null);
        minputHeight.setText(null);
        mBMIClassification.setVisibility(View.INVISIBLE);
        mBMIValue.setText(null);
        mBMITag.setText(null);
    }

    public boolean vacant() {
        if (minputWeight.getText().toString().equals("") || minputHeight.getText().toString().equals("")) {
            if (minputWeight.getText().toString().equals("")) {
                minputWeight.setError("This Field Can't be Empty");

            }
            if (minputHeight.getText().toString().equals("")) {
                minputHeight.setError("This Field Can't be Empty");

            }
            if (minputWeight.getText().toString().equals("") && minputHeight.getText().toString().equals("")) {
                minputWeight.setError("This Field Can't be Empty");
                minputHeight.setError("This Field Can't be Empty");
            }
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
