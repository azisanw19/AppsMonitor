package timeline.lizimumu.com.t.common.presentation.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import timeline.lizimumu.com.t.di.AppModule;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(IgnoreExecutorViewModel.class)) {
            return (T) new IgnoreExecutorViewModel(AppModule.getInstance().getMonitoringRepository());
        }
        if (modelClass.isAssignableFrom(CheckPermissionDataViewModel.class)) {
            return (T) new CheckPermissionDataViewModel(AppModule.getInstance().getDataManagerRepository());
        }
        if (modelClass.isAssignableFrom(MonitoringAppsViewModel.class)) {
            return (T) new MonitoringAppsViewModel(AppModule.getInstance().getDataManagerRepository());
        }
        if (modelClass.isAssignableFrom(MonitoringAppsTargetViewModel.class)) {
            return (T) new MonitoringAppsTargetViewModel(AppModule.getInstance().getDataManagerRepository());
        }
        if (modelClass.isAssignableFrom(ListIgnoreAppsViewModel.class)) {
            return (T) new ListIgnoreAppsViewModel(AppModule.getInstance().getMonitoringRepository());
        }
        if (modelClass.isAssignableFrom(DeleteIgnoreExecutorViewModel.class)) {
            return (T) new DeleteIgnoreExecutorViewModel(AppModule.getInstance().getMonitoringRepository());
        }

        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
