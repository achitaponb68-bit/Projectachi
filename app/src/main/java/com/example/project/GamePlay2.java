package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class GamePlay2 extends AppCompatActivity {

    private Button btnBackCategory;
    private TextView tvLanguageBadge, tvDifficultyBadge, tvQuestionNumber;
    private TextView tvTitle, tvSubTitle, tvQuestionHeader, tvQuestionDescription, tvQuestionCode;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnOption5, btnOption6;
    private Button btnCheck, btnHint, btnNext;

    private int selectedSlotIndex = 0;
    private final String[] userAnswers = {"[ ช่องที่ 1 ]", "[ ช่องที่ 2 ]", "[ ช่องที่ 3 ]"};

    private int hintCount = 0;
    private final List<Button> wrongOptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_game_play2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String category = getIntent().getStringExtra("category");
        String difficulty = getIntent().getStringExtra("difficulty");

        btnBackCategory = findViewById(R.id.btnBackCategory);
        tvLanguageBadge = findViewById(R.id.tvLanguageBadge);
        tvDifficultyBadge = findViewById(R.id.tvDifficultyBadge);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvTitle = findViewById(R.id.tvTitle);
        tvSubTitle = findViewById(R.id.tvSubTitle);
        tvQuestionHeader = findViewById(R.id.tvQuestionHeader);
        tvQuestionDescription = findViewById(R.id.tvQuestionDescription);
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

        initWrongOptions();
        setupListeners();
        updateCodeDisplay();

        if (btnBackCategory != null) {
            btnBackCategory.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay2.this, Catagory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        if (btnCheck != null) {
            btnCheck.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay2.this, SolutionDialog2.class);
                startActivity(intent);
            });
        }

        if (btnHint != null) {
            btnHint.setOnClickListener(v -> {
                if (hintCount >= 3) {
                    Toast.makeText(this, "ใช้คำใบ้ครบ 3 ครั้งแล้ว (เหลือเฉพาะคำตอบที่ถูกต้อง)", Toast.LENGTH_SHORT).show();
                    return;
                }
                HintDialog2 hintDialog = new HintDialog2(GamePlay2.this, this::useHint);
                hintDialog.show();
            });
        }

        if (btnNext != null) {
            btnNext.setOnClickListener(v -> {
                Intent intent = new Intent(GamePlay2.this, GamePlay3.class);
                intent.putExtra("category", category);
                intent.putExtra("difficulty", difficulty);
                startActivity(intent);
                finish();
            });
        }
    }

    private void initWrongOptions() {
        wrongOptions.clear();
        wrongOptions.add(btnOption2);
        wrongOptions.add(btnOption3);
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
        Toast.makeText(this, "ตัดตัวเลือกที่ผิดออกแล้ว 1 ข้อ (ใช้คำใบ้ไป " + hintCount + "/3)", Toast.LENGTH_SHORT).show();
    }

    private void setupListeners() {
        if (btnOption1 != null) btnOption1.setOnClickListener(v -> selectOption(btnOption1.getText().toString()));
        if (btnOption2 != null) btnOption2.setOnClickListener(v -> selectOption(btnOption2.getText().toString()));
        if (btnOption3 != null) btnOption3.setOnClickListener(v -> selectOption(btnOption3.getText().toString()));
        if (btnOption4 != null) btnOption4.setOnClickListener(v -> selectOption(btnOption4.getText().toString()));
        if (btnOption5 != null) btnOption5.setOnClickListener(v -> selectOption(btnOption5.getText().toString()));
        if (btnOption6 != null) btnOption6.setOnClickListener(v -> selectOption(btnOption6.getText().toString()));

        if (tvQuestionCode != null) {
            tvQuestionCode.setOnClickListener(v -> resetAnswers());
        }
    }

    private void selectOption(String value) {
        userAnswers[selectedSlotIndex] = value;
        selectedSlotIndex = (selectedSlotIndex + 1) % 3;
        updateCodeDisplay();
    }

    private void resetAnswers() {
        userAnswers[0] = "[ ช่องที่ 1 ]";
        userAnswers[1] = "[ ช่องที่ 2 ]";
        userAnswers[2] = "[ ช่องที่ 3 ]";
        selectedSlotIndex = 0;
        updateCodeDisplay();
    }

    private void updateCodeDisplay() {
        String codeTemplate =
                "#include <stdio.h>\n\n" +
                "int main() {\n" +
                "    char data = 'A';\n" +
                "    char key = 'K';\n\n" +
                "    char encrypted = " + userAnswers[0] + "\n\n" +
                "    char temp = encrypted;\n\n" +
                "    " + userAnswers[1] + "\n\n" +
                "    " + userAnswers[2] + "\n\n" +
                "    printf(\"Decrypted: %c\\n\", temp ^ key);\n" +
                "    return 0;\n" +
                "}";

        if (tvQuestionCode != null) {
            tvQuestionCode.setText(codeTemplate);
        }
    }
}