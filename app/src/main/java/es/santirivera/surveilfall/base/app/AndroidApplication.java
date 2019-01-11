package es.santirivera.surveilfall.base.app;

import android.app.Application;

import es.santirivera.surveilfall.di.components.ApplicationComponent;
import es.santirivera.surveilfall.di.components.DaggerApplicationComponent;
import es.santirivera.surveilfall.di.modules.ApplicationModule;

public class AndroidApplication extends Application {

    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        initDependencies();
    }

    private void initDependencies() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }
}
