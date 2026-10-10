package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;

public class GameProgressManager {

    private static final String PREF_NAME = "CodieQuestProgress";

    public static void markStageCompleted(Context context, int stageNumber) {
        if (context == null || stageNumber < 1 || stageNumber > 9) return;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean("stage_" + stageNumber + "_completed", true).apply();
    }

    public static int getCompletedStagesCount(Context context) {
        if (context == null) return 0;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (prefs.getBoolean("stage_" + i + "_completed", false)) {
                count++;
            }
        }
        return count;
    }
}