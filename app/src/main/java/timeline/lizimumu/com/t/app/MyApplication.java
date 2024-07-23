package timeline.lizimumu.com.t.app;

import android.app.Application;
import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import timeline.lizimumu.com.t.AppConst;
import timeline.lizimumu.com.t.BuildConfig;
import timeline.lizimumu.com.t.common.data.AppItem;
import timeline.lizimumu.com.t.common.data.DataManager;
import timeline.lizimumu.com.t.common.data.source.db.DbHistoryExecutor;
import timeline.lizimumu.com.t.common.data.source.db.DbIgnoreExecutor;
import timeline.lizimumu.com.t.common.presentation.viewModel.IgnoreExecutorViewModel;
import timeline.lizimumu.com.t.di.AppModule;
import timeline.lizimumu.com.t.service.AppService;
import timeline.lizimumu.com.t.util.CrashHandler;
import timeline.lizimumu.com.t.common.data.source.preference.PreferenceManager;

/**
 * My Application
 * Created by zb on 18/12/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManager.init(this);
        getApplicationContext().startService(new Intent(getApplicationContext(), AppService.class));
        DbIgnoreExecutor.init(getApplicationContext());
        DbHistoryExecutor.init(getApplicationContext());
        DataManager.init();
        addDefaultIgnoreAppsToDB();
        if (AppConst.CRASH_TO_FILE) CrashHandler.getInstance().init();
    }

    private void addDefaultIgnoreAppsToDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> mDefaults = new ArrayList<>();
                mDefaults.add("com.android.settings");
                mDefaults.add(BuildConfig.APPLICATION_ID);
                for (String packageName : mDefaults) {
                    AppItem item = new AppItem();
                    item.mPackageName = packageName;
                    item.mEventTime = System.currentTimeMillis();
                    DbIgnoreExecutor.getInstance().insertItem(item);
                }
            }
        }).run();
    }
}
