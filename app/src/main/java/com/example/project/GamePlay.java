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

public class GamePlay extends AppCompatActivity {

    private Button btnBackCategory;
    private TextView tvQuestionNumber, tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String category = getIntent().getStringExtra("category");
        String difficulty = getIntent().getStringExtra("difficulty");

        // ผูก View
        btnBackCategory = findViewById(R.id.btnBackCategory);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
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

        // ปุ่มย้อนกลับ
        btnBackCategory.setOnClickListener(v -> finish());

        // ปุ่มตรวจคำตอบ (เปิด SolutionDialog1)
        btnCheck.setOnClickListener(v -> {
            Intent intent = new Intent(GamePlay.this, SolutionDialog1.class);
            startActivity(intent);
        });

        // ปุ่มคำใบ้ (แสดง HintDialog1)
        btnHint.setOnClickListener(v -> {
            HintDialog1 hintDialog = new HintDialog1(GamePlay.this, null);
            hintDialog.show();
        });

        // ปุ่มไปข้อถัดไป (เปิด GamePlay2)
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(GamePlay.this, GamePlay2.class);
            intent.putExtra("category", category);
            intent.putExtra("difficulty", difficulty);
            startActivity(intent);
        });
    }
}
