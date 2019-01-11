package es.santirivera.surveilfall.di.modules;


import dagger.Module;
import dagger.Provides;
import es.santirivera.surveilfall.base.activity.BaseActivity;

@Module
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    BaseActivity provideActivity() {
        return this.activity;
    }

}