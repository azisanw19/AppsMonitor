package timeline.lizimumu.com.t.common.data.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timeline.lizimumu.com.t.common.domain.model.HistoryItem;
import timeline.lizimumu.com.t.common.data.log.FileLogManager;
import timeline.lizimumu.com.t.common.data.source.db.DbHistoryExecutor;
import timeline.lizimumu.com.t.common.domain.model.AppItem;
import timeline.lizimumu.com.t.util.AlarmUtil;
import timeline.lizimumu.com.t.util.AppUtil;

public class AlarmService extends Worker {

    private static final String ALARM_SERVICE_NAME = "alarm.service";
    private final Context mContext;

    public AlarmService(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        DataManager manager = DataManager.getInstance();
        List<AppItem> items = manager.getApps(0, 1);
        for (AppItem item : items) {
            HistoryItem historyItem = new HistoryItem();
            historyItem.mName = item.mName;
            historyItem.mPackageName = item.mPackageName;
            historyItem.mMobileTraffic = item.mMobile;
            historyItem.mIsSystem = AppUtil.isSystemApp(mContext.getPackageManager(), item.mPackageName) ? 1 : 0;
            historyItem.mDuration = item.mUsageTime;
            historyItem.mTimeStamp = AppUtil.getYesterdayTimestamp();
            historyItem.mDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(new Date(historyItem.mTimeStamp));
            DbHistoryExecutor.getInstance().insert(historyItem);
        }

        FileLogManager fileLogManager = FileLogManager.getInstance();
        fileLogManager.log("alarm " + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(new Date(System.currentTimeMillis())) + "\n");

        AlarmUtil.setAlarm(this.getApplicationContext());
        return Result.success();
    }
}
