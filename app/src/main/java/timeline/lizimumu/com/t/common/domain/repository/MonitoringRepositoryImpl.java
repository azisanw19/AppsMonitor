package timeline.lizimumu.com.t.common.domain.repository;

import java.util.Collections;
import java.util.List;

import timeline.lizimumu.com.t.common.domain.model.AppItem;
import timeline.lizimumu.com.t.common.data.repository.MonitoringRepository;
import timeline.lizimumu.com.t.common.data.source.db.DbIgnoreExecutor;
import timeline.lizimumu.com.t.common.domain.model.IgnoreItem;

public class MonitoringRepositoryImpl implements MonitoringRepository {

    private final DbIgnoreExecutor dbIgnoreExecutor;

    public MonitoringRepositoryImpl(DbIgnoreExecutor dbIgnoreExecutor) {
        this.dbIgnoreExecutor = dbIgnoreExecutor;
    }

    @Override
    public void insertItem(AppItem item) {
        dbIgnoreExecutor.insertItem(item);
    }

    @Override
    public void insertItem(String packageName) {
        dbIgnoreExecutor.insertItem(packageName);
    }

    @Override
    public List<IgnoreItem> getAllItems() {
        return DbIgnoreExecutor.getInstance().getAllItems();
    }

    @Override
    public void deleteItem(IgnoreItem item) {
        dbIgnoreExecutor.deleteItem(item);
    }
}
