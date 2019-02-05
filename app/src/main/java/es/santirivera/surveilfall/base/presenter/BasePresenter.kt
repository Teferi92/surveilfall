package es.santirivera.surveilfall.base.presenter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.interfaces.BaseNavigation
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.domain.usecases.base.UseCaseHandler
import es.santirivera.surveilfall.domain.usecases.providers.UseCaseProvider
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
abstract class BasePresenter<ListenerType : BaseNavigation> : Fragment() {

    protected val LOG_TAG: String = javaClass.simpleName

    protected var callback: ListenerType? = null

    @set:Inject
    var useCaseHandler: UseCaseHandler? = null

    @set:Inject
    var useCaseProvider: UseCaseProvider? = null

    protected var baseView: BaseView<*>? = null

    abstract val titleForActivity: String?

    interface ViewCreatedListener {
        fun onViewCreated(view: View)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = parentFragment as ListenerType?
            if (callback == null) {
                callback = context as ListenerType
            }
        } catch (e: ClassCastException) {
            throw RuntimeException(context.toString() + " must implement " + callback!!.javaClass.simpleName)
        }
        if (context is BaseActivity) {
            (this.context as BaseActivity).component().inject(this as BasePresenter<BaseNavigation>)
        } else {
            throw RuntimeException("baseActivity not instanceof BaseActivity")
        }
    }

    override fun onResume() {
        super.onResume()
        val title = titleForActivity
        val showMenu = shouldShowMenu()
        val activity = activity as BaseActivity?
        if (title != null && title.length > 0 && activity != null) {
            activity.title = title
        }
        activity!!.setDrawerEnabled(showMenu)
    }

    fun updateTitle() {
        val title = titleForActivity
        val activity = activity as BaseActivity?
        if (title != null && title.length > 0 && activity != null) {
            activity.title = title
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseView = instanceView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainView = inflater.inflate(baseView!!.contentView, container, false)
        setupPresenterLayout(mainView)
        ButterKnife.bind(this, mainView)
        baseView!!.setupLayout(mainView)
        return mainView
    }

    private fun setupPresenterLayout(mainView: View) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadViewData()
    }


    fun backPressed(): Boolean {
        return false
    }

    protected abstract fun instanceView(): BaseView<*>

    protected abstract fun loadViewData()

    abstract fun shouldShowMenu(): Boolean
}
