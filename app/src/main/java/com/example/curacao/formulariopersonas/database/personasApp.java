package com.example.curacao.formulariopersonas.database;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

public class personasApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
