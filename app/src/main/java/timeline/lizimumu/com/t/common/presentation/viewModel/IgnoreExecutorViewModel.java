package timeline.lizimumu.com.t.common.presentation.viewModel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import timeline.lizimumu.com.t.BuildConfig;
import timeline.lizimumu.com.t.common.data.AppItem;
import timeline.lizimumu.com.t.common.data.source.db.DbIgnoreExecutor;


public class IgnoreExecutorViewModel extends ViewModel {

    private final DbIgnoreExecutor mdbIgnoreExecutor;

    public IgnoreExecutorViewModel(DbIgnoreExecutor dbIgnoreExecutor) {
        mdbIgnoreExecutor = dbIgnoreExecutor;
    }

    public void addDefaultIgnoreAppsToDB() {
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
        }).start();
    }

}
