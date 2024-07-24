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

public class MonitoringAppsViewModel extends ViewModel {

    private final DataManagerRepository mDataManagerRepository;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public final MutableLiveData<List<AppItem>> operationSuccess = new MutableLiveData<>();
    public final MutableLiveData<String> operationError = new MutableLiveData<>();
    public final MutableLiveData<Boolean> operationLoading = new MutableLiveData<>();

    public MonitoringAppsViewModel(DataManagerRepository dataManagerRepository) {
        mDataManagerRepository = dataManagerRepository;
    }

    public void getApps(int sort, int order) {
        operationLoading.setValue(true);

        Disposable disposable = Single.fromCallable(() -> mDataManagerRepository.getApps(sort, order)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        appItems -> {
                            operationLoading.setValue(false);
                            operationSuccess.postValue(appItems);
                        },
                        throwable -> {
                            operationLoading.setValue(false);
                            operationError.postValue(throwable.toString());
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
