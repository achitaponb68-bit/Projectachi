package com.example.project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Difficulty_SQL extends AppCompatActivity {

    // ประกาศตัวแปร UI
    private Button btnBack, btnStartGame;
    private LinearLayout btnEasy, btnMedium, btnHard;
    private TextView tvStatusEasy, tvStatusMedium, tvStatusHard;
    private TextView tvSelectedDifficultyText;

    private String selectedDifficulty = "Easy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_sql);

        initViews();
        setupListeners();

        // เลือกด่านเริ่มต้นเป็น Easy
        selectDifficulty("Easy");
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnStartGame = findViewById(R.id.btnStartGame);
        btnEasy = findViewById(R.id.btnEasy);
        btnMedium = findViewById(R.id.btnMedium);
        btnHard = findViewById(R.id.btnHard);

        tvStatusEasy = findViewById(R.id.tvStatusEasy);
        tvStatusMedium = findViewById(R.id.tvStatusMedium);
        tvStatusHard = findViewById(R.id.tvStatusHard);
        tvSelectedDifficultyText = findViewById(R.id.tvSelectedDifficultyText);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnEasy.setOnClickListener(v -> selectDifficulty("Easy"));
        btnMedium.setOnClickListener(v -> selectDifficulty("Medium"));
        btnHard.setOnClickListener(v -> selectDifficulty("Hard"));

        btnStartGame.setOnClickListener(v -> {
            Intent intent;
            if ("Medium".equals(selectedDifficulty)) {
                intent = new Intent(Difficulty_SQL.this, GamePlay8.class);
            } else if ("Hard".equals(selectedDifficulty)) {
                intent = new Intent(Difficulty_SQL.this, GamePlay9.class);
            } else {
                intent = new Intent(Difficulty_SQL.this, GamePlay7.class);
            }
            intent.putExtra("LANGUAGE", "SQL");
            intent.putExtra("DIFFICULTY_LEVEL", selectedDifficulty);
            startActivity(intent);
        });
    }

    private void selectDifficulty(String level) {
        selectedDifficulty = level;

        // ซ่อนสถานะทั้งหมดก่อน
        if (tvStatusEasy != null) tvStatusEasy.setVisibility(View.GONE);
        if (tvStatusMedium != null) tvStatusMedium.setVisibility(View.GONE);
        if (tvStatusHard != null) tvStatusHard.setVisibility(View.GONE);

        // แสดงสถานะการเลือกของระดับที่คลิก
        if ("Easy".equals(level)) {
            if (tvStatusEasy != null) tvStatusEasy.setVisibility(View.VISIBLE);
            if (tvSelectedDifficultyText != null) tvSelectedDifficultyText.setText("⚡ เลิอด่านง่ายแล้ว");
        } else if ("Medium".equals(level)) {
            if (tvStatusMedium != null) tvStatusMedium.setVisibility(View.VISIBLE);
            if (tvSelectedDifficultyText != null) tvSelectedDifficultyText.setText("⚡ เลิอด่านปานกลางแล้ว");
        } else if ("Hard".equals(level)) {
            if (tvStatusHard != null) tvStatusHard.setVisibility(View.VISIBLE);
            if (tvSelectedDifficultyText != null) tvSelectedDifficultyText.setText("⚡ เลิอด่านยากแล้ว");
        }
    }
}