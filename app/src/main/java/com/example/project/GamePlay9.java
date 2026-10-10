package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GamePlay9 extends AppCompatActivity {

    private Button btnBackCategory;
    private TextView tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;

    private int selectedSlotIndex = 0;
    private final String[] userAnswers = {"[ ช่องที่ 1 ]", "[ ช่องที่ 2 ]", "[ ช่องที่ 3 ]"};

    private int hintCount = 0;
    private final List<Button> wrongOptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play9);

        initViews();
        initWrongOptions();
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

    private void initWrongOptions() {
        wrongOptions.clear();
        wrongOptions.add(btnOption2);
        wrongOptions.add(btnOption5);
        wrongOptions.add(btnOption6);
    }

    private void useHint() {
        if (hintCount >= 3 || wrongOptions.isEmpty()) {
            Toast.makeText(this, "ใช้คำใบ้ครบ 3 ครั้งแล้ว (เหลือเฉพาะคำตอบที่ถูกต้อง)", Toast.LENGTH_SHORT).show();
            return;
        }

        Button btnToRemove = wrongOptions.remove(0);
        if (btnToRemove != null) {
            btnToRemove.setEnabled(false);
            btnToRemove.setAlpha(0.3f);
        }

        hintCount++;
        Toast.makeText(this, "ตัดตัวเลือกที่ผิดออกแล้ว 1 ข้อ (ใช้คำใบ้ไป " + hintCount + "/3)", Toast.LENGTH_SHORT).show();
    }

    private void setupListeners() {
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay9.this, Catagory.class);
                startActivity(intent);
                finish();
            });
        }

        if (btnOption1 != null) btnOption1.setOnClickListener(v -> selectOption(btnOption1.getText().toString()));
        if (btnOption2 != null) btnOption2.setOnClickListener(v -> selectOption(btnOption2.getText().toString()));
        if (btnOption3 != null) btnOption3.setOnClickListener(v -> selectOption(btnOption3.getText().toString()));
        if (btnOption4 != null) btnOption4.setOnClickListener(v -> selectOption(btnOption4.getText().toString()));
        if (btnOption5 != null) btnOption5.setOnClickListener(v -> selectOption(btnOption5.getText().toString()));
        if (btnOption6 != null) btnOption6.setOnClickListener(v -> selectOption(btnOption6.getText().toString()));

        if (tvQuestionCode != null) {
            tvQuestionCode.setOnClickListener(v -> resetAnswers());
        }

        if (btnCheck != null) {
            btnCheck.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay9.this, SolutionDialog9.class);
                startActivity(intent);
            });
        }

        if (btnHint != null) {
            btnHint.setOnClickListener(v -> showHintDialog());
        }

        if (btnNext != null) {
            btnNext.setOnClickListener(v -> goToNextQuestion());
        }
    }

    private void selectOption(String selectedValue) {
        userAnswers[selectedSlotIndex] = selectedValue;
        selectedSlotIndex = (selectedSlotIndex + 1) % 3;
        updateCodeDisplay();
    }

    private void resetAnswers() {
        userAnswers[0] = "[ ช่องที่ 1 ]";
        userAnswers[1] = "[ ช่องที่ 2 ]";
        userAnswers[2] = "[ ช่องที่ 3 ]";
        selectedSlotIndex = 0;
        updateCodeDisplay();
    }

    private void updateCodeDisplay() {
        String codeTemplate =
                "-- ซ่อนเลขบัตรประชาชนให้แสดงเฉพาะ หลักท้าย\n" +
                        "ALTER TABLE customer_info\n\n" +
                        "MODIFY COLUMN national_id " + userAnswers[0] + "\n\n" +
                        "WITH (FUNCTION = " + userAnswers[1] + ");\n\n\n" +
                        "-- กำหนดสิทธิ์ตาม Least Privilege ให้ดูเฉพาะข้อมูลที่จำเป็น\n" +
                        userAnswers[2] + " SELECT ON customer_info TO 'app_read_role';";

        if (tvQuestionCode != null) {
            tvQuestionCode.setText(codeTemplate);
        }
    }

    private void showHintDialog() {
        if (hintCount >= 3) {
            Toast.makeText(this, "ใช้คำใบ้ครบ 3 ครั้งแล้ว (เหลือเฉพาะคำตอบที่ถูกต้อง)", Toast.LENGTH_SHORT).show();
            return;
        }
        HintDialog9 hintDialog = new HintDialog9(GamePlay9.this, this::useHint);
        hintDialog.show();
    }

    private void goToNextQuestion() {
        Intent intent = new Intent(GamePlay9.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}