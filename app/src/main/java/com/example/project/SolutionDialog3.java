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

public class SolutionDialog3 extends AppCompatActivity {

    private TextView tvCodeSolution;
    private Button btnBackMenu, btnNextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_solution_dialog3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvCodeSolution = findViewById(R.id.tvCodeSolution);
        btnBackMenu = findViewById(R.id.btnBackMenu);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);

        if (btnBackMenu != null) {
            btnBackMenu.setOnClickListener(v -> {
                Intent intent = new Intent(SolutionDialog3.this, Catagory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }

        if (btnNextQuestion != null) {
            btnNextQuestion.setOnClickListener(v -> {
                Toast.makeText(this, "จบด่านแล้ว! กำลังไปยังหน้าสรุปคะแนน...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SolutionDialog3.this, Levelsummary.class);
                startActivity(intent);
                finish();
            });
        }
    }
}