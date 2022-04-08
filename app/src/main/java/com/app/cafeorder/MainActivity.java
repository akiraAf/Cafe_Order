package com.app.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.editTextName);
        editPassword= findViewById(R.id.editTextPassword);
    }

    public void onClickCreate(View view){
        String name = editName.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        if(!name.isEmpty() && !password.isEmpty()){
            Intent intent = new Intent(this, CreateOrderActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("password", password);
            startActivity(intent);
        } else
            Toast.makeText(this, "Пароль где, мда", Toast.LENGTH_SHORT).show();

    }
}