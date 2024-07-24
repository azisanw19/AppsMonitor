package timeline.lizimumu.com.t.common.data.repository;

public interface PreferenceRepository {

    public int getPreferenceListSort();

    public int setPreferenceListSort(Integer sort);

    public Boolean getSettingsUninstall();

    public Boolean setSettingsUninstall(Boolean bool);

    public Boolean getSettingsHideSystemApps();

    public Boolean setSettingsHideSystemApps(Boolean bool);

    public Boolean getSettingsHideOpenableApps();

    public Boolean setSettingsHideOpenableApps(Boolean bool);

}
