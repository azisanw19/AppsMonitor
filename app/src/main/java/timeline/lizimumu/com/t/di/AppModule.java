package timeline.lizimumu.com.t.di;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import timeline.lizimumu.com.t.common.data.repository.PreferenceRepository;
import timeline.lizimumu.com.t.common.data.service.DataManager;
import timeline.lizimumu.com.t.common.data.repository.DataManagerRepository;
import timeline.lizimumu.com.t.common.data.repository.MonitoringRepository;
import timeline.lizimumu.com.t.common.data.source.db.DbHistoryExecutor;
import timeline.lizimumu.com.t.common.data.source.db.DbIgnoreExecutor;
import timeline.lizimumu.com.t.common.data.source.preference.PreferenceManager;
import timeline.lizimumu.com.t.common.domain.repository.DataManagerRepositoryImpl;
import timeline.lizimumu.com.t.common.domain.repository.MonitoringRepositoryImpl;
import timeline.lizimumu.com.t.common.domain.repository.PreferenceRepositoryImpl;

public class AppModule {

    private static AppModule sInstance;
    @Nullable
    private static DbIgnoreExecutor mdbIgnoreExecutor = null;
    @Nullable
    private static PreferenceManager mPreferenceManager = null;
    @Nullable
    private static DataManager mDataManager = null;
    @Nullable
    private static DbHistoryExecutor mDbHistoryExecutor = null;

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

    public @NonNull DataManager getDataManager() throws RuntimeException {
        if (mDataManager != null)
            return mDataManager;

        throw new RuntimeException("DataManager not been registered");
    }

    public void register(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public @NonNull DbHistoryExecutor getDbHistoryExecutor() throws RuntimeException {
        if (mDbHistoryExecutor != null)
            return mDbHistoryExecutor;

        throw new RuntimeException("DbHistoryExecutor not been registered");
    }

    public void register(DbHistoryExecutor dbHistoryExecutor) {
        mDbHistoryExecutor = dbHistoryExecutor;
    }

    public MonitoringRepository getMonitoringRepository() {
        return new MonitoringRepositoryImpl(getDbIgnoreExecutor());
    }

    public DataManagerRepository getDataManagerRepository() {
        return new DataManagerRepositoryImpl(getDataManager());
    }

    public PreferenceRepository getPreferenceRepository() {
        return new PreferenceRepositoryImpl(getPreferenceManager());
    }
}
