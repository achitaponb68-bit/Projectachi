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

public class HintDialog7 extends Dialog {

    public interface OnHintUseListener {
        void onUseHint();
    }

    private final OnHintUseListener listener;

    public HintDialog7(@NonNull Context context, OnHintUseListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hint_dialog7);

        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
            );
        }

        View btnUse = findViewById(R.id.btnUse5050);
        View btnClose = findViewById(R.id.btnCloseHint);

        if (btnUse != null) {
            btnUse.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onUseHint();
                }
                dismiss();
            });
        }

        if (btnClose != null) {
            btnClose.setOnClickListener(v -> dismiss());
        }
    }
}