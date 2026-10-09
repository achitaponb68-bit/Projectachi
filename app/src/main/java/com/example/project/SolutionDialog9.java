package com.example.project; // เปลี่ยนให้ตรงกับ package name ของโปรเจกต์คุณ

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SolutionDialog9 extends AppCompatActivity {

    private Button btnBackCategory;
    private Button btnDone;
    private TextView tvSolutionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_dialog9);

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

        // ปุ่มเข้าใจแล้ว / ปิดหน้าเฉลยกลับหน้าหลัก
        btnDone.setOnClickListener(v -> finish());
    }

    private void displaySolutionCode() {
        String fullSolutionCode =
                "-- ซ่อนเลขบัตรประชาชนให้แสดงเฉพาะ  หลักท้าย\n" +
                        "ALTER TABLE customer_info\n\n" +
                        "MODIFY COLUMN national_id MASKED\n\n" +
                        "WITH (FUNCTION = partial(0,\"XXXXXXXXX\",4));\n\n\n" +
                        "-- กำหนดสิทธิ์ตาม Least Privilege ให้ดูเฉพาะข้อมูลที่จำเป็น\n" +
                        "GRANT SELECT ON customer_info TO 'app_read_role';";

        tvSolutionCode.setText(fullSolutionCode);
    }
}