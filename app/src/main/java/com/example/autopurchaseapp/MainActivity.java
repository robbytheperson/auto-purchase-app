package com.example.autopurchaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    AutoLoan loan;
    static final String LOAN_KEY = "loan";
    static final String MONTHLY_KEY = "monthly";
    private EditText price;
    private EditText downPayment;
    private EditText rate;
    private RadioGroup termRG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        price = (EditText)findViewById(R.id.priceTyper);
        downPayment = (EditText)findViewById(R.id.downTyper);
        rate = (EditText)findViewById(R.id.rateTyper);
        loan = new AutoLoan();
        termRG = (RadioGroup)findViewById(R.id.term_RG);
    }

    public void goToSummary(View view){
        getValues();

        Intent intent = new Intent(MainActivity.this, LoanSummaryActivity.class);
        Gson gson = new Gson();
        String loanString = gson.toJson(loan);
        intent.putExtra(LOAN_KEY, loanString);
        startActivity(intent);
    }

    public void getValues() {
        try {
            loan.setPrice((double)Integer.parseInt(price.getText().toString()));
        } catch (NumberFormatException e) {
            loan.setPrice(0.0);
        }

        try {
            loan.setDownPayment((double)Integer.parseInt(downPayment.getText().toString()));
        } catch (NumberFormatException e) {
            loan.setDownPayment(0.0);
        }

        try {
            loan.setRate((double)Integer.parseInt(rate.getText().toString()) / 100);
        } catch (NumberFormatException e) {
            loan.setRate(0.0);
        }
        int radioID = termRG.getCheckedRadioButtonId();
        RadioButton term = (RadioButton)findViewById(radioID);
        loan.setLoanTerm(Integer.parseInt(term.getContentDescription().toString()));
    }
}