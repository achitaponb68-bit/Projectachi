package com.example.project; // เปลี่ยนเป็น package ของคุณ

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;

public class HintDialog1 extends Dialog {

    // Interface สำหรับส่ง Event เมื่อผู้ใช้กดปุ่มใช้ตัวช่วย
    public interface OnHintUseListener {
        void onUseHint();
    }

    private final OnHintUseListener listener;

    public HintDialog1(@NonNull Context context, OnHintUseListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hint_dialog1);

        // ตั้งค่าให้พื้นหลัง Dialog โปร่งใสเพื่อแสดงขอบมน CardView ชัดเจน
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
            );
        }

        Button btnUseHint = findViewById(R.id.btnUseHint);
        TextView tvCloseDialog = findViewById(R.id.tvCloseDialog);

        // กดปุ่มใช้งานเพื่อตัดตัวเลือก
        btnUseHint.setOnClickListener(v -> {
            if (listener != null) {
                listener.onUseHint();
            }
            dismiss();
        });

        // กดปุ่มยกเลิก / ปิด
        tvCloseDialog.setOnClickListener(v -> dismiss());
    }
}