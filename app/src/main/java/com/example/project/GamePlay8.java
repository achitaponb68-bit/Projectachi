package com.example.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.View;
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
    private Button btnSlot1, btnSlot2, btnSlot3;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;

    // ===== สถานะคำตอบและการเลือก (State Variables) =====
    private int selectedSlotIndex = 0;
    private final String[] userAnswers = {"[ ช่องที่ 1 ]", "[ ช่องที่ 2 ]", "[ ช่องที่ 3 ]"};

    // ===== ระบบคำใบ้ (Hint System) =====
    private int hintCount = 0;
    private final List<Button> wrongOptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play8);

        initViews();
        initWrongOptions();
        setupListeners();
        updateCodeDisplay();
    }

    private void initViews() {
        btnBackCategory = findViewById(R.id.btnBackCategory);
        tvQuestionCode = findViewById(R.id.tvQuestionCode);

        btnSlot1 = findViewById(R.id.btnSlot1);
        btnSlot2 = findViewById(R.id.btnSlot2);
        btnSlot3 = findViewById(R.id.btnSlot3);

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

    private boolean isSlotCorrect(int index) {
        if (index == 0) return "?".equals(userAnswers[0]);
        if (index == 1) return "?".equals(userAnswers[1]);
        if (index == 2) return "@user_email, @user_pass".equals(userAnswers[2]);
        return false;
    }

    private boolean isAllAnswersCorrect() {
        return isSlotCorrect(0) && isSlotCorrect(1) && isSlotCorrect(2);
    }

    private void checkAndRecordAnswer() {
        if (isAllAnswersCorrect()) {
            GameProgressManager.markStageCompleted(this, 8);
            Toast.makeText(this, "ตอบถูกต้องครบทั้ง 3 ช่อง! บันทึกด่านสำเร็จแล้ว 🎉", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "คำตอบยังไม่ถูกต้องครบทั้ง 3 ช่อง ลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();
        }
    }

    private void initWrongOptions() {
        wrongOptions.clear();
        wrongOptions.add(btnOption3);
        wrongOptions.add(btnOption4);
        wrongOptions.add(btnOption5);
    }

    private void useHint() {
        if (hintCount >= 3 || wrongOptions.isEmpty()) {
            Toast.makeText(this, "ใช้คำใบ้ครบ 3 ครั้งแล้ว (เหลือเฉพาะคำตอบที่ถูกต้อง)", Toast.LENGTH_SHORT).show();
            return;
        }

        Button btnToRemove = wrongOptions.remove(0);
        if (btnToRemove != null) {
            btnToRemove.setEnabled(false);
            btnToRemove.setAlpha(0.3f);
        }

        hintCount++;
        GameProgressManager.recordStageHints(this, 8, hintCount);
        Toast.makeText(this, "ตัดตัวเลือกที่ผิดออกแล้ว 1 ข้อ (ใช้คำใบ้ไป " + hintCount + "/3)", Toast.LENGTH_SHORT).show();
    }

    private void setupListeners() {
        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay8.this, Catagory.class);
                startActivity(intent);
                finish();
            });
        }

        androidx.cardview.widget.CardView cardQuestion = findViewById(R.id.cardQuestion);
        if (cardQuestion != null) {
            cardQuestion.setOnClickListener(v -> resetAnswers());
        }
        if (tvQuestionCode != null) {
            tvQuestionCode.setOnClickListener(v -> resetAnswers());
        }

        if (btnSlot1 != null) btnSlot1.setOnClickListener(v -> setSlotIndex(0));
        if (btnSlot2 != null) btnSlot2.setOnClickListener(v -> setSlotIndex(1));
        if (btnSlot3 != null) btnSlot3.setOnClickListener(v -> setSlotIndex(2));

        if (btnOption1 != null) btnOption1.setOnClickListener(v -> selectOption(btnOption1.getText().toString()));
        if (btnOption2 != null) btnOption2.setOnClickListener(v -> selectOption(btnOption2.getText().toString()));
        if (btnOption3 != null) btnOption3.setOnClickListener(v -> selectOption(btnOption3.getText().toString()));
        if (btnOption4 != null) btnOption4.setOnClickListener(v -> selectOption(btnOption4.getText().toString()));
        if (btnOption5 != null) btnOption5.setOnClickListener(v -> selectOption(btnOption5.getText().toString()));
        if (btnOption6 != null) btnOption6.setOnClickListener(v -> selectOption(btnOption6.getText().toString()));

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

        if (btnHint != null) {
            btnHint.setOnClickListener(v -> showHintDialog());
        }

        if (btnNext != null) {
            btnNext.setOnClickListener(v -> goToNextQuestion());
        }
    }

    private void setSlotIndex(int index) {
        selectedSlotIndex = index;
        updateCodeDisplay();
    }

    private void selectOption(String selectedValue) {
        userAnswers[selectedSlotIndex] = selectedValue;
        selectedSlotIndex = (selectedSlotIndex + 1) % 3;
        updateCodeDisplay();
    }

    private void resetAnswers() {
        boolean anyReset = false;
        for (int i = 0; i < 3; i++) {
            if (!isSlotCorrect(i)) {
                userAnswers[i] = "[ ช่องที่ " + (i + 1) + " ]";
                anyReset = true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (!isSlotCorrect(i)) {
                selectedSlotIndex = i;
                break;
            }
        }
        updateCodeDisplay();
        if (anyReset) {
            Toast.makeText(this, "รีเซ็ตเฉพาะช่องที่ตอบผิดแล้ว (ล็อกช่องที่ตอบถูกไว้)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ทุกช่องตอบถูกแล้ว!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateSlotButtonsUI() {
        Button[] slotButtons = {btnSlot1, btnSlot2, btnSlot3};
        for (int i = 0; i < slotButtons.length; i++) {
            Button btn = slotButtons[i];
            if (btn != null) {
                if (isSlotCorrect(i)) {
                    btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#15803D")));
                    btn.setTextColor(Color.parseColor("#FFFFFF"));
                    btn.setText("✓ ช่องที่ " + (i + 1));
                } else if (i == selectedSlotIndex) {
                    btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#38BDF8")));
                    btn.setTextColor(Color.parseColor("#0F172A"));
                    btn.setText("ช่องที่ " + (i + 1));
                } else {
                    btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#0F172A")));
                    btn.setTextColor(Color.parseColor("#E2E8F0"));
                    btn.setText("ช่องที่ " + (i + 1));
                }
            }
        }
    }

    private void updateCodeDisplay() {
        updateSlotButtonsUI();

        String slot1 = userAnswers[0];
        String slot2 = userAnswers[1];
        String slot3 = userAnswers[2];

        String codeTemplate =
                "-- สร้างคำสั่งที่ปลอดภัยสำหรับการตรวจสอบการเข้าสู่ระบบ\n" +
                        "PREPARE login_stmt FROM\n\n" +
                        "'SELECT role, username FROM customer WHERE email = " + slot1 + " AND password_hash = " + slot2 + "';\n\n\n" +
                        "-- รับค่าที่ได้จากผู้ใช้มาเก็บไว้ในตัวแปร\n" +
                        "SET @user_email = 'user@example.com';\n" +
                        "SET @user_pass = 'hashed_pass_value';\n\n\n" +
                        "-- เรียกใช้คำสั่งโดยส่งค่าตัวแปรเข้าไป\n" +
                        "EXECUTE login_stmt USING " + slot3 + ";";

        SpannableStringBuilder spannable = new SpannableStringBuilder(codeTemplate);

        if (tvQuestionCode != null) {
            tvQuestionCode.setMovementMethod(LinkMovementMethod.getInstance());
        }

        String[] slots = {slot1, slot2, slot3};
        int searchStartIndex = 0;

        for (int i = 0; i < slots.length; i++) {
            final int slotIdx = i;
            String slotText = slots[i];
            int startPos = codeTemplate.indexOf(slotText, searchStartIndex);
            if (startPos != -1) {
                int endPos = startPos + slotText.length();

                spannable.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        setSlotIndex(slotIdx);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(false);
                        if (isSlotCorrect(slotIdx)) {
                            ds.setColor(Color.parseColor("#4ADE80"));
                            ds.setFakeBoldText(true);
                        } else if (slotIdx == selectedSlotIndex) {
                            ds.setColor(Color.parseColor("#FACC15"));
                            ds.setFakeBoldText(true);
                        } else {
                            ds.setColor(Color.parseColor("#38BDF8"));
                            ds.setFakeBoldText(false);
                        }
                    }
                }, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                if (isSlotCorrect(i)) {
                    spannable.setSpan(
                            new BackgroundColorSpan(Color.parseColor("#3322C55E")),
                            startPos,
                            endPos,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
                } else if (i == selectedSlotIndex) {
                    spannable.setSpan(
                            new BackgroundColorSpan(Color.parseColor("#33FACC15")),
                            startPos,
                            endPos,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    );
                }

                searchStartIndex = endPos;
            }
        }

        if (tvQuestionCode != null) {
            tvQuestionCode.setText(spannable);
        }
    }

    private void showHintDialog() {
        if (hintCount >= 3) {
            Toast.makeText(this, "ใช้คำใบ้ครบ 3 ครั้งแล้ว (เหลือเฉพาะคำตอบที่ถูกต้อง)", Toast.LENGTH_SHORT).show();
            return;
        }
        HintDialog8 hintDialog = new HintDialog8(GamePlay8.this, this::useHint);
        hintDialog.show();
    }

    private void goToNextQuestion() {
        if (isAllAnswersCorrect()) {
            GameProgressManager.markStageCompleted(GamePlay8.this, 8);
            Toast.makeText(this, "ตอบถูกต้องครบทั้ง 3 ช่อง! บันทึกด่านสำเร็จแล้ว 🎉", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ข้ามไปยังข้อถัดไป (ยังไม่บันทึกผ่านด่าน เนื่องจากยังตอบไม่ถูกครบ 3 ช่อง)", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(GamePlay8.this, GamePlay9.class);
        startActivity(intent);
        finish();
    }
}