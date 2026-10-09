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
                Intent intent = new Intent(GamePlay8.this, SolutionDialog8.class);
                startActivity(intent);
            });
        }

        // ปุ่มคำใบ้
        btnHint.setOnClickListener(v -> showHintDialog());

        // ปุ่มข้อถัดไป
        btnNext.setOnClickListener(v -> goToNextQuestion());
    }



    private void updateCodeDisplay() {
        String codeTemplate =
                "-- สร้างคำสั่งที่ปลอดภัยสำหรับการตรวจสอบการเข้าสู่ระบบ\n" +
                        "PREPARE login_stmt FROM\n\n" +
                        "'SELECT role, username FROM customer WHERE email = [ ช่องที่ 1 ] AND password_hash = [ ช่องที่ 2 ]';\n\n\n" +
                        "-- รับค่าที่ได้จากผู้ใช้มาเก็บไว้ในตัวแปร\n" +
                        "SET @user_email = 'user@example.com';\n" +
                        "SET @user_pass = 'hashed_pass_value';\n\n\n" +
                        "-- เรียกใช้คำสั่งโดยส่งค่าตัวแปรเข้าไป\n" +
                        "EXECUTE login_stmt USING [ ช่องที่ 3 ];";

        tvQuestionCode.setText(codeTemplate);
    }



    // แสดงคำใบ้
    private void showHintDialog() {
        Intent intent = new Intent(GamePlay8.this, HintDialog8.class);
        startActivity(intent);
    }

    // ไปข้อถัดไป (GamePlay9)
    private void goToNextQuestion() {
        Intent intent = new Intent(GamePlay8.this, GamePlay9.class);
        startActivity(intent);
        finish();
    }
}