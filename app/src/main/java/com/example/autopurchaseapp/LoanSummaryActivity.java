package com.example.autopurchaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.autopurchaseapp.R;
import com.google.gson.Gson;

public class LoanSummaryActivity extends AppCompatActivity {
    AutoLoan loan;

    TextView monthlyTV;
    TextView vPrice;
    TextView vDown;
    TextView vTax;
    TextView vTotal;
    TextView vLoanAmt;
    TextView vInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan_summary_activity);

        monthlyTV = findViewById(R.id.monthly_TV);

        vPrice = findViewById(R.id.vPrice_in_dollars);
        vDown = findViewById(R.id.vDown_payment_TV);
        vTax = findViewById(R.id.vTax_TV);
        vTotal = findViewById(R.id.vCost_display);
        vLoanAmt = findViewById(R.id.vLoan_amount_TV);
        vInterest = findViewById(R.id.vInterest_TV);


        Intent intent = getIntent();
        String loanString = intent.getStringExtra(MainActivity.LOAN_KEY);
        String monthlyPayment = intent.getStringExtra(MainActivity.MONTHLY_KEY);
        Gson gson = new Gson();
        loan = gson.fromJson(loanString, AutoLoan.class);

        fill();
    }

    public void fill() {
        monthlyTV.setText("$" + String.format("%.02f",loan.getMonthlyPayment()));

        vPrice.setText("$" + String.format("%.02f",loan.getPrice()));
        vDown.setText("$" + String.format("%.02f",loan.getDownPayment()));
        vTax.setText("$" + String.format("%.02f",loan.getSalesTax()));
        vTotal.setText("$" + String.format("%.02f",loan.getTotalCost()));
        vLoanAmt.setText("$" + String.format("%.02f",loan.getLoanAmount()));
        vInterest.setText("$" + String.format("%.02f",loan.getInterestAmount()));
    }

    public void goToMain(View v) {
        finish();
    }
}