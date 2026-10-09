package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GamePlay7 extends AppCompatActivity {

    // UI Components
    private Button btnBackCategory;
    private TextView tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;

    // Logic Variables
    private int selectedSlotIndex = 0; // 0 = ช่องที่ 1, 1 = ช่องที่ 2, 2 = ช่องที่ 3
    private final String[] userAnswers = {"[ ช่องที่ 1 ]", "[ ช่องที่ 2 ]", "[ ช่องที่ 3 ]"};

    public GamePlay7() {
        // ต้องเป็น public constructor เพื่อไม่ให้แอป crash
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play7);

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
        // 1. ปุ่มย้อนกลับ
        btnBackCategory.setOnClickListener(v -> finish());

        // 2. ปุ่มตัวเลือกคำตอบ
        if (btnOption1 != null) btnOption1.setOnClickListener(v -> selectOption(btnOption1.getText().toString()));
        if (btnOption2 != null) btnOption2.setOnClickListener(v -> selectOption(btnOption2.getText().toString()));
        if (btnOption3 != null) btnOption3.setOnClickListener(v -> selectOption(btnOption3.getText().toString()));
        if (btnOption4 != null) btnOption4.setOnClickListener(v -> selectOption(btnOption4.getText().toString()));
        if (btnOption5 != null) btnOption5.setOnClickListener(v -> selectOption(btnOption5.getText().toString()));
        if (btnOption6 != null) btnOption6.setOnClickListener(v -> selectOption(btnOption6.getText().toString()));

        // 3. ปุ่มตรวจคำตอบ 
        if (btnCheck != null) {
            btnCheck.setOnClickListener(v -> checkAnswers());
        }

        // 4. ปุ่มคำใบ้
        btnHint.setOnClickListener(v -> showHintDialog());

        // 5. ปุ่มข้อถัดไป
        btnNext.setOnClickListener(v -> goToNextQuestion());
    }

    // เติมคำตอบลงในช่องว่างปัจจุบัน
    private void selectOption(String selectedValue) {
        userAnswers[selectedSlotIndex] = selectedValue;

        // ขยับไปช่องถัดไปอัตโนมัติ (0 -> 1 -> 2 -> 0)
        selectedSlotIndex = (selectedSlotIndex + 1) % 3;

        updateCodeDisplay();
    }

    // อัปเดตการแสดงผลโค้ดบน TextView
    private void updateCodeDisplay() {
        String codeTemplate =
                "SELECT username, role\n" +
                        "FROM users\n" +
                        "WHERE username = 'john_doe'\n" +
                        userAnswers[0] + "\n" +
                        userAnswers[1] + " = " + userAnswers[2] + ";";

        tvQuestionCode.setText(codeTemplate);
    }





    // ==========================================
    // 1. ปุ่มตรวจคำตอบ (Check Answer)
    // ==========================================
    private void checkAnswers() {
        // เปลี่ยนหน้าไปที่ SolutionDialog7 ทันที
        Intent intent = new Intent(GamePlay7.this, SolutionDialog7.class);
        startActivity(intent);
    }

    // ==========================================
    // 2. ปุ่มคำใบ้ (Hint Dialog)
    // ==========================================
    private void showHintDialog() {
        Intent intent = new Intent(GamePlay7.this, HintDialog7.class);
        startActivity(intent);
    }

    // ==========================================
    // 3. ปุ่มข้อถัดไป (Next Question)
    // ==========================================
    private void goToNextQuestion() {
        Intent intent = new Intent(GamePlay7.this, GamePlay8.class);
        startActivity(intent);
    }
}
