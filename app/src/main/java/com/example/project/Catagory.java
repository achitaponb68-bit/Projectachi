package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class  Catagory extends AppCompatActivity {

    private View btnC, btnSQL, btnJava;
    private TextView checkC, checkSQL, checkJava;
    private TextView tvSelectedCategoryName;
    private Button btnStartStage, btnBack;

    // ตัวแปรเก็บภาษาที่เลือกอยู่ปัจจุบัน (เริ่มต้นเป็นภาษา C)
    private String selectedCategory = "C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_catagory);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. ผูก View จาก XML
        btnC = findViewById(R.id.btnC);
        btnSQL = findViewById(R.id.btnSQL);
        btnJava = findViewById(R.id.btnJava);

        checkC = findViewById(R.id.checkC);
        checkSQL = findViewById(R.id.checkSQL);
        checkJava = findViewById(R.id.checkJava);

        tvSelectedCategoryName = findViewById(R.id.tvSelectedCategoryName);
        btnStartStage = findViewById(R.id.btnStartStage);
        btnBack = findViewById(R.id.btnBack);

        // 2. ตั้งค่าการเลือกเริ่มต้น (ภาษา C)
        selectCategory("C");

        // 3. Event เมื่อกดเลือกภาษา C
        btnC.setOnClickListener(v -> selectCategory("C"));

        // 4. Event เมื่อกดเลือกภาษา SQL
        btnSQL.setOnClickListener(v -> selectCategory("SQL"));

        // 5. Event เมื่อกดเลือกภาษา Java
        btnJava.setOnClickListener(v -> selectCategory("Java"));

        // 6. Event เมื่อกดปุ่มเริ่มด่านล่างสุด
        btnStartStage.setOnClickListener(v -> {
            Intent intent;
            if ("SQL".equals(selectedCategory)) {
                intent = new Intent(Catagory.this, Difficulty_SQL.class);
            } else if ("Java".equals(selectedCategory)) {
                intent = new Intent(Catagory.this, Difficulty_Java.class);
            } else {
                intent = new Intent(Catagory.this, Difficulty_C.class);
            }
            startActivity(intent);
        });

        // 7. Event ปุ่มย้อนกลับ
        btnBack.setOnClickListener(v -> finish());
    }

    /**
     * ฟังก์ชันจัดการการเลือกหมวดหมู่ภาษา
     * - แสดงติ๊กถูก (✔) ที่ภาษาที่เลือก และซ่อนที่เหลือ
     * - อัปเดตชื่อหมวดหมู่ที่เลือกด้านล่าง
     */
    private void selectCategory(String category) {
        selectedCategory = category;

        // ซ่อนไอคอนติ๊กถูกทั้งหมดก่อน
        checkC.setVisibility(View.GONE);
        checkSQL.setVisibility(View.GONE);
        checkJava.setVisibility(View.GONE);

        // แสดงติ๊กถูกเฉพาะภาษาที่เลือก + อัปเดตข้อความชื่อหมวดหมู่
        if ("C".equals(category)) {
            checkC.setVisibility(View.VISIBLE);
            if (tvSelectedCategoryName != null) {
                tvSelectedCategoryName.setText("ภาษา C");
            }
        } else if ("SQL".equals(category)) {
            checkSQL.setVisibility(View.VISIBLE);
            if (tvSelectedCategoryName != null) {
                tvSelectedCategoryName.setText("ภาษา SQL");
            }
        } else if ("Java".equals(category)) {
            checkJava.setVisibility(View.VISIBLE);
            if (tvSelectedCategoryName != null) {
                tvSelectedCategoryName.setText("ภาษา Java");
            }
        }
    }
}