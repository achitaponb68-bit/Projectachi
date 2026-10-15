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

    public static int getCompletedStagesCountForCategory(Context context, String category) {
        if (context == null) return 0;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int start;
        int end;
        if (category != null && category.equalsIgnoreCase("Java")) {
            start = 4;
            end = 6;
        } else if (category != null && category.equalsIgnoreCase("SQL")) {
            start = 7;
            end = 9;
        } else {
            start = 1;
            end = 3;
        }
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (prefs.getBoolean("stage_" + i + "_completed", false)) {
                count++;
            }
        }
        return count;
    }

    public static void recordStageHints(Context context, int stageNumber, int hintCount) {
        if (context == null || stageNumber < 1 || stageNumber > 9) return;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt("stage_" + stageNumber + "_hints", hintCount).apply();
    }

    public static void resetCategoryProgress(Context context, String category) {
        if (context == null) return;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int start;
        int end;
        if (category != null && category.equalsIgnoreCase("Java")) {
            start = 4;
            end = 6;
        } else if (category != null && category.equalsIgnoreCase("SQL")) {
            start = 7;
            end = 9;
        } else {
            start = 1;
            end = 3;
        }
        SharedPreferences.Editor editor = prefs.edit();
        for (int i = start; i <= end; i++) {
            editor.putInt("stage_" + i + "_hints", 0);
            editor.putBoolean("stage_" + i + "_completed", false);
        }
        editor.apply();
    }

    public static void resetCategoryHints(Context context, String category) {
        resetCategoryProgress(context, category);
    }

    public static int getCategoryHintsCount(Context context, String category) {
        if (context == null) return 0;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int start;
        int end;
        if (category != null && category.equalsIgnoreCase("Java")) {
            start = 4;
            end = 6;
        } else if (category != null && category.equalsIgnoreCase("SQL")) {
            start = 7;
            end = 9;
        } else {
            start = 1;
            end = 3;
        }
        int totalHints = 0;
        for (int i = start; i <= end; i++) {
            totalHints += prefs.getInt("stage_" + i + "_hints", 0);
        }
        return totalHints;
    }
}