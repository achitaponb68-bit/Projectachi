package com.example.project; // เปลี่ยนให้ตรงกับ package name ของโปรเจกต์คุณ

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SolutionDialog9 extends AppCompatActivity {

    private Button btnBackCategory;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_dialog9);

        initViews();
        setupListeners();
    }

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        btnDone = findViewById(R.id.btnDone);
    }

    private void setupListeners() {
        // ปุ่มย้อนกลับ
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> finish());
        }

        // ปุ่มเข้าใจแล้ว / ปิดหน้าเฉลยกลับหน้าหลัก
        if (btnDone != null) {
            btnDone.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog9.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }
    }
}
