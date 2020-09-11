
package com.infelt.inschedus;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.infelt.schedule.annotation.InSchedule;
import com.infelt.schedule.annotation.InScheduleType;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "start test TID: " + android.os.Process.myTid());
        testRunIo();
    }

    @InSchedule(InScheduleType.TYPE_MAIN)
    private void testRunMain() {
        Log.d(TAG, "testRunMain TID: " + android.os.Process.myTid());
    }

    @InSchedule(InScheduleType.TYPE_IO)
    private void testRunIo() {
        Log.d(TAG, "testRunIo TID: " + android.os.Process.myTid());
        testRunMain();
    }
}
