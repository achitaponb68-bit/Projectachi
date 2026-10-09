package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Difficulty_C extends AppCompatActivity {

    // ประกาศตัวแปรสำหรับองค์ประกอบใน UI
    private Button btnBack, btnStartGame;
    private LinearLayout btnEasy, btnMedium, btnHard;
    private TextView tvStatusEasy, tvStatusMedium, tvStatusHard;
    private TextView tvSelectedDifficultyText;

    private String selectedDifficulty = "Easy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_c);

        initViews();
        setupListeners();

        // 1. ตั้งค่าการเลือกเริ่มต้นเป็น Easy
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

        // เมื่อกดเลือกลำดับด่านต่างๆ
        btnEasy.setOnClickListener(v -> selectDifficulty("Easy"));
        btnMedium.setOnClickListener(v -> selectDifficulty("Medium"));
        btnHard.setOnClickListener(v -> selectDifficulty("Hard"));

        btnStartGame.setOnClickListener(v -> {
            Intent intent;
            if ("Medium".equals(selectedDifficulty)) {
                intent = new Intent(Difficulty_C.this, GamePlay2.class);
            } else if ("Hard".equals(selectedDifficulty)) {
                intent = new Intent(Difficulty_C.this, GamePlay3.class);
            } else {
                intent = new Intent(Difficulty_C.this, GamePlay.class);
            }
            intent.putExtra("DIFFICULTY_LEVEL", selectedDifficulty);
            startActivity(intent);
        });
    }

    /**
     * ฟังก์ชันเปลี่ยนสถานะด่านที่เลือก
     */
    private void selectDifficulty(String level) {
        selectedDifficulty = level;

        // ซ่อนคำว่า "เลือกแล้ว" บนด่านทั้งหมดก่อน
        if (tvStatusEasy != null) tvStatusEasy.setVisibility(View.GONE);
        if (tvStatusMedium != null) tvStatusMedium.setVisibility(View.GONE);
        if (tvStatusHard != null) tvStatusHard.setVisibility(View.GONE);

        // แสดงคำว่า "เลือกแล้ว" เฉพาะด่านที่เลือก + อัปเดตข้อความกล่องปุ่มด้านล่าง
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