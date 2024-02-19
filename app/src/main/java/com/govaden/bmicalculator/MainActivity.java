package com.govaden.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Class Variables; also are called as Fields
    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText weightEditText;
    private EditText inchesEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);

        maleRadioButton = findViewById(R.id.radio_button_male);
        femaleRadioButton = findViewById(R.id.radio_button_female);

        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBMI();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {

                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }

            }
        });
    }


    private double calculateBMI() {


        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        // Converting the number 'Strings' into 'int' variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        // conversion from inches to meters
        double heightInMeters = totalInches * 0.0254;

        // BMI formula = kg/m^2
        return weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi) {
        // we must convert decimal/double into a String for our TextView
        // String bmiTextResult = String.valueOf(bmi);
        // resultText.setText(bmiTextResult);

        // to format decimal numbers, with a pattern
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi); // formatting 'bmi' to specified pattern

        String fullResultString;
        if (bmi < 18.5) {
            // Display underweight
            fullResultString = bmiTextResult + " - you are underweight";
        } else if (bmi > 25) {
            // Display overweight
            fullResultString = bmiTextResult + " - you are overweight";
        } else {
            // Display healthy
            fullResultString = bmiTextResult + " - you are a healthy weight";
        }

        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi); // formatting 'bmi' to specified pattern

        String fullResultString;

        if (maleRadioButton.isChecked()) {
            // Display male guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult your doctor for healthy range for boys";

        } else if (femaleRadioButton.isChecked()) {
            // Display female guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult your doctor for healthy range for girls";
        } else {
            // Display general guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult your doctor for healthy range";
        }

        resultText.setText(fullResultString);
    }

}