package es.santirivera.surveilfall.activity;


import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import es.santirivera.surveilfall.R;
import es.santirivera.surveilfall.base.activity.BaseActivity;
import es.santirivera.surveilfall.data.model.Set;
import es.santirivera.surveilfall.fragment.setlist.SetListFragment;
import es.santirivera.surveilfall.fragment.setlist.SetListListener;

public class MainActivity extends BaseActivity implements SetListListener {

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void prepareInterface() {
        SetListFragment fragment = new SetListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    @Override
    public void onSetClicked(@NotNull Set set) {
        Toast.makeText(this, set.getName(), Toast.LENGTH_SHORT).show();
    }
}
