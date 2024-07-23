package timeline.lizimumu.com.t.common.domain.repository;

import timeline.lizimumu.com.t.common.data.DataManager;
import timeline.lizimumu.com.t.common.data.repository.DataManagerRepository;

public class DataManagerRepositoryImpl implements DataManagerRepository {

    private final DataManager dataManager;

    public DataManagerRepositoryImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public boolean hasPermission() {
        return dataManager.hasPermission();
    }

}
