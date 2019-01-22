package es.santirivera.surveilfall.di.modules


import dagger.Module
import dagger.Provides
import es.santirivera.surveilfall.base.activity.BaseActivity

@Module
class ActivityModule(private val activity: BaseActivity) {

    @Provides
    internal fun provideActivity(): BaseActivity {
        return this.activity
    }

}