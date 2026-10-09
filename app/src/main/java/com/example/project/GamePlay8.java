package com.example.project; // ตรงกับ package name ในโปรเจกต์ของคุณ

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GamePlay8 extends AppCompatActivity {

    // UI Components
    private Button btnBackCategory;
    private TextView tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;

    // Logic Variables
    private int selectedSlotIndex = 0; // 0 = ช่องที่ 1, 1 = ช่องที่ 2, 2 = ช่องที่ 3
    private final String[] userAnswers = {"[ ช่องที่ 1 ]", "[ ช่องที่ 2 ]", "[ ช่องที่ 3 ]"};

    // คำตอบที่ถูกต้องสำหรับโจทย์ข้อนี้
    // ช่องที่ 1: ? (Placeholder)
    // ช่องที่ 2: ? (Placeholder)
    // ช่องที่ 3: @user_email, @user_pass (ส่งตัวแปรเข้าไปใน EXECUTE USING)
    private final String[] correctAnswers = {
            "?",
            "?",
            "@user_email, @user_pass"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play8);

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

        // ปุ่มข้อถัดไป
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
                "-- สร้างคำสั่งที่ปลอดภัยสำหรับการตรวจสอบการเข้าสู่ระบบ\n" +
                        "PREPARE login_stmt FROM\n\n" +
                        "'SELECT role, username FROM customer WHERE email = " + userAnswers[0] + " AND password_hash = " + userAnswers[1] + "';\n\n\n" +
                        "-- รับค่าที่ได้จากผู้ใช้มาเก็บไว้ในตัวแปร\n" +
                        "SET @user_email = 'user@example.com';\n" +
                        "SET @user_pass = 'hashed_pass_value';\n\n\n" +
                        "-- เรียกใช้คำสั่งโดยส่งค่าตัวแปรเข้าไป\n" +
                        "EXECUTE login_stmt USING " + userAnswers[2] + ";";

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
            builder.setTitle("🎉 ถูกต้องแล้ว!")
                    .setMessage("คำสั่ง Prepared Statement ถูกต้อง! ช่วยป้องกัน SQL Injection โดยใช้ ? เป็นตัวแทนค่าพารามิเตอร์")
                    .setCancelable(false)
                    .setPositiveButton("ข้อถัดไป ➔", (dialog, which) -> goToNextQuestion())
                    .setNegativeButton("ปิด", null);
        } else {
            builder.setTitle("❌ ยังไม่ถูกต้อง")
                    .setMessage("คำตอบยังไม่ถูกต้อง ลองใช้เครื่องหมาย '?' แทนพารามิเตอร์ และส่งตัวแปรผ่าน USING ดูนะครับ")
                    .setPositiveButton("ลองใหม่", null)
                    .setNeutralButton("ดูคำใบ้ 💡", (dialog, which) -> showHintDialog());
        }
        builder.create().show();
    }

    // แสดงคำใบ้
    private void showHintDialog() {
        new AlertDialog.Builder(this)
                .setTitle("💡 คำใบ้ (Hint)")
                .setMessage("1. ใน PREPARE Statement เราใช้เครื่องหมาย '?' สำหรับเป็นตัวแทนของพารามิเตอร์ที่จะนำมาแทนที่ทีหลัง\n" +
                        "2. ในคำสั่ง EXECUTE ... USING เราต้องส่งตัวแปรตามลำดับเครื่องหมาย '?' คือ @user_email, @user_pass")
                .setPositiveButton("เข้าใจแล้ว", null)
                .create()
                .show();
    }

    // ไปข้อถัดไป (GamePlay9)
    private void goToNextQuestion() {
        Intent intent = new Intent(GamePlay8.this, GamePlay9.class);
        startActivity(intent);
        finish();
    }
}