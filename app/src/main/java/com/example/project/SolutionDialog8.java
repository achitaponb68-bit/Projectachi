package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SolutionDialog8: คลาส Activity แสดงหน้าเฉลยและคำอธิบายฉบับเต็มของด่านที่ 8
 */
public class SolutionDialog8 extends AppCompatActivity {

    private Button btnBackCategory;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_dialog8);

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
                Intent intent = new Intent(SolutionDialog8.this, Catagory.class);
                // ใช้ Flag เพื่อล้าง Stack ของ Activity เก่า ป้องกัน Memory Leak
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        // ปุ่มไปยังด่านถัดไป (GamePlay9)
        if (btnDone != null) {
            btnDone.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog8.this, GamePlay9.class);
                startActivity(intent);
                finish();
            });
        }
    }
}