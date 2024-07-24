package timeline.lizimumu.com.t.app;

import android.app.Application;
import android.content.Intent;

import timeline.lizimumu.com.t.AppConst;
import timeline.lizimumu.com.t.common.data.service.DataManager;
import timeline.lizimumu.com.t.common.data.source.db.DbHistoryExecutor;
import timeline.lizimumu.com.t.common.data.source.db.DbIgnoreExecutor;
import timeline.lizimumu.com.t.di.AppModule;
import timeline.lizimumu.com.t.common.data.service.AppService;
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
        DataManager.init(getApplicationContext());
        if (AppConst.CRASH_TO_FILE) CrashHandler.getInstance().init();

        AppModule.init();
        AppModule appModule = AppModule.getInstance();
        appModule.register(PreferenceManager.getInstance());
        appModule.register(DbIgnoreExecutor.getInstance());
        appModule.register(DbHistoryExecutor.getInstance());
        appModule.register(DataManager.getInstance());
    }
}
