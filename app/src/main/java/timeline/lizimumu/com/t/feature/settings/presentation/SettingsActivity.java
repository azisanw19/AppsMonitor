package timeline.lizimumu.com.t.feature.settings.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Locale;

import timeline.lizimumu.com.t.AppConst;
import timeline.lizimumu.com.t.BuildConfig;
import timeline.lizimumu.com.t.R;
import timeline.lizimumu.com.t.common.presentation.viewModel.PreferenceViewModel;
import timeline.lizimumu.com.t.common.presentation.viewModel.ViewModelFactory;
import timeline.lizimumu.com.t.feature.about.presentation.AboutActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch mSwitchSystem;
    Switch mSwitchUninstall;
    Switch mSwitchOpenable;

    private PreferenceViewModel preferenceViewModel;

    private Boolean mHideSystem = false;
    private Boolean mHideUninstall = false;
    private Boolean mHideOpenable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ViewModelFactory viewModelFactory = new ViewModelFactory();
        preferenceViewModel = new ViewModelProvider(this, viewModelFactory).get(PreferenceViewModel.class);
        preferenceViewModel.getPreferenceHideSystem();
        preferenceViewModel.getPreferenceHideUninstall();
        preferenceViewModel.getPreferenceHideOpenable();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.settings);
        }

        // hide system
        mSwitchSystem = findViewById(R.id.switch_system_apps);
        mSwitchSystem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mHideSystem != b) {
                    preferenceViewModel.setPreferenceHideSystem(b);
                    setResult(1);
                }
            }
        });

        findViewById(R.id.group_system).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwitchSystem.performClick();
            }
        });

        // hide uninstall
        mSwitchUninstall = findViewById(R.id.switch_uninstall_appps);
        mSwitchUninstall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mHideUninstall != b) {
                    preferenceViewModel.setPreferenceHideUninstall(b);
                    setResult(1);
                }
            }
        });

        findViewById(R.id.group_uninstall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwitchUninstall.performClick();
            }
        });

        // hide openable
        mSwitchOpenable = findViewById(R.id.switch_openable_appps);
        mSwitchOpenable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mHideOpenable != isChecked) {
                    preferenceViewModel.setPreferenceHideOpenable(isChecked);
                    setResult(1);
                }
            }
        });

        findViewById(R.id.group_openable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwitchOpenable.performClick();
            }
        });

        // ignore list
        findViewById(R.id.group_ignore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, IgnoreActivity.class));
            }
        });

        // about
        findViewById(R.id.group_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
            }
        });

        // share
        findViewById(R.id.group_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareText = getResources().getString(R.string.share_desc);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        String.format(Locale.getDefault(), shareText, AppConst.GP_DETAIL_PREFIX, BuildConfig.APPLICATION_ID));
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        observeData();
    }

    private void observeData() {
        preferenceViewModel.preferenceHideSystem.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mHideSystem = aBoolean;
                mSwitchSystem.setChecked(aBoolean);
            }
        });

        preferenceViewModel.preferenceHideUninstall.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mHideUninstall = aBoolean;
                mSwitchUninstall.setChecked(aBoolean);
            }
        });

        preferenceViewModel.preferenceHideOpenable.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mHideOpenable = aBoolean;
                mSwitchOpenable.setChecked(aBoolean);
            }
        });
    }
}
