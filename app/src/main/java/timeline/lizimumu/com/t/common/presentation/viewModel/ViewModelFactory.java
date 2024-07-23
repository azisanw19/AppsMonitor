package timeline.lizimumu.com.t.common.presentation.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import timeline.lizimumu.com.t.common.data.source.db.DbIgnoreExecutor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(IgnoreExecutorViewModel.class)) {
            DbIgnoreExecutor dbIgnoreExecutor = DbIgnoreExecutor.getInstance();
            return (T) new IgnoreExecutorViewModel(dbIgnoreExecutor);
        }

        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
