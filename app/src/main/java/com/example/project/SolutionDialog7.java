package com.example.project; // เปลี่ยนให้ตรงกับ package ในโปรเจกต์ของคุณ

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
        tvSolutionCode = findViewById(R.id.tvSolutionCode);
    }

    private void setupListeners() {
        // ปุ่มย้อนกลับ
        if (btnBackMenu != null) {
            btnBackMenu.setOnClickListener(v -> finish());
        }

        // ปุ่มเข้าใจแล้ว / ไปข้อถัดไป (GamePlay8)
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

        tvSolutionCode.setText(fullSolutionCode);
    }
}