package timeline.lizimumu.com.t.common.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timeline.lizimumu.com.t.common.domain.model.AppItem;
import timeline.lizimumu.com.t.common.data.repository.DataManagerRepository;

public class MonitoringAppsTargetViewModel extends ViewModel {

    private final DataManagerRepository mDataManagerRepository;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public final MutableLiveData<List<AppItem>> operationSuccess = new MutableLiveData<>();
    public final MutableLiveData<String> operationError = new MutableLiveData<>();
    public final MutableLiveData<Boolean> operationLoading = new MutableLiveData<>();

    public MonitoringAppsTargetViewModel(DataManagerRepository dataManagerRepository) {
        mDataManagerRepository = dataManagerRepository;
    }

    public void getTargetAppTimeline(String target, int offset) {
        operationLoading.setValue(true);

        Disposable disposable = Single.fromCallable(() -> mDataManagerRepository.getTargetAppTimeline(target, offset))
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        appItems -> {
                            operationLoading.setValue(false);
                            operationSuccess.postValue(appItems);
                        },
                        throwable -> {
                            operationError.postValue(throwable.toString());
                            operationLoading.setValue(false);
                        }
                );

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
