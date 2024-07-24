package timeline.lizimumu.com.t.feature.settings.presentation;

import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timeline.lizimumu.com.t.GlideApp;
import timeline.lizimumu.com.t.R;
import timeline.lizimumu.com.t.common.domain.model.IgnoreItem;
import timeline.lizimumu.com.t.common.presentation.viewModel.DeleteIgnoreExecutorViewModel;
import timeline.lizimumu.com.t.common.presentation.viewModel.ListIgnoreAppsViewModel;
import timeline.lizimumu.com.t.common.presentation.viewModel.ViewModelFactory;
import timeline.lizimumu.com.t.util.AppUtil;

public class IgnoreActivity extends AppCompatActivity {

    private IgnoreAdapter mAdapter;

    private ListIgnoreAppsViewModel listIgnoreAppsViewModel;
    private DeleteIgnoreExecutorViewModel deleteIgnoreExecutorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ignore);

        ViewModelFactory viewModelFactory = new ViewModelFactory();
        listIgnoreAppsViewModel = new ViewModelProvider(this, viewModelFactory).get(ListIgnoreAppsViewModel.class);
        deleteIgnoreExecutorViewModel = new ViewModelProvider(this, viewModelFactory).get(DeleteIgnoreExecutorViewModel.class);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.ignore);
        }

        RecyclerView mList = findViewById(R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new IgnoreAdapter();
        mList.setAdapter(mAdapter);
        observeData();

        listIgnoreAppsViewModel.getAllItems();
    }

    class IgnoreAdapter extends RecyclerView.Adapter<IgnoreAdapter.IgnoreViewHolder> {

        private List<IgnoreItem> mData;

        IgnoreAdapter() {
            mData = new ArrayList<>();
        }

        void setData(List<IgnoreItem> data) {
            mData = data;
            notifyDataSetChanged();
        }

        @Override
        public IgnoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_ignore, parent, false);
            return new IgnoreViewHolder(view);
        }

        @Override
        public void onBindViewHolder(IgnoreViewHolder holder, int position) {
            IgnoreItem item = mData.get(position);
            holder.mCreated.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(new Date(item.mCreated)));
            holder.mName.setText(item.mName);
            GlideApp.with(getApplicationContext())
                    .load(AppUtil.getPackageIcon(getApplicationContext(), item.mPackageName))
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(holder.mIcon);
            holder.setOnClickListener(item);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class IgnoreViewHolder extends RecyclerView.ViewHolder {

            private ImageView mIcon;
            private TextView mName;
            private TextView mCreated;

            IgnoreViewHolder(View itemView) {
                super(itemView);
                mIcon = itemView.findViewById(R.id.app_image);
                mName = itemView.findViewById(R.id.app_name);
                mCreated = itemView.findViewById(R.id.app_time);
            }

            void setOnClickListener(final IgnoreItem item) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteIgnoreExecutorViewModel.deleteItem(item);
                    }
                });
            }
        }
    }

    private void observeData() {
        listIgnoreAppsViewModel.operationSuccess.observe(this, ignoreItems -> {
            if (ignoreItems.size() > 0) {
                for (IgnoreItem item : ignoreItems) {
                    item.mName = AppUtil.parsePackageName(IgnoreActivity.this.getPackageManager(), item.mPackageName);
                }
                mAdapter.setData(ignoreItems);
            }
        });

        deleteIgnoreExecutorViewModel.operationSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                listIgnoreAppsViewModel.getAllItems();
            }
        });
    }


}
