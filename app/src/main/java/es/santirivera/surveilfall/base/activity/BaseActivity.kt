package es.santirivera.surveilfall.base.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import es.santirivera.surveilfall.base.app.AndroidApplication
import es.santirivera.surveilfall.di.components.ActivityComponent
import es.santirivera.surveilfall.di.components.DaggerActivityComponent
import es.santirivera.surveilfall.di.modules.ApplicationModule
import es.santirivera.surveilfall.domain.usecases.base.UseCaseHandler
import es.santirivera.surveilfall.domain.usecases.providers.UseCaseProvider
import javax.inject.Inject
import android.R
import es.santirivera.surveilfall.base.interfaces.BaseNavigation


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

    fun hideKeyboard(v: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.let { imm.hideSoftInputFromWindow(v.windowToken, 0) }
    }

}
