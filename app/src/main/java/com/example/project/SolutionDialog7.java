package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SolutionDialog7: คลาส Activity แสดงหน้าเฉลยและคำอธิบายฉบับเต็มของด่านที่ 7
 */
public class SolutionDialog7 extends AppCompatActivity {

    private Button btnBackMenu;
    private Button btnNextQuestion;
    private TextView tvSolutionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_dialog7);

        initViews();
        setupListeners();
        displaySolutionCode();
    }

    /**
     * ค้นหาและเชื่อมโยง View จากไฟล์ XML
     */
    private void initViews() {
        btnBackMenu = findViewById(R.id.btnBackMenu);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        tvSolutionCode = findViewById(R.id.tvCodeSolution);
    }

    /**
     * ตั้งค่า Event Listener ให้กับปุ่มในหน้าเฉลย
     */
    private void setupListeners() {
        // ปุ่มย้อนกลับไปหน้าหมวดหมู่หลัก (Catagory)
        if (btnBackMenu != null) {
            btnBackMenu.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog7.this, Catagory.class);
                // ใช้ Flag เพื่อล้าง Stack ของ Activity เก่า ป้องกัน Memory Leak
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        // ปุ่มไปยังด่านถัดไป (GamePlay8)
        if (btnNextQuestion != null) {
            btnNextQuestion.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog7.this, GamePlay8.class);
                startActivity(intent);
                finish();
            });
        }
    }

    /**
     * แสดงผลโค้ดเฉลยแบบสมบูรณ์บน TextView
     */
    private void displaySolutionCode() {
        String fullSolutionCode =
                "SELECT username, role\n" +
                        "FROM users\n" +
                        "WHERE username = 'john_doe'\n" +
                        "AND\n" +
                        "status = 'active';";

        if (tvSolutionCode != null) {
            tvSolutionCode.setText(fullSolutionCode);
        }
    }
}