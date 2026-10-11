package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Difficulty_Java extends AppCompatActivity {

    private Button btnBack, btnStartGame;
    private LinearLayout btnEasy, btnMedium, btnHard;
    private TextView tvStatusEasy, tvStatusMedium, tvStatusHard;
    private TextView tvSelectedDifficultyText;

    private String selectedDifficulty = "Easy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_java);

        initViews();
        setupListeners();

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
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> {
                Intent intent = new Intent(Difficulty_Java.this, Catagory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        btnEasy.setOnClickListener(v -> selectDifficulty("Easy"));
        btnMedium.setOnClickListener(v -> selectDifficulty("Medium"));
        btnHard.setOnClickListener(v -> selectDifficulty("Hard"));

        btnStartGame.setOnClickListener(v -> {
            Intent intent;
            if ("Medium".equals(selectedDifficulty)) {
                intent = new Intent(Difficulty_Java.this, GamePlay5.class);
            } else if ("Hard".equals(selectedDifficulty)) {
                intent = new Intent(Difficulty_Java.this, GamePlay6.class);
            } else {
                GameProgressManager.resetCategoryHints(Difficulty_Java.this, "Java");
                intent = new Intent(Difficulty_Java.this, GamePlay4.class);
            }
            intent.putExtra("LANGUAGE", "Java");
            intent.putExtra("DIFFICULTY_LEVEL", selectedDifficulty);
            startActivity(intent);
        });
    }

    private void selectDifficulty(String level) {
        selectedDifficulty = level;

        if (tvStatusEasy != null) tvStatusEasy.setVisibility(View.GONE);
        if (tvStatusMedium != null) tvStatusMedium.setVisibility(View.GONE);
        if (tvStatusHard != null) tvStatusHard.setVisibility(View.GONE);

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