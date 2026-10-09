package com.example.project; // เปลี่ยนให้ตรงกับ package name ของโปรเจกต์คุณ

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SolutionDialog8 extends AppCompatActivity {

    private Button btnBackCategory;
    private Button btnDone;
    private TextView tvSolutionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_dialog8);

        initViews();
        setupListeners();
        displaySolutionCode();
    }

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        btnDone = findViewById(R.id.btnDone);
        tvSolutionCode = findViewById(R.id.tvSolutionCode);
    }

    private void setupListeners() {
        // ปุ่มย้อนกลับ
        btnBackCategory.setOnClickListener(v -> finish());

        // ปุ่มเข้าใจแล้ว / ไปข้อถัดไป (GamePlay9)
        btnDone.setOnClickListener(v -> {
            Intent intent = new Intent(SolutionDialog8.this, GamePlay9.class);
            startActivity(intent);
            finish();
        });
    }

    private void displaySolutionCode() {
        String fullSolutionCode =
                "-- สร้างคำสั่งที่ปลอดภัยสำหรับการตรวจสอบการเข้าสู่ระบบ\n" +
                        "PREPARE login_stmt FROM\n\n" +
                        "'SELECT role, username FROM customer WHERE email = ? AND password_hash = ?';\n\n\n" +
                        "-- รับค่าที่ได้จากผู้ใช้มาเก็บไว้ในตัวแปร\n" +
                        "SET @user_email = 'user@example.com';\n" +
                        "SET @user_pass = 'hashed_pass_value';\n\n\n" +
                        "-- เรียกใช้คำสั่งโดยส่งค่าตัวแปรเข้าไป\n" +
                        "EXECUTE login_stmt USING @user_email, @user_pass;";

        tvSolutionCode.setText(fullSolutionCode);
    }
}