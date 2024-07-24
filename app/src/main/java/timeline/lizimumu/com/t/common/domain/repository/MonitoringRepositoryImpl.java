package timeline.lizimumu.com.t.common.domain.repository;

import timeline.lizimumu.com.t.common.domain.model.AppItem;
import timeline.lizimumu.com.t.common.data.repository.MonitoringRepository;
import timeline.lizimumu.com.t.common.data.source.db.DbIgnoreExecutor;

public class MonitoringRepositoryImpl implements MonitoringRepository {

    private final DbIgnoreExecutor dbIgnoreExecutor;

    public MonitoringRepositoryImpl(DbIgnoreExecutor dbIgnoreExecutor) {
        this.dbIgnoreExecutor = dbIgnoreExecutor;
    }

    @Override
    public void insertItem(AppItem item) {
        dbIgnoreExecutor.insertItem(item);
    }
}
