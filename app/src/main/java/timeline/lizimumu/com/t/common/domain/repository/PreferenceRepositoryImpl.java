package timeline.lizimumu.com.t.common.domain.repository;

import timeline.lizimumu.com.t.common.data.repository.PreferenceRepository;
import timeline.lizimumu.com.t.common.data.source.preference.PreferenceManager;

public class PreferenceRepositoryImpl implements PreferenceRepository {

    private final PreferenceManager mPreferenceManager;

    public PreferenceRepositoryImpl(PreferenceManager preferenceManager) {
        mPreferenceManager = preferenceManager;
    }

    @Override
    public int getPreferenceListSort() {
        return mPreferenceManager.getInt(PreferenceManager.PREF_LIST_SORT);
    }

    @Override
    public int setPreferenceListSort(Integer sort) {
        mPreferenceManager.putInt(PreferenceManager.PREF_LIST_SORT, sort);
        return mPreferenceManager.getInt(PreferenceManager.PREF_LIST_SORT);
    }

    @Override
    public Boolean getSettingsUninstall() {
        return mPreferenceManager.getUninstallSettings(PreferenceManager.PREF_SETTINGS_HIDE_UNINSTALL_APPS);
    }

    @Override
    public Boolean setSettingsUninstall(Boolean b) {
        PreferenceManager.getInstance().putBoolean(PreferenceManager.PREF_SETTINGS_HIDE_UNINSTALL_APPS, b);
        return mPreferenceManager.getUninstallSettings(PreferenceManager.PREF_SETTINGS_HIDE_UNINSTALL_APPS);
    }

    @Override
    public Boolean getSettingsHideSystemApps() {
        return mPreferenceManager.getSystemSettings(PreferenceManager.PREF_SETTINGS_HIDE_SYSTEM_APPS);
    }

    @Override
    public Boolean setSettingsHideSystemApps(Boolean bool) {
        PreferenceManager.getInstance().putBoolean(PreferenceManager.PREF_SETTINGS_HIDE_SYSTEM_APPS, bool);
        return mPreferenceManager.getSystemSettings(PreferenceManager.PREF_SETTINGS_HIDE_SYSTEM_APPS);
    }

    @Override
    public Boolean getSettingsHideOpenableApps() {
        return mPreferenceManager.getSystemSettings(PreferenceManager.PREF_SETTINGS_HIDE_OPENABLE);
    }

    @Override
    public Boolean setSettingsHideOpenableApps(Boolean bool) {
        PreferenceManager.getInstance().putBoolean(PreferenceManager.PREF_SETTINGS_HIDE_OPENABLE, bool);
        return mPreferenceManager.getSystemSettings(PreferenceManager.PREF_SETTINGS_HIDE_OPENABLE);
    }
}
