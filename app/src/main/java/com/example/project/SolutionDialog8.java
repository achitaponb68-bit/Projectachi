package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SolutionDialog8 extends AppCompatActivity {

    private Button btnBackCategory;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_dialog8);

        initViews();
        setupListeners();
    }

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        btnDone = findViewById(R.id.btnDone);
    }

    private void setupListeners() {
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog8.this, Catagory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        if (btnDone != null) {
            btnDone.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog8.this, GamePlay9.class);
                startActivity(intent);
                finish();
            });
        }
    }
}