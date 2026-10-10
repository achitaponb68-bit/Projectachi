package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GamePlay8 extends AppCompatActivity {

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
        setContentView(R.layout.activity_game_play8);

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
        wrongOptions.add(btnOption3);
        wrongOptions.add(btnOption4);
        wrongOptions.add(btnOption5);
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
                Intent intent = new Intent(GamePlay8.this, Catagory.class);
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
                Intent intent = new Intent(GamePlay8.this, SolutionDialog8.class);
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
                "-- สร้างคำสั่งที่ปลอดภัยสำหรับการตรวจสอบการเข้าสู่ระบบ\n" +
                        "PREPARE login_stmt FROM\n\n" +
                        "'SELECT role, username FROM customer WHERE email = " + userAnswers[0] + " AND password_hash = " + userAnswers[1] + "';\n\n\n" +
                        "-- รับค่าที่ได้จากผู้ใช้มาเก็บไว้ในตัวแปร\n" +
                        "SET @user_email = 'user@example.com';\n" +
                        "SET @user_pass = 'hashed_pass_value';\n\n\n" +
                        "-- เรียกใช้คำสั่งโดยส่งค่าตัวแปรเข้าไป\n" +
                        "EXECUTE login_stmt USING " + userAnswers[2] + ";";

        if (tvQuestionCode != null) {
            tvQuestionCode.setText(codeTemplate);
        }
    }

    private void showHintDialog() {
        if (hintCount >= 3) {
            Toast.makeText(this, "ใช้คำใบ้ครบ 3 ครั้งแล้ว (เหลือเฉพาะคำตอบที่ถูกต้อง)", Toast.LENGTH_SHORT).show();
            return;
        }
        HintDialog8 hintDialog = new HintDialog8(GamePlay8.this, this::useHint);
        hintDialog.show();
    }

    private void goToNextQuestion() {
        Intent intent = new Intent(GamePlay8.this, GamePlay9.class);
        startActivity(intent);
        finish();
    }
}