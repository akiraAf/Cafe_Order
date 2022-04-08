package com.app.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewAddition;
    private CheckBox checkBoxLemon;
    private CheckBox checkBoxMilk;
    private  CheckBox checkBoxSugar;
    private Spinner spinnerCoffee;
    private Spinner spinnerTea;


    private StringBuilder builderAdditions = new StringBuilder();

    private String password;
    private String name;
    private String drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        password = intent.getStringExtra("password");


        textViewHello = findViewById(R.id.textViewHello);
        textViewAddition = findViewById(R.id.textViewChooseAddition);
        checkBoxLemon = findViewById(R.id.checkboxLemon);
        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxSugar = findViewById(R.id.checkboxSugar);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);

        String hello = String.format(getString(R.string.hello), name);
        textViewHello.setText(hello);

        drink = getString(R.string.tea);
        String addition = String.format(getString(R.string.choose_additions), drink);
        textViewAddition.setText(addition);

    }

    public void OnClickChangeDrink(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        drink = getString(R.string.tea) ;
        if(id == R.id.radioButtonTea){
            drink = getString(R.string.tea) ;
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        }

        else if(id == R.id.radioButtonCoffee){
            drink = getString(R.string.coffee);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }

        String addition = String.format(getString(R.string.choose_additions), drink);
        textViewAddition.setText(addition);
    }

    public void OnClickSendOrder(View view) {
        builderAdditions.setLength(0);
        if (checkBoxMilk.isChecked()) {
            builderAdditions.append(getString(R.string.milk)).append(" ");
        }
        if (checkBoxSugar.isChecked()) {
            builderAdditions.append(getString(R.string.sugar)).append(" ");
        }
        if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.tea))) {
            builderAdditions.append(getString(R.string.lemon)).append(" ");
        }
        String optionOfDrink = "";
        if (drink.equals(getString(R.string.tea))) {
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        } else {
            optionOfDrink = spinnerCoffee.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order), name, password, drink, optionOfDrink);
        String additions;
        if (builderAdditions.length() > 0) {
            additions = getString(R.string.need_additions) + builderAdditions.toString();
        } else {
            additions = "";
        }
        String fullOrder = order + additions;
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);
    }
}