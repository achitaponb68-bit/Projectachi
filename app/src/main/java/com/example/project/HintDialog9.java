package com.example.project;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;

/**
 * HintDialog9: คลาส Dialog สำหรับแสดงป๊อปอัปยืนยันการใช้คำใบ้ในด่านที่ 9
 * ใช้ Interface Callback (OnHintUseListener) สื่อสารกับคลาส GamePlay9
 */
public class HintDialog9 extends Dialog {

    /**
     * อินเทอร์เฟซตัวรับฟังอีเวนต์เมื่อผู้ใช้ยืนยันจะใช้คำใบ้
     */
    public interface OnHintUseListener {
        void onUseHint();
    }

    private final OnHintUseListener listener;

    /**
     * Constructor รับ Context และ Listener เพื่อเชื่อมต่อการทำงานกับ GamePlay9
     */
    public HintDialog9(@NonNull Context context, OnHintUseListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // ซ่อน Title Bar
        setContentView(R.layout.activity_hint_dialog9);

        // กำหนดให้พื้นหลังของ Dialog เป็นแบบโปร่งใส (Transparent) และปรับขนาดให้เหมาะสม
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
            );
        }

        View btnUse = findViewById(R.id.btnUse5050);
        View btnClose = findViewById(R.id.btnCloseHint);

        // เมื่อผู้ใช้กดปุ่ม "ใช้คำใบ้"
        if (btnUse != null) {
            btnUse.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onUseHint(); // เรียกเมธอด useHint() ใน GamePlay9
                }
                dismiss(); // ปิดป๊อปอัป Dialog
            });
        }

        // เมื่อผู้ใช้กดปุ่มปิด
        if (btnClose != null) {
            btnClose.setOnClickListener(v -> dismiss());
        }
    }
}