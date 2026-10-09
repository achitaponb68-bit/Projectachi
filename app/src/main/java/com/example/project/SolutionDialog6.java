package com.example.project; // ตรงตาม package name ของโปรเจกต์คุณ

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SolutionDialog6 extends AppCompatActivity {

    private Button btnBackCategory;
    private Button btnDone;
    private TextView tvSolutionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_dialog6);

        initViews();
        setupListeners();
    }

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        btnDone = findViewById(R.id.btnDone);
        tvSolutionCode = findViewById(R.id.tvSolutionCode);
    }

    private void setupListeners() {
        // ปุ่มกลับเมนู (ปิดหน้าปัจจุบันและหน้า GamePlay ก่อนหน้าได้ถ้าใช้ Intent flag,
        // สำหรับที่นี่ใช้ finish() เพื่อปิดหน้านี้)
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> finish());
        }

        // ปุ่มเข้าใจแล้ว / ปิดหน้าเฉลย
        if (btnDone != null) {
            btnDone.setOnClickListener(v -> finish());
        }
    }
}
