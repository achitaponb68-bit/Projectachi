package com.example.project; // เปลี่ยนให้ตรงกับ package ในโปรเจกต์ของคุณ

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GamePlay9 extends AppCompatActivity {

    // UI Components
    private Button btnBackCategory;
    private TextView tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;

    // Logic Variables
    private int selectedSlotIndex = 0; // 0 = ช่องที่ 1, 1 = ช่องที่ 2, 2 = ช่องที่ 3
    private final String[] userAnswers = {"[ ช่องที่ 1 ]", "[ ช่องที่ 2 ]", "[ ช่องที่ 3 ]"};

    // คำตอบที่ถูกต้อง
    // ช่องที่ 1: MASKED
    // ช่องที่ 2: partial(0,"XXXXXXXXX",4)
    // ช่องที่ 3: GRANT
    private final String[] correctAnswers = {
            "MASKED",
            "partial(0,\"XXXXXXXXX\",4)",
            "GRANT"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play9);

        initViews();
        setupListeners();
        updateCodeDisplay();
    }

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
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
    }

    private void setupListeners() {
        // ปุ่มย้อนกลับ
        btnBackCategory.setOnClickListener(v -> finish());

        // ปุ่มตัวเลือกคำตอบ (ปิดการใช้งานสำหรับหน้า SQL)
        if (btnOption1 != null) btnOption1.setOnClickListener(null);
        if (btnOption2 != null) btnOption2.setOnClickListener(null);
        if (btnOption3 != null) btnOption3.setOnClickListener(null);
        if (btnOption4 != null) btnOption4.setOnClickListener(null);
        if (btnOption5 != null) btnOption5.setOnClickListener(null);
        if (btnOption6 != null) btnOption6.setOnClickListener(null);

        // ปุ่มตรวจคำตอบ (เชื่อมตรงไปหน้าเฉลย)
        if (btnCheck != null) {
            btnCheck.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay9.this, SolutionDialog9.class);
                startActivity(intent);
            });
        }

        // ปุ่มคำใบ้
        btnHint.setOnClickListener(v -> showHintDialog());

        // ปุ่มข้อถัดไป / จบหมวดหมู่
        btnNext.setOnClickListener(v -> goToNextQuestion());
    }



    private void updateCodeDisplay() {
        String codeTemplate =
                "-- ซ่อนเลขบัตรประชาชนให้แสดงเฉพาะ หลักท้าย\n" +
                        "ALTER TABLE customer_info\n\n" +
                        "MODIFY COLUMN national_id [ ช่องที่ 1 ]\n\n" +
                        "WITH (FUNCTION = [ ช่องที่ 2 ]);\n\n\n" +
                        "-- กำหนดสิทธิ์ตาม Least Privilege ให้ดูเฉพาะข้อมูลที่จำเป็น\n" +
                        "[ ช่องที่ 3 ] SELECT ON customer_info TO 'app_read_role';";

        tvQuestionCode.setText(codeTemplate);
    }



    // แสดงคำใบ้
    private void showHintDialog() {
        Intent intent = new Intent(GamePlay9.this, HintDialog9.class);
        startActivity(intent);
    }

    // จบด่าน / กลับหน้าเลือกหมวดหมู่
    private void goToNextQuestion() {
        Intent intent = new Intent(GamePlay9.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish(); 
    }
}