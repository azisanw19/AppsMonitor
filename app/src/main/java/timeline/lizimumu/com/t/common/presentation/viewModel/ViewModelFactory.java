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

        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
