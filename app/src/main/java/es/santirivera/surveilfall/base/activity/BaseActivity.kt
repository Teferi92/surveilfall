package es.santirivera.surveilfall.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import es.santirivera.surveilfall.base.app.AndroidApplication
import es.santirivera.surveilfall.di.components.ActivityComponent
import es.santirivera.surveilfall.di.components.DaggerActivityComponent
import es.santirivera.surveilfall.di.modules.ApplicationModule
import es.santirivera.surveilfall.domain.usecases.base.UseCaseHandler
import es.santirivera.surveilfall.domain.usecases.providers.UseCaseProvider
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    private var component: ActivityComponent? = null

    @set:Inject
    lateinit var useCaseProvider: UseCaseProvider
    @set:Inject
    lateinit var useCaseHandler: UseCaseHandler

    protected abstract val contentView: Int

    private val baseApplication: AndroidApplication
        get() = application as AndroidApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnCreate()
    }

    private fun setOnCreate() {
        if (contentView > 0) {
            setContentView(contentView)
            ButterKnife.bind(this)
            component().inject(this)
            prepareInterface()
        }
    }


    fun component(): ActivityComponent {
        if (component == null) {
            component = DaggerActivityComponent
                    .builder().applicationModule(ApplicationModule(baseApplication)).build()
        }
        return component!!
    }

    protected abstract fun prepareInterface()

    abstract fun setDrawerEnabled(show: Boolean)

}
