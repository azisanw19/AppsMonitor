package timeline.lizimumu.com.t.di;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import timeline.lizimumu.com.t.common.data.source.db.DbIgnoreExecutor;
import timeline.lizimumu.com.t.common.data.source.preference.PreferenceManager;

public class AppModule {

    private static AppModule sInstance;
    @Nullable
    private static DbIgnoreExecutor mdbIgnoreExecutor = null;
    @Nullable
    private static PreferenceManager mPreferenceManager = null;

    private AppModule() {

    }

    public static void init() {
        sInstance = new AppModule();
    }

    public static AppModule getInstance() {
        return sInstance;
    }

    public @NonNull DbIgnoreExecutor getDbIgnoreExecutor() throws RuntimeException {
        if (mdbIgnoreExecutor != null)
            return mdbIgnoreExecutor;

        throw new RuntimeException("dbIgnoreExecutor not been registered");
    }

    public void register(DbIgnoreExecutor dbIgnoreExecutor) {
        mdbIgnoreExecutor = dbIgnoreExecutor;
    }

    public @NonNull PreferenceManager getPreferenceManager() throws RuntimeException {
        if (mPreferenceManager != null)
            return mPreferenceManager;

        throw new RuntimeException("PreferenceManager not been registered");
    }

    public void register(PreferenceManager preferenceManager) {
        mPreferenceManager = preferenceManager;
    }
}
