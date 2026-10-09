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

        // ปุ่มตัวเลือกคำตอบ
        btnOption1.setOnClickListener(v -> selectOption(btnOption1.getText().toString()));
        btnOption2.setOnClickListener(v -> selectOption(btnOption2.getText().toString()));
        btnOption3.setOnClickListener(v -> selectOption(btnOption3.getText().toString()));
        btnOption4.setOnClickListener(v -> selectOption(btnOption4.getText().toString()));
        btnOption5.setOnClickListener(v -> selectOption(btnOption5.getText().toString()));
        btnOption6.setOnClickListener(v -> selectOption(btnOption6.getText().toString()));

        // ปุ่มตรวจคำตอบ
        btnCheck.setOnClickListener(v -> checkAnswers());

        // ปุ่มคำใบ้
        btnHint.setOnClickListener(v -> showHintDialog());

        // ปุ่มข้อถัดไป / จบหมวดหมู่
        btnNext.setOnClickListener(v -> goToNextQuestion());
    }

    private void selectOption(String selectedValue) {
        userAnswers[selectedSlotIndex] = selectedValue;

        // ขยับไปช่องถัดไปอัตโนมัติ (0 -> 1 -> 2 -> 0)
        selectedSlotIndex = (selectedSlotIndex + 1) % 3;

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

        tvQuestionCode.setText(codeTemplate);
    }

    // ตรวจคำตอบ
    private void checkAnswers() {
        for (int i = 0; i < 3; i++) {
            if (userAnswers[i].startsWith("[ ช่องที่")) {
                Toast.makeText(this, "กรุณาเลือกคำตอบให้ครบทุกช่องก่อนครับ", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        boolean isCorrect = true;
        for (int i = 0; i < 3; i++) {
            if (!userAnswers[i].equals(correctAnswers[i])) {
                isCorrect = false;
                break;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (isCorrect) {
            builder.setTitle("🏆 ยินดีด้วย! คุณผ่านหมวด SQL แล้ว")
                    .setMessage("คำสั่งถูกต้องครบถ้วน!\n- MASKED WITH (FUNCTION = partial(...)) ช่วยซ่อนข้อมูลความลับ\n- GRANT ใช้กำหนดสิทธิ์เท่าที่จำเป็น (Least Privilege)")
                    .setCancelable(false)
                    .setPositiveButton("กลับสู่หน้าหลัก", (dialog, which) -> goToNextQuestion())
                    .setNegativeButton("ปิด", null);
        } else {
            builder.setTitle("❌ ยังไม่ถูกต้อง")
                    .setMessage("คำตอบยังไม่ถูกต้อง ลองตรวจสอบไวยากรณ์ Masking และคีย์เวิร์ดการให้สิทธิ์ดูนะครับ")
                    .setPositiveButton("ลองใหม่", null)
                    .setNeutralButton("ดูคำใบ้ 💡", (dialog, which) -> showHintDialog());
        }
        builder.create().show();
    }

    // แสดงคำใบ้
    private void showHintDialog() {
        new AlertDialog.Builder(this)
                .setTitle("💡 คำใบ้ (Hint)")
                .setMessage("1. คำสั่งสำหรับทำ Dynamic Data Masking จะใช้คีย์เวิร์ด MASKED\n" +
                        "2. ฟังก์ชันที่ใช้แสดงผลบางส่วนและซ่อนตัวอักษรด้านหน้าคือ partial(prefix, padding, suffix)\n" +
                        "3. คำสั่งมอบสิทธิ์ในการเข้าถึงข้อมูลตารางให้กับ Role คือ GRANT")
                .setPositiveButton("เข้าใจแล้ว", null)
                .create()
                .show();
    }

    // จบด่าน / กลับหน้าเลือกหมวดหมู่
    private void goToNextQuestion() {
        finish(); // ปิดหน้าจบด่านเพื่อกลับสู่หน้าเมนูหลัก
    }
}