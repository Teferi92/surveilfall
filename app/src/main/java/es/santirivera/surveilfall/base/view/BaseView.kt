package es.santirivera.surveilfall.base.view

import android.view.View

import butterknife.ButterKnife
import es.santirivera.surveilfall.base.activity.BaseActivity


abstract class BaseView<ViewInterfaceType>(protected val baseActivity: BaseActivity, protected val presenter: ViewInterfaceType) {

    protected var mainView: View? = null

    abstract val contentView: Int

    fun setupLayout(mainView: View) {
        this.mainView = mainView
        ButterKnife.bind(this, mainView)
        prepareView()
    }

    protected abstract fun prepareView()
}
