package timeline.lizimumu.com.t.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import timeline.lizimumu.com.t.common.data.service.AlarmService;

/**
 * Alarm receiver
 * Created by zb on 02/01/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        WorkRequest workRequest = new OneTimeWorkRequest.Builder(AlarmService.class).build();
        WorkManager.getInstance(context).enqueue(workRequest);
    }
}
