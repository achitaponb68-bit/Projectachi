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
        displaySolutionCode();
    }

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        btnDone = findViewById(R.id.btnDone);
        tvSolutionCode = findViewById(R.id.tvSolutionCode);
    }

    private void setupListeners() {
        // ปุ่มย้อนกลับ
        btnBackCategory.setOnClickListener(v -> finish());

        // ปุ่มเข้าใจแล้ว / ปิดหน้าเฉลย
        btnDone.setOnClickListener(v -> finish());
    }

    private void displaySolutionCode() {
        String fullSolutionCode =
                "import javax.xml.parsers.DocumentBuilderFactory;\n\n" +
                        "public class SafeXmlParser {\n" +
                        "    public static void secureParser(DocumentBuilderFactory dbf) throws Exception {\n" +
                        "        // ป้องกัน XXE โดยปิดการทำงานที่ไม่ปลอดภัย\n\n" +
                        "        dbf.setFeature(\"http://apache.org/xml/features/disallow-doctype-decl\", true);\n\n" +
                        "        dbf.setFeature(\"http://xml.org/sax/features/external-general-entities\", false);\n\n" +
                        "        dbf.setFeature(\"http://xml.org/sax/features/external-parameter-entities\", false);\n" +
                        "    }\n" +
                        "}";

        tvSolutionCode.setText(fullSolutionCode);
    }
}