package com.example.mvvmlogindemo.view;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class Dumy extends AppCompatActivity {
    public void startAtivity(Context currentActivity, Class cls){
        Intent intent = new Intent(currentActivity,cls);
        startActivity(intent);
    }
}
