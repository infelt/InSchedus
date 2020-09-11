package com.infelt.inschedus;

import android.app.Application;

import com.infelt.schedule.annotation.InScheduleInit;


public class MyApplication extends Application {

    @InScheduleInit
    @Override
    public void onCreate() {
        super.onCreate();
    }

}
