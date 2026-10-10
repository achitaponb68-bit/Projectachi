package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SolutionDialog7 extends AppCompatActivity {

    private Button btnBackMenu;
    private Button btnNextQuestion;
    private TextView tvSolutionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_dialog7);

        initViews();
        setupListeners();
        displaySolutionCode();
    }

    private void initViews() {
        btnBackMenu = findViewById(R.id.btnBackMenu);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        tvSolutionCode = findViewById(R.id.tvCodeSolution);
    }

    private void setupListeners() {
        if (btnBackMenu != null) {
            btnBackMenu.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog7.this, Catagory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        if (btnNextQuestion != null) {
            btnNextQuestion.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog7.this, GamePlay8.class);
                startActivity(intent);
                finish();
            });
        }
    }

    private void displaySolutionCode() {
        String fullSolutionCode =
                "SELECT username, role\n" +
                        "FROM users\n" +
                        "WHERE username = 'john_doe'\n" +
                        "AND\n" +
                        "status = 'active';";

        if (tvSolutionCode != null) {
            tvSolutionCode.setText(fullSolutionCode);
        }
    }
}