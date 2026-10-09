package com.example.project;

import android.os.Bundle;
import android.text.Html;
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

    // Logic Variables
    private int selectedSlotIndex = 0; // 0 = ช่องที่ 1, 1 = ช่องที่ 2, 2 = ช่องที่ 3
    private String[] userAnswers = {"[ ช่องที่ 1 ]", "[ ช่องที่ 2 ]", "[ ช่องที่ 3 ]"};

    // คำตอบที่ถูกต้อง
    private final String[] correctAnswers = {
            "\"http://apache.org/xml/features/disallow-doctype-decl\"",
            "false",
            "false"
    };

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
        btnBackCategory.setOnClickListener(v -> finish());

        // ปุ่มตัวเลือกคำตอบ
        btnOption1.setOnClickListener(v -> selectOption(btnOption1.getText().toString()));
        btnOption2.setOnClickListener(v -> selectOption(btnOption2.getText().toString()));
        btnOption3.setOnClickListener(v -> selectOption(btnOption3.getText().toString()));
        btnOption4.setOnClickListener(v -> selectOption(btnOption4.getText().toString()));
        btnOption5.setOnClickListener(v -> selectOption(btnOption5.getText().toString()));
        btnOption6.setOnClickListener(v -> selectOption(btnOption6.getText().toString()));

        // ปุ่มตรวจคำตอบ
        btnCheck.setOnClickListener(v -> checkAnswers());

        // ปุ่มคำใบ้
        btnHint.setOnClickListener(v -> showHint());

        // ปุ่มข้อถัดไป
        btnNext.setOnClickListener(v -> {
            Toast.makeText(this, "กำลังไปยังข้อถัดไป...", Toast.LENGTH_SHORT).show();
            // TODO: ใส่ Intent เพื่อเปิด Activity ถัดไป
        });
    }

    // ฟังก์ชันเมื่อผู้ใช้เลือกคำตอบลงในช่องว่างปัจจุบัน
    private void selectOption(String selectedValue) {
        // เติม เครื่องหมายคำพูด ให้กรณีเป็น URL String
        if (selectedValue.startsWith("http")) {
            selectedValue = "\"" + selectedValue + "\"";
        }

        userAnswers[selectedSlotIndex] = selectedValue;

        // ขยับไปช่องถัดไปอัตโนมัติ (วนลูป 0 -> 1 -> 2 -> 0)
        selectedSlotIndex = (selectedSlotIndex + 1) % 3;

        updateCodeDisplay();
    }

    // ฟังก์ชันอัปเดตการแสดงผลโค้ด Java บน TextView
    private void updateCodeDisplay() {
        String codeTemplate =
                "import javax.xml.parsers.DocumentBuilderFactory;\n\n" +
                        "public class SafeXmlParser {\n" +
                        "    public static void secureParser(DocumentBuilderFactory dbf) throws Exception {\n" +
                        "        // ป้องกัน XXE โดยปิดการทำงานที่ไม่ปลอดภัย\n\n" +
                        "        dbf.setFeature(" + userAnswers[0] + ", true);\n\n" +
                        "        dbf.setFeature(\"http://xml.org/sax/features/external-general-entities\", " + userAnswers[1] + ");\n\n" +
                        "        dbf.setFeature(\"http://xml.org/sax/features/external-parameter-entities\", " + userAnswers[2] + ");\n" +
                        "    }\n" +
                        "}";

        tvQuestionCode.setText(codeTemplate);
    }

    // ฟังก์ชันตรวจคำตอบ
    private void checkAnswers() {
        boolean isCorrect = true;

        for (int i = 0; i < 3; i++) {
            if (!userAnswers[i].equals(correctAnswers[i])) {
                isCorrect = false;
                break;
            }
        }

        if (isCorrect) {
            Toast.makeText(this, "🎉 ถูกต้องแล้ว! ป้องกันช่องโหว่ XXE ได้สำเร็จ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "❌ ยังไม่ถูกต้อง ลองใหม่อีกครั้งครับ", Toast.LENGTH_SHORT).show();
        }
    }

    // ฟังก์ชันแสดงคำใบ้
    private void showHint() {
        Toast.makeText(this, "💡 คำใบ้: คุณต้องปิด (disallow) DOCTYPE และตั้งค่า Entities เป็น false เพื่อป้องกัน XXE", Toast.LENGTH_LONG).show();
    }
}