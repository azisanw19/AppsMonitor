package timeline.lizimumu.com.t.common.data.repository;


import java.util.List;

import timeline.lizimumu.com.t.common.domain.model.AppItem;

public interface DataManagerRepository {

    public boolean hasPermission();

    public List<AppItem> getApps(int sort, int offset);

    public List<AppItem> getTargetAppTimeline(String target, int offset);

}
