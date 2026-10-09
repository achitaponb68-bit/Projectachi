package com.example.project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HowToPlay extends AppCompatActivity {

    // ประกาศตัวแปรทั้งหมดไว้ที่ส่วนบนของคลาสเพื่อแก้เออเรอร์ Cannot resolve symbol
    private TextView tvCodeSnippet;
    private Button btnOption1, btnOption2, btnOption3;
    private Button btnCheckAnswer, btnBackMenu;

    private String selectedAnswer = ""; // เก็บค่าช้อยส์ที่เลือก

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_how_to_play);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ผูก View กับ ID ใน XML
        tvCodeSnippet = findViewById(R.id.tvCodeSnippet);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnCheckAnswer = findViewById(R.id.btnCheckAnswer);
        btnBackMenu = findViewById(R.id.btnBackMenu);

        // ตั้งค่าเมื่อคลิกเลือกช้อยส์
        if (btnOption1 != null) btnOption1.setOnClickListener(v -> selectOption(btnOption1, "\"Hello\""));
        if (btnOption2 != null) btnOption2.setOnClickListener(v -> selectOption(btnOption2, "\"Hi\""));
        if (btnOption3 != null) btnOption3.setOnClickListener(v -> selectOption(btnOption3, "\"World\""));

        // ปุ่มตรวจคำตอบ
        if (btnCheckAnswer != null) {
            btnCheckAnswer.setOnClickListener(v -> {
                if (selectedAnswer.isEmpty()) {
                    Toast.makeText(this, "กรุณาเลือกคำตอบก่อนกดตรวจครับ", Toast.LENGTH_SHORT).show();
                } else if (selectedAnswer.equals("\"Hello\"")) {
                    Toast.makeText(this, "ถูกต้อง! คุณพร้อมเล่นเกมแล้ว 🎉", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "ยังไม่ถูกต้อง ลองเลือกคำตอบใหม่อีกครั้งนะ", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // ปุ่มกลับหน้าเมนู
        if (btnBackMenu != null) {
            btnBackMenu.setOnClickListener(v -> finish());
        }
    }

    private void selectOption(Button selectedBtn, String answer) {
        selectedAnswer = answer;

        resetButtonStyles();

        if (selectedBtn != null) {
            selectedBtn.setAlpha(0.6f);
        }

        // อัปเดตโค้ดแสดงคำตอบ
        if (tvCodeSnippet != null) {
            String updatedCode = "#include <stdio.h>\n\nint main() {\n\n    printf(" + answer + ");\n\n    return 0;\n}";
            tvCodeSnippet.setText(updatedCode);
        }
    }

    private void resetButtonStyles() {
        if (btnOption1 != null) btnOption1.setAlpha(1.0f);
        if (btnOption2 != null) btnOption2.setAlpha(1.0f);
        if (btnOption3 != null) btnOption3.setAlpha(1.0f);
    }
}