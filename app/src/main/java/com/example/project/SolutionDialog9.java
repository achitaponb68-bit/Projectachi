package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SolutionDialog9: คลาส Activity แสดงหน้าเฉลยและคำอธิบายฉบับเต็มของด่านที่ 9 (ด่านสุดท้ายของหมวด SQL)
 */
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

    /**
     * ค้นหาและเชื่อมโยง View จากไฟล์ XML
     */
    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        btnDone = findViewById(R.id.btnDone);
    }

    /**
     * ตั้งค่า Event Listener ให้กับปุ่มในหน้าเฉลย
     */
    private void setupListeners() {
        // ปุ่มย้อนกลับไปหน้าหมวดหมู่หลัก (Catagory)
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog9.this, Catagory.class);
                // ใช้ Flag เพื่อล้าง Stack ของ Activity เก่า ป้องกัน Memory Leak
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        // ปุ่มไปยังหน้าสรุปผลการเล่น (Summary)
        if (btnDone != null) {
            btnDone.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog9.this, Summary.class);
                intent.putExtra("category", "SQL");
                intent.putExtra("nextButtonLabel", "เลือกหมวดหมู่");
                startActivity(intent);
                finish();
            });
        }
    }
}