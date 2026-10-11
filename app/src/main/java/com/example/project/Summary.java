package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Summary extends AppCompatActivity {

    private TextView tvHintDetails, tvWrongDetails;
    private Button btnNextStage, btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String category = getIntent().getStringExtra("category");
        String nextButtonLabel = getIntent().getStringExtra("nextButtonLabel");

        int extraCorrect = getIntent().getIntExtra("correctStages", -1);
        int correctStages = (extraCorrect != -1) ? extraCorrect : GameProgressManager.getCompletedStagesCountForCategory(this, category);

        int categoryHints = GameProgressManager.getCategoryHintsCount(this, category);

        // ผูก View จาก XML
        tvHintDetails = findViewById(R.id.tvHintDetails);
        tvWrongDetails = findViewById(R.id.tvWrongDetails);
        btnNextStage = findViewById(R.id.button8); // ปุ่มด่านถัดไป
        btnMainMenu = findViewById(R.id.button9);  // ปุ่มกลับไปหน้าเมนู

        // อัปเดตข้อความตามจำนวนที่ได้รับ
        if (tvHintDetails != null) {
            tvHintDetails.setText("ใช้คำใบ้ไปทั้งหมด: " + categoryHints + " ครั้ง");
        }
        if (tvWrongDetails != null) {
            tvWrongDetails.setText("ตอบถูกทั้งหมด: " + correctStages + " / 3 ด่าน");
        }

        if (btnNextStage != null && nextButtonLabel != null && !nextButtonLabel.isEmpty()) {
            btnNextStage.setText(nextButtonLabel + "  ➔");
        }

        // Event ปุ่มกลับหน้าเมนู
        if (btnMainMenu != null) {
            btnMainMenu.setOnClickListener(v -> {
                Intent intent = new Intent(Summary.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        // Event ปุ่มไปด่านถัดไป
        if (btnNextStage != null) {
            btnNextStage.setOnClickListener(v -> {
                Intent intent = new Intent(Summary.this, Catagory.class);
                startActivity(intent);
                finish();
            });
        }
    }
}


