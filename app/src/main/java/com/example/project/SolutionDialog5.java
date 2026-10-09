package com.example.project;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SolutionDialog5 extends AppCompatActivity {

    private TextView tvCodeSolution;
    private Button btnBackMenu, btnNextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_solution_dialog5);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ผูก View กับ ID ใน XML
        tvCodeSolution = findViewById(R.id.tvCodeSolution);
        btnBackMenu = findViewById(R.id.btnBackMenu);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);

        // 1. ปุ่มกลับเมนูหลัก
        if (btnBackMenu != null) {
            btnBackMenu.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog5.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        // 2. ปุ่มไปข้อถัดไป (เปิด GamePlay6)
        if (btnNextQuestion != null) {
            btnNextQuestion.setOnClickListener(v -> {
                Toast.makeText(this, "กำลังไปยังโจทย์ข้อถัดไป...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SolutionDialog5.this, GamePlay6.class);
                startActivity(intent);
                finish();
            });
        }
    }
}