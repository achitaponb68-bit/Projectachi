package com.example.project;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HintDialog5 extends AppCompatActivity {

    private Button btnUse5050, btnCloseHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hint_dialog5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. ผูก View กับ ID จากไฟล์ activity_hint_dialog5.xml
        btnUse5050 = findViewById(R.id.btnUse5050);
        btnCloseHint = findViewById(R.id.btnCloseHint);

        // 2. เมื่อกดปุ่ม "ใช้งานเพื่อตัดตัวเลือกที่ผิด"
        btnUse5050.setOnClickListener(v -> {
            setResult(RESULT_OK); // ส่งค่า RESULT_OK กลับไปบอกหน้า GamePlay ว่าตกลงใช้คำใบ้
            finish();              // ปิดหน้า Dialog
        });

        // 3. เมื่อกดปุ่ม "ยกเลิก / ปิด"
        btnCloseHint.setOnClickListener(v -> {
            setResult(RESULT_CANCELED); // ส่งค่า canceled เพื่อยกเลิก
            finish();                   // ปิดหน้า Dialog
        });
    }
}