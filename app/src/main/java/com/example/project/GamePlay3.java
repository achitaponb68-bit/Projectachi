package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GamePlay3 extends AppCompatActivity {

    private Button btnBackCategory;
    private TextView tvLanguageBadge, tvDifficultyBadge, tvQuestionNumber;
    private TextView tvTitle, tvSubTitle, tvQuestionHeader, tvQuestionDescription, tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // 1. กำหนด setContentView ไปยัง activity_game_play3
        setContentView(R.layout.activity_game_play3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String category = getIntent().getStringExtra("category");
        String difficulty = getIntent().getStringExtra("difficulty");

        // 2. ผูก View จาก activity_game_play3.xml
        btnBackCategory = findViewById(R.id.btnBackCategory);
        tvLanguageBadge = findViewById(R.id.tvLanguageBadge);
        tvDifficultyBadge = findViewById(R.id.tvDifficultyBadge);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvTitle = findViewById(R.id.tvTitle);
        tvSubTitle = findViewById(R.id.tvSubTitle);
        tvQuestionHeader = findViewById(R.id.tvQuestionHeader);
        tvQuestionDescription = findViewById(R.id.tvQuestionDescription);
        tvQuestionCode = findViewById(R.id.tvQuestionCode);

        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnOption5 = findViewById(R.id.btnOption5);
        btnOption6 = findViewById(R.id.btnOption6);

        btnCheck = findViewById(R.id.btnCheck);
        btnHint = findViewById(R.id.btnHint);
        btnNext = findViewById(R.id.btnNext);

        // 3. ปุ่มย้อนกลับเลือกหมวดหมู่
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> finish());
        }

        // 4. ปุ่มตรวจคำตอบ (เปิด SolutionDialog3)
        if (btnCheck != null) {
            btnCheck.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay3.this, SolutionDialog3.class);
                startActivity(intent);
            });
        }

        // 5. ปุ่มคำใบ้ข้อ 3 (เปิด HintDialog3)
        if (btnHint != null) {
            btnHint.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay3.this, HintDialog3.class);
                startActivity(intent);
            });
        }

        // 6. ปุ่มไปข้อถัดไป (สำหรับข้อ Hard เปลี่ยนเป็น จบด่าน แล้วไปหน้าหลัก)
        if (btnNext != null) {
            btnNext.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay3.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }
    }
}