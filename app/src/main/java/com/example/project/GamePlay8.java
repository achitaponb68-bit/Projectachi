package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * GamePlay8: คลาสควบคุมหน้าเล่นเกม ด่านที่ 8 (ภาษา SQL - ระดับปานกลาง Medium)
 * หัวข้อ: การป้องกัน SQL Injection ด้วย Prepared Statements (PREPARE / EXECUTE USING)
 */
public class GamePlay8 extends AppCompatActivity {

    // ===== ส่วนประกอบ UI (Views) =====
    private Button btnBackCategory;
    private TextView tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;

    // ===== สถานะคำตอบและการเลือก (State Variables) =====
    // ดัชนีระบุตำแหน่งช่องที่กำลังเลือกเติม (0, 1 หรือ 2)
    private int selectedSlotIndex = 0;
    // อาร์เรย์เก็บคำตอบของผู้ใช้ในแต่ละช่อง (เริ่มต้นด้วยข้อความ Placeholder)
    private final String[] userAnswers = {"[ ช่องที่ 1 ]", "[ ช่องที่ 2 ]", "[ ช่องที่ 3 ]"};

    // ===== ระบบคำใบ้ (Hint System) =====
    // ตัวนับจำนวนคำใบ้ที่ใช้ไปแล้ว (ใช้ได้สูงสุด 3 ครั้ง)
    private int hintCount = 0;
    // รายการปุ่มตัวเลือกที่เป็นคำตอบผิด สำหรับนำมาปิดการใช้งานเมื่อกดใช้คำใบ้
    private final List<Button> wrongOptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play8);

        // เริ่มต้นการทำงานของ View, ปุ่มตัวเลือกผิด, Listener และการแสดงผลโค้ด
        initViews();
        initWrongOptions();
        setupListeners();
        updateCodeDisplay();
    }

    /**
     * ค้นหาและเชื่อมโยง View จากไฟล์ XML เข้ากับตัวแปรในคลาส
     */
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

    /**
     * ตรวจสอบว่าคำตอบในอาร์เรย์ userAnswers ทั้ง 3 ช่อง ตรงกับเฉลยของด่านที่ 8 หรือไม่
     * @return true หากตอบถูกต้องทั้ง 3 ช่อง, false หากยังมีช่องที่ผิด
     */
    private boolean isAllAnswersCorrect() {
        return "?".equals(userAnswers[0]) &&
               "?".equals(userAnswers[1]) &&
               "@user_email, @user_pass".equals(userAnswers[2]);
    }

    /**
     * ตรวจสอบคำตอบและบันทึกความคืบหน้าการผ่านด่านลงในระบบ
     */
    private void checkAndRecordAnswer() {
        if (isAllAnswersCorrect()) {
            GameProgressManager.markStageCompleted(this, 8);
            Toast.makeText(this, "ตอบถูกต้องครบทั้ง 3 ช่อง! บันทึกด่านสำเร็จแล้ว 🎉", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "คำตอบยังไม่ถูกต้องครบทั้ง 3 ช่อง ลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * กำหนดปุ่มตัวเลือกที่เป็นคำตอบผิดลงใน wrongOptions สำหรับระบบคำใบ้
     */
    private void initWrongOptions() {
        wrongOptions.clear();
        wrongOptions.add(btnOption3);
        wrongOptions.add(btnOption4);
        wrongOptions.add(btnOption5);
    }

    /**
     * ประมวลผลการใช้คำใบ้: ดึงปุ่มผิดออก 1 ปุ่ม, ปิดการใช้งานปุ่ม, จางปุ่มลง และบันทึกประวัติคำใบ้
     */
    private void useHint() {
        if (hintCount >= 3 || wrongOptions.isEmpty()) {
            Toast.makeText(this, "ใช้คำใบ้ครบ 3 ครั้งแล้ว (เหลือเฉพาะคำตอบที่ถูกต้อง)", Toast.LENGTH_SHORT).show();
            return;
        }

        // ดึงปุ่มผิดตัวแรกออกจากลิสต์แล้วสั่งปิดการใช้งาน
        Button btnToRemove = wrongOptions.remove(0);
        if (btnToRemove != null) {
            btnToRemove.setEnabled(false);
            btnToRemove.setAlpha(0.3f);
        }

        hintCount++;
        GameProgressManager.recordStageHints(this, 8, hintCount);
        Toast.makeText(this, "ตัดตัวเลือกที่ผิดออกแล้ว 1 ข้อ (ใช้คำใบ้ไป " + hintCount + "/3)", Toast.LENGTH_SHORT).show();
    }

    /**
     * ตั้งค่า Event Listener ให้กับปุ่มต่างๆ ในหน้าจอ
     */
    private void setupListeners() {
        // ปุ่มย้อนกลับไปหน้าเลือกหมวดหมู่
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay8.this, Catagory.class);
                startActivity(intent);
                finish();
            });
        }

        // ปุ่มตัวเลือกคำตอบทั้ง 6 ปุ่ม
        if (btnOption1 != null) btnOption1.setOnClickListener(v -> selectOption(btnOption1.getText().toString()));
        if (btnOption2 != null) btnOption2.setOnClickListener(v -> selectOption(btnOption2.getText().toString()));
        if (btnOption3 != null) btnOption3.setOnClickListener(v -> selectOption(btnOption3.getText().toString()));
        if (btnOption4 != null) btnOption4.setOnClickListener(v -> selectOption(btnOption4.getText().toString()));
        if (btnOption5 != null) btnOption5.setOnClickListener(v -> selectOption(btnOption5.getText().toString()));
        if (btnOption6 != null) btnOption6.setOnClickListener(v -> selectOption(btnOption6.getText().toString()));

        // แตะที่ตัวข้อความโค้ด เพื่อทำการล้างคำตอบ (Reset)
        if (tvQuestionCode != null) {
            tvQuestionCode.setOnClickListener(v -> resetAnswers());
        }

        // ปุ่มตรวจคำตอบ
        if (btnCheck != null) {
            btnCheck.setOnClickListener(v -> {
                if (isAllAnswersCorrect()) {
                    checkAndRecordAnswer();
                    Intent intent = new Intent(GamePlay8.this, SolutionDialog8.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "คำตอบยังไม่ถูกต้อง ลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // ปุ่มขอคำใบ้
        if (btnHint != null) {
            btnHint.setOnClickListener(v -> showHintDialog());
        }

        // ปุ่มไปยังข้อถัดไป
        if (btnNext != null) {
            btnNext.setOnClickListener(v -> goToNextQuestion());
        }
    }

    /**
     * ใส่ค่าข้อความที่เลือกลงในอาร์เรย์คำตอบ และเลื่อนตำแหน่งช่องถัดไป
     * @param selectedValue ข้อความบนปุ่มตัวเลือกที่ผู้ใช้กด
     */
    private void selectOption(String selectedValue) {
        userAnswers[selectedSlotIndex] = selectedValue;
        selectedSlotIndex = (selectedSlotIndex + 1) % 3; // วนลูป 0 -> 1 -> 2 -> 0
        updateCodeDisplay();
    }

    /**
     * รีเซ็ตคำตอบทุกช่องกลับเป็นข้อความ Placeholder เริ่มต้น
     */
    private void resetAnswers() {
        userAnswers[0] = "[ ช่องที่ 1 ]";
        userAnswers[1] = "[ ช่องที่ 2 ]";
        userAnswers[2] = "[ ช่องที่ 3 ]";
        selectedSlotIndex = 0;
        updateCodeDisplay();
    }

    /**
     * อัปเดตเทมเพลตโค้ด SQL แสดงผลบน TextView หน้าจอ
     */
    private void updateCodeDisplay() {
        String codeTemplate =
                "-- สร้างคำสั่งที่ปลอดภัยสำหรับการตรวจสอบการเข้าสู่ระบบ\n" +
                        "PREPARE login_stmt FROM\n\n" +
                        "'SELECT role, username FROM customer WHERE email = " + userAnswers[0] + " AND password_hash = " + userAnswers[1] + "';\n\n\n" +
                        "-- รับค่าที่ได้จากผู้ใช้มาเก็บไว้ในตัวแปร\n" +
                        "SET @user_email = 'user@example.com';\n" +
                        "SET @user_pass = 'hashed_pass_value';\n\n\n" +
                        "-- เรียกใช้คำสั่งโดยส่งค่าตัวแปรเข้าไป\n" +
                        "EXECUTE login_stmt USING " + userAnswers[2] + ";";

        if (tvQuestionCode != null) {
            tvQuestionCode.setText(codeTemplate);
        }
    }

    /**
     * สร้างและแสดงป๊อปอัปคำใบ้ HintDialog8
     */
    private void showHintDialog() {
        if (hintCount >= 3) {
            Toast.makeText(this, "ใช้คำใบ้ครบ 3 ครั้งแล้ว (เหลือเฉพาะคำตอบที่ถูกต้อง)", Toast.LENGTH_SHORT).show();
            return;
        }
        HintDialog8 hintDialog = new HintDialog8(GamePlay8.this, this::useHint);
        hintDialog.show();
    }

    /**
     * ตรวจความถูกต้องและนำทางไปยังด่านถัดไป (GamePlay9)
     */
    private void goToNextQuestion() {
        if (isAllAnswersCorrect()) {
            GameProgressManager.markStageCompleted(GamePlay8.this, 8);
            Intent intent = new Intent(GamePlay8.this, GamePlay9.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "คำตอบยังไม่ถูกต้อง กรุณาตอบให้ถูกต้องก่อนไปข้อถัดไป", Toast.LENGTH_SHORT).show();
        }
    }
}