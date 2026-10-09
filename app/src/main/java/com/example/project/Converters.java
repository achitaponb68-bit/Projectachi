package com.example.project;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    // แปลงจาก List<String> เป็น String (JSON Format) บันทึกลง Room DB
    @TypeConverter
    public static String fromListToString(List<String> list) {
        if (list == null) return null;
        return new Gson().toJson(list);
    }

    // แปลงจาก String (JSON Format) กลับเป็น List<String> เพื่อนำมาใช้งาน
    @TypeConverter
    public static List<String> fromStringToList(String value) {
        if (value == null) return null;
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

}
