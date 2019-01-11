package es.santirivera.surveilfall.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import es.santirivera.surveilfall.base.app.AndroidApplication;
import es.santirivera.surveilfall.di.modules.ActivityModule;
import es.santirivera.surveilfall.di.modules.ApplicationModule;
import es.santirivera.surveilfall.di.modules.DomainModule;
import es.santirivera.surveilfall.di.modules.GsonModule;
import es.santirivera.surveilfall.di.modules.RepositoryModule;
import es.santirivera.surveilfall.di.modules.WebServicesModule;
import es.santirivera.surveilfall.domain.use_cases.base.UseCaseHandler;
import es.santirivera.surveilfall.domain.use_cases.providers.UseCaseProvider;

@Singleton
@Component(
        modules = {
                ActivityModule.class,
                ApplicationModule.class,
                GsonModule.class,
                DomainModule.class,
                RepositoryModule.class,
                WebServicesModule.class
        })
public interface ApplicationComponent {

    void inject(AndroidApplication application);

    // Exported to child components
    AndroidApplication application();

    Context context();

    UseCaseProvider useCaseProvider();

    UseCaseHandler useCaseHandler();

}

