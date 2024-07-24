package timeline.lizimumu.com.t.common.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timeline.lizimumu.com.t.BuildConfig;
import timeline.lizimumu.com.t.common.domain.model.AppItem;
import timeline.lizimumu.com.t.common.data.repository.MonitoringRepository;


public class IgnoreExecutorViewModel extends ViewModel {

    private final MonitoringRepository mMonitoringRepository;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public final MutableLiveData<Boolean> operationSuccess = new MutableLiveData<>();
    public final MutableLiveData<String> operationError = new MutableLiveData<>();

    public IgnoreExecutorViewModel(MonitoringRepository monitoringRepository) {
        mMonitoringRepository = monitoringRepository;
    }

    public void addDefaultIgnoreAppsToDB() {
        Disposable disposable = Single.fromCallable(() -> {
                    List<String> mDefaults = new ArrayList<>();
                    mDefaults.add("com.android.settings");
                    mDefaults.add(BuildConfig.APPLICATION_ID);

                    for (String packageName : mDefaults) {
                        AppItem item = new AppItem();
                        item.mPackageName = packageName;
                        item.mEventTime = System.currentTimeMillis();
                        mMonitoringRepository.insertItem(item);
                    }
                    return true;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            operationSuccess.postValue(true);
                        },
                        throwable -> {
                            operationError.postValue(throwable.toString());
                        }
                );
        compositeDisposable.add(disposable);
    }

    public void addIgnoreAppsToDB(String packageName) {
        Disposable disposable = Single.fromCallable(() -> {
                    mMonitoringRepository.insertItem(packageName);
                    return true;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            operationSuccess.postValue(true);
                        },
                        throwable -> {
                            operationError.postValue(throwable.toString());
                        }
                );
        compositeDisposable.add(disposable);
    }

    public void addIgnoreAppsToDB(AppItem item) {
        Disposable disposable = Single.fromCallable(() -> {
                    mMonitoringRepository.insertItem(item);
                    return true;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
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
