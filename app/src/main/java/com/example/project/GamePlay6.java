package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GamePlay6 extends AppCompatActivity {

    // UI Components
    private Button btnBackCategory;
    private TextView tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play6); // อ้างอิงชื่อไฟล์ XML ของคุณ

        initViews();
        setupListeners();
        updateCodeDisplay();
    }

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        tvQuestionCode = findViewById(R.id.tvQuestionCode);

        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnOption5 = findViewById(R.id.btnOption5);
        btnOption6 = findViewById(R.id.btnOption6);

        btnCheck = findViewById(R.id.btnCheck);
        btnHint = findViewById(R.id.btnHint);
        btnNext = findViewById(R.id.btnNext);
    }

    private void setupListeners() {
        // ปุ่มย้อนกลับ
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> finish());
        }

        // ปุ่มตัวเลือกคำตอบ (ปิดการใช้งานตามความต้องการ "ยังไม่ควรใส่คำตอบได้")
        if (btnOption1 != null) btnOption1.setOnClickListener(null);
        if (btnOption2 != null) btnOption2.setOnClickListener(null);
        if (btnOption3 != null) btnOption3.setOnClickListener(null);
        if (btnOption4 != null) btnOption4.setOnClickListener(null);
        if (btnOption5 != null) btnOption5.setOnClickListener(null);
        if (btnOption6 != null) btnOption6.setOnClickListener(null);

        // ปุ่มตรวจคำตอบ (เชื่อมกับ activity_solution_dialog6.xml โดยตรง)
        if (btnCheck != null) {
            btnCheck.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay6.this, SolutionDialog6.class);
                startActivity(intent);
            });
        }

        // ปุ่มคำใบ้
        if (btnHint != null) {
            btnHint.setOnClickListener(v -> showHintDialog());
        }

        // ปุ่มจบด่าน (เนื่องจากเป็นข้อ 3/3 ของ Java) ไปหน้าหลัก
        if (btnNext != null) {
            btnNext.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay6.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish(); 
            });
        }
    }



    // ฟังก์ชันอัปเดตการแสดงผลโค้ด Java บน TextView
    private void updateCodeDisplay() {
        String codeTemplate =
                "import javax.xml.parsers.DocumentBuilderFactory;\n\n" +
                        "public class SafeXmlParser {\n" +
                        "    public static void secureParser(DocumentBuilderFactory dbf) throws Exception {\n" +
                        "        // ป้องกัน XXE โดยปิดการทำงานที่ไม่ปลอดภัย\n\n" +
                        "        dbf.setFeature( [ ช่องที่ 1 ] , true);\n\n" +
                        "        dbf.setFeature(\"http://xml.org/sax/features/external-general-entities\", [ ช่องที่ 2 ] );\n\n" +
                        "        dbf.setFeature(\"http://xml.org/sax/features/external-parameter-entities\", [ ช่องที่ 3 ] );\n" +
                        "    }\n" +
                        "}";

        if (tvQuestionCode != null) {
            tvQuestionCode.setText(codeTemplate);
        }
    }



    // ฟังก์ชันแสดงคำใบ้
    private void showHintDialog() {
        Intent intent = new Intent(GamePlay6.this, HintDialog6.class);
        startActivity(intent);
    }
}