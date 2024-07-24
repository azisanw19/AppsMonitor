package timeline.lizimumu.com.t.common.data.repository;

import java.util.List;

import timeline.lizimumu.com.t.common.domain.model.AppItem;
import timeline.lizimumu.com.t.common.domain.model.IgnoreItem;

public interface MonitoringRepository {

    public void insertItem(AppItem item);

    public void insertItem(String packageName);

    public List<IgnoreItem> getAllItems();

    public void deleteItem(IgnoreItem item);

}
