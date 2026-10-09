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

    private TextView tvHeader, tvQuestionNumber, tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6 ;
    private Button btnHint, btnNext;


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
        tvHeader = findViewById(R.id.btnBackCategory);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionCode = findViewById(R.id.tvQuestionCode);

        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnOption5 = findViewById(R.id.btnOption5);
        btnOption6 = findViewById(R.id.btnOption6);

        btnHint = findViewById(R.id.btnHint);
        btnNext = findViewById(R.id.btnNext);

        // คำใบ้ข้อ 6 (HintDialog6)
        btnHint.setOnClickListener(v -> {
            Intent intent = new Intent(GamePlay.this, HintDialog6.class);
            startActivity(intent);
        });

        // ไปข้อถัดไป (GamePlay7)
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(GamePlay.this, GamePlay2.class);
            intent.putExtra("category", category);
            intent.putExtra("difficulty", difficulty);
            startActivity(intent);
        });


    }
}