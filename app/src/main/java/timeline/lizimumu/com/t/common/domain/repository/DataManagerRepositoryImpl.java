package timeline.lizimumu.com.t.common.domain.repository;

import java.util.List;

import timeline.lizimumu.com.t.common.domain.model.AppItem;
import timeline.lizimumu.com.t.common.data.service.DataManager;
import timeline.lizimumu.com.t.common.data.repository.DataManagerRepository;

public class DataManagerRepositoryImpl implements DataManagerRepository {

    private final DataManager dataManager;

    public DataManagerRepositoryImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public boolean hasPermission() {
        return dataManager.hasPermission();
    }

    @Override
    public List<AppItem> getApps(int sort, int offset) {
        return dataManager.getApps(sort, offset);
    }

    @Override
    public List<AppItem> getTargetAppTimeline(String target, int offset) {
        return dataManager.getTargetAppTimeline(target, offset);
    }

}
