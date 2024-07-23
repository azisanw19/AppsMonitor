package timeline.lizimumu.com.t.common.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import timeline.lizimumu.com.t.common.data.repository.DataManagerRepository;

public class CheckPermissionDataViewModel extends ViewModel {

    private final DataManagerRepository mDataManagerRepository;

    public final MutableLiveData<Boolean> hasPermission = new MutableLiveData<>();

    public CheckPermissionDataViewModel(DataManagerRepository dataManagerRepository) {
        mDataManagerRepository = dataManagerRepository;
    }

    public void hasPermission() {
        hasPermission.postValue(mDataManagerRepository.hasPermission());
    }

}
