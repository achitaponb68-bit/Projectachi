package com.example.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * GamePlay9: คลาสควบคุมหน้าเล่นเกม ด่านที่ 9 (ภาษา SQL - ระดับยาก Hard)
 * หัวข้อ: Dynamic Data Masking (MASKED) และการให้สิทธิ์เท่าที่จำเป็น (GRANT / Least Privilege)
 */
public class GamePlay9 extends AppCompatActivity {

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
        setContentView(R.layout.activity_game_play9);

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
     * ตรวจสอบว่าคำตอบในอาร์เรย์ userAnswers ทั้ง 3 ช่อง ตรงกับเฉลยของด่านที่ 9 หรือไม่
     * @return true หากตอบถูกต้องทั้ง 3 ช่อง, false หากยังมีช่องที่ผิด
     */
    private boolean isAllAnswersCorrect() {
        return "MASKED".equals(userAnswers[0]) &&
               "partial(0,\"XXXXXXXXX\",4)".equals(userAnswers[1]) &&
               "GRANT".equals(userAnswers[2]);
    }

    /**
     * ตรวจสอบคำตอบและบันทึกความคืบหน้าการผ่านด่านลงในระบบ
     */
    private void checkAndRecordAnswer() {
        if (isAllAnswersCorrect()) {
            GameProgressManager.markStageCompleted(this, 9);
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
        wrongOptions.add(btnOption2);
        wrongOptions.add(btnOption5);
        wrongOptions.add(btnOption6);
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
        GameProgressManager.recordStageHints(this, 9, hintCount);
        Toast.makeText(this, "ตัดตัวเลือกที่ผิดออกแล้ว 1 ข้อ (ใช้คำใบ้ไป " + hintCount + "/3)", Toast.LENGTH_SHORT).show();
    }

    /**
     * ตั้งค่า Event Listener ให้กับปุ่มต่างๆ ในหน้าจอ
     */
    private void setupListeners() {
        // ปุ่มย้อนกลับไปหน้าเลือกหมวดหมู่
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay9.this, Catagory.class);
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
                    Intent intent = new Intent(GamePlay9.this, SolutionDialog9.class);
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
        String slot1 = userAnswers[0];
        String slot2 = userAnswers[1];
        String slot3 = userAnswers[2];

        String codeTemplate =
                "-- ซ่อนเลขบัตรประชาชนให้แสดงเฉพาะ หลักท้าย\n" +
                        "ALTER TABLE customer_info\n\n" +
                        "MODIFY COLUMN national_id " + slot1 + "\n\n" +
                        "WITH (FUNCTION = " + slot2 + ");\n\n\n" +
                        "-- กำหนดสิทธิ์ตาม Least Privilege ให้ดูเฉพาะข้อมูลที่จำเป็น\n" +
                        slot3 + " SELECT ON customer_info TO 'app_read_role';";

        SpannableStringBuilder spannable = new SpannableStringBuilder(codeTemplate);
        int slotColor = Color.parseColor("#FACC15"); // สีเหลืองสด

        String[] slots = {slot1, slot2, slot3};
        int searchStartIndex = 0;

        for (String slotText : slots) {
            int startPos = codeTemplate.indexOf(slotText, searchStartIndex);
            if (startPos != -1) {
                int endPos = startPos + slotText.length();

                spannable.setSpan(
                        new ForegroundColorSpan(slotColor),
                        startPos,
                        endPos,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );

                searchStartIndex = endPos;
            }
        }

        if (tvQuestionCode != null) {
            tvQuestionCode.setText(spannable);
        }
    }

    /**
     * สร้างและแสดงป๊อปอัปคำใบ้ HintDialog9
     */
    private void showHintDialog() {
        if (hintCount >= 3) {
            Toast.makeText(this, "ใช้คำใบ้ครบ 3 ครั้งแล้ว (เหลือเฉพาะคำตอบที่ถูกต้อง)", Toast.LENGTH_SHORT).show();
            return;
        }
        HintDialog9 hintDialog = new HintDialog9(GamePlay9.this, this::useHint);
        hintDialog.show();
    }

    /**
     * ตรวจความถูกต้อง บันทึกความสำเร็จด่าน 9 และนำทางไปยังหน้าสรุปผลรวม (Summary)
     */
    private void goToNextQuestion() {
        if (isAllAnswersCorrect()) {
            GameProgressManager.markStageCompleted(GamePlay9.this, 9);
            Intent intent = new Intent(GamePlay9.this, Summary.class);
            intent.putExtra("hintCount", hintCount);
            intent.putExtra("category", "SQL");
            intent.putExtra("nextButtonLabel", "เลือกหมวดหมู่");
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "คำตอบยังไม่ถูกต้อง กรุณาตอบให้ถูกต้องก่อนไปข้อถัดไป", Toast.LENGTH_SHORT).show();
        }
    }
}