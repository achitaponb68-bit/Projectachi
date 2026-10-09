package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private LinearLayout btnCategory, btnProfile, btnHowToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. ผูก View กับ ID ใน XML
        btnCategory = findViewById(R.id.btnCategory);
        btnProfile = findViewById(R.id.btnProfile);
        btnHowToPlay = findViewById(R.id.btnHowToPlay);

        // 2. ปุ่มเลือกหมวดหมู่ -> แก้ไขตรงนี้ให้ไปหน้า Catagory.class
        if (btnCategory != null) {
            btnCategory.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, Catagory.class); // <--- แก้เป็น Catagory.class
                startActivity(intent);
            });
        }

        // 3. ปุ่มโปรไฟล์ -> ไปหน้า Profile
        if (btnProfile != null) {
            btnProfile.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            });
        }

        // 4. ปุ่มวิธีการเล่น -> ไปหน้า HowToPlay
        if (btnHowToPlay != null) {
            btnHowToPlay.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, HowToPlay.class);
                startActivity(intent);
            });
        }
    }
}