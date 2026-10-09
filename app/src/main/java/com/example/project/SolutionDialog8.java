package com.example.project; // เปลี่ยนให้ตรงกับ package name ของโปรเจกต์คุณ

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

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

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        btnDone = findViewById(R.id.btnDone);
    }

    private void setupListeners() {
        // ปุ่มย้อนกลับ
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> finish());
        }

        // ปุ่มเข้าใจแล้ว / ไปข้อถัดไป (GamePlay9)
        if (btnDone != null) {
            btnDone.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog8.this, GamePlay9.class);
                startActivity(intent);
                finish();
            });
        }
    }
}
