package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
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

    // คำตอบที่ถูกต้องสำหรับโจทย์ SQL ข้อนี้
    private final String[] correctAnswers = {
            "AND",
            "status",
            "'active'"
    };

    private GamePlay7() {
    }

    public static GamePlay7 createGamePlay7() {
        return new GamePlay7();
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
        btnOption1.setOnClickListener(v -> selectOption(btnOption1.getText().toString()));
        btnOption2.setOnClickListener(v -> selectOption(btnOption2.getText().toString()));
        btnOption3.setOnClickListener(v -> selectOption(btnOption3.getText().toString()));
        btnOption4.setOnClickListener(v -> selectOption(btnOption4.getText().toString()));
        btnOption5.setOnClickListener(v -> selectOption(btnOption5.getText().toString()));
        btnOption6.setOnClickListener(v -> selectOption(btnOption6.getText().toString()));

        // 3. ปุ่มตรวจคำตอบ
        btnCheck.setOnClickListener(v -> checkAnswers());

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
        // ตรวจสอบว่าผู้ใช้ตอบครบทุกช่องหรือยัง
        for (int i = 0; i < 3; i++) {
            if (userAnswers[i].startsWith("[ ช่องที่")) {
                Toast.makeText(this, "กรุณาเลือกคำตอบให้ครบทุกช่องก่อนครับ", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // ตรวจสอบว่าคำตอบถูกต้องหรือไม่
        boolean isCorrect = true;
        for (int i = 0; i < 3; i++) {
            if (!userAnswers[i].equals(correctAnswers[i])) {
                isCorrect = false;
                break;
            }
        }

        // แสดง Dialog แจ้งผลลัพธ์
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (isCorrect) {
            builder.setTitle("🎉 ถูกต้องแล้ว!")
                    .setMessage("คำสั่ง SQL ถูกต้อง ระบบตรวจสอบทั้งชื่อผู้ใช้และสถานะบัญชี active เรียบร้อย")
                    .setCancelable(false)
                    .setPositiveButton("ข้อถัดไป ➔", (dialog, which) -> goToNextQuestion())
                    .setNegativeButton("ปิด", null);
        } else {
            builder.setTitle("❌ ยังไม่ถูกต้อง")
                    .setMessage("คำตอบยังไม่ถูกต้อง ลองทบทวนเงื่อนไขหรือลองใช้คำใบ้ดูนะครับ")
                    .setPositiveButton("ลองใหม่", null)
                    .setNeutralButton("ดูคำใบ้ 💡", (dialog, which) -> showHintDialog());
        }
        builder.create().show();
    }

    // ==========================================
    // 2. ปุ่มคำใบ้ (Hint Dialog)
    // ==========================================
    private void showHintDialog() {
        new AlertDialog.Builder(this)
                .setTitle("💡 คำใบ้ (Hint)")
                .setMessage("1. ใช้คำสั่ง AND เพื่อบังคับให้ตรวจเงื่อนไขทั้งสองข้อพร้อมกัน\n" +
                        "2. คอลัมน์ที่ใช้วัดสถานะคือ status\n" +
                        "3. ค่าสถานะสำหรับบัญชีที่เปิดใช้งานคือ 'active'")
                .setPositiveButton("เข้าใจแล้ว", null)
                .create()
                .show();
    }

    // ==========================================
    // 3. ปุ่มข้อถัดไป (Next Question)
    // ==========================================
    private void goToNextQuestion() {
        // เปลี่ยน GamePlaySQL2.class ให้ตรงกับ Class ของข้อถัดไปในโปรเจกต์ของคุณ
        Intent intent = new Intent(GamePlay7.this, GamePlay7.class);
        startActivity(intent);
        finish(); // ปิดหน้าปัจจุบันเพื่อไม่ให้กด Back ย้อนกลับมาข้อเดิม
    }
}