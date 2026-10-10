package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SolutionDialog6 extends AppCompatActivity {

    private Button btnBackCategory;
    private Button btnDone;
    private TextView tvSolutionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_dialog6);

        initViews();
        setupListeners();
    }

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        btnDone = findViewById(R.id.btnDone);
        tvSolutionCode = findViewById(R.id.tvSolutionCode);
    }

    private void setupListeners() {
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog6.this, Catagory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        if (btnDone != null) {
            btnDone.setOnClickListener(v -> finish());
        }
    }
}