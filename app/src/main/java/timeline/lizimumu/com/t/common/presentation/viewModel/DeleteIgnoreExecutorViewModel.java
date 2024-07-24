package timeline.lizimumu.com.t.common.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timeline.lizimumu.com.t.common.data.repository.MonitoringRepository;
import timeline.lizimumu.com.t.common.domain.model.IgnoreItem;

public class DeleteIgnoreExecutorViewModel extends ViewModel {

    private final MonitoringRepository mMonitoringRepository;

    public final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public final MutableLiveData<Boolean> operationSuccess = new MutableLiveData<>();
    public final MutableLiveData<String> operationError = new MutableLiveData<>();

    public DeleteIgnoreExecutorViewModel(MonitoringRepository monitoringRepository) {
        mMonitoringRepository = monitoringRepository;
    }

    public void deleteItem(IgnoreItem item) {
        Disposable disposable = Single.fromCallable(() -> {
                    mMonitoringRepository.deleteItem(item);
                    return true;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        aBoolean -> {
                            operationSuccess.postValue(true);
                        },
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
