package timeline.lizimumu.com.t.common.data.service;

import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import timeline.lizimumu.com.t.R;
import timeline.lizimumu.com.t.feature.appMonitor.presentation.MainActivity;

public class AppService extends Service {

    public static final String SERVICE_ACTION = "service_action";
    public static final String SERVICE_ACTION_CHECK = "service_action_check";

    static final long CHECK_INTERVAL = 400;

    private DataManager mManager;
    private Context mContext;
    private Handler mHandler = new Handler();
    private Runnable mRepeatCheckTask = new Runnable() {
        @Override
        public void run() {
            Log.d("AppService", "mRepeatCheck");
            if (!mManager.hasPermission()) {
                mHandler.postDelayed(mRepeatCheckTask, CHECK_INTERVAL);
            } else {
                mHandler.removeCallbacks(mRepeatCheckTask);
                Toast.makeText(mContext, R.string.grant_success, Toast.LENGTH_SHORT).show();
                WorkRequest workRequest = new OneTimeWorkRequest.Builder(timeline.lizimumu.com.t.common.data.service.AlarmService.class).build();
                WorkManager.getInstance(mContext).enqueue(workRequest);
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        DataManager.init(mContext);
        mManager = DataManager.getInstance();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getStringExtra(SERVICE_ACTION);
            if (!TextUtils.isEmpty(action)) {
                switch (action) {
                    case SERVICE_ACTION_CHECK:
                        startIntervalCheck();
                        break;
                }
            }
        }
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        try {
            startService(new Intent(mContext, AppService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startIntervalCheck() {
        boolean valid = true;
        try {
            mManager.requestPermission();
        } catch (ActivityNotFoundException e) {
            valid = false;
        }
        if (valid) {
            Toast.makeText(mContext, R.string.toast_permission, Toast.LENGTH_LONG).show();
            mHandler.post(mRepeatCheckTask);
        } else {
            Toast.makeText(mContext, R.string.not_support, Toast.LENGTH_LONG).show();
        }
    }

}

