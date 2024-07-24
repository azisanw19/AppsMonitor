package timeline.lizimumu.com.t.common.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timeline.lizimumu.com.t.common.data.repository.MonitoringRepository;
import timeline.lizimumu.com.t.common.domain.model.IgnoreItem;

public class ListIgnoreAppsViewModel extends ViewModel {

    private final MonitoringRepository mMonitoringRepository;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public final MutableLiveData<List<IgnoreItem>> operationSuccess = new MutableLiveData<>();
    public final MutableLiveData<String> operationError = new MutableLiveData<>();

    public ListIgnoreAppsViewModel(MonitoringRepository monitoringRepository) {
        mMonitoringRepository = monitoringRepository;
    }

    public void getAllItems() {
        Disposable disposable = Single.fromCallable(mMonitoringRepository::getAllItems)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        operationSuccess::postValue,
                        throwable -> {
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
