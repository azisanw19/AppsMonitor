package timeline.lizimumu.com.t.common.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import timeline.lizimumu.com.t.common.data.repository.PreferenceRepository;

public class PreferenceViewModel extends ViewModel {

    private final PreferenceRepository preferenceRepository;

    public MutableLiveData<Integer> preferenceListSort = new MutableLiveData<>();
    public MutableLiveData<Boolean> preferenceHideSystem = new MutableLiveData<>();
    public MutableLiveData<Boolean> preferenceHideUninstall = new MutableLiveData<>();
    public MutableLiveData<Boolean> preferenceHideOpenable = new MutableLiveData<>();

    public PreferenceViewModel(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public void getPreferenceListSort() {
        preferenceListSort.postValue(preferenceRepository.getPreferenceListSort());
    }

    public void setPreferenceListSort(int sort) {
        preferenceListSort.postValue(preferenceRepository.setPreferenceListSort(sort));
    }

    public void getPreferenceHideSystem() {
        preferenceHideSystem.postValue(preferenceRepository.getSettingsHideSystemApps());
    }

    public void setPreferenceHideSystem(boolean hide) {
        preferenceHideSystem.postValue(preferenceRepository.setSettingsHideSystemApps(hide));
    }

    public void getPreferenceHideUninstall() {
        preferenceHideUninstall.postValue(preferenceRepository.getSettingsUninstall());
    }

    public void setPreferenceHideUninstall(boolean hide) {
        preferenceHideUninstall.postValue(preferenceRepository.setSettingsUninstall(hide));
    }

    public void getPreferenceHideOpenable() {
        preferenceHideOpenable.postValue(preferenceRepository.getSettingsHideOpenableApps());
    }

    public void setPreferenceHideOpenable(boolean hide) {
        preferenceHideOpenable.postValue(preferenceRepository.setSettingsHideOpenableApps(hide));
    }

}
