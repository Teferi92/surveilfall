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

    private lateinit var callback: ListenerType

    @set:Inject
    lateinit var useCaseHandler: UseCaseHandler

    @set:Inject
    lateinit var useCaseProvider: UseCaseProvider

    private lateinit var baseView: BaseView<*>

    abstract val titleForActivity: String?


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = try {
            if (parentFragment != null) {
                parentFragment as ListenerType
            } else {
                context as ListenerType
            }
        } catch (e: ClassCastException) {
            throw RuntimeException(context.toString() + " must implement " + callback.javaClass.simpleName)
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
        if (title != null && title.isNotEmpty() && activity != null) {
            activity.title = title
        }
        activity!!.setDrawerEnabled(showMenu)
    }

    fun updateTitle() {
        val title = titleForActivity
        val activity = activity as BaseActivity?
        if (title != null && title.isNotEmpty() && activity != null) {
            activity.title = title
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseView = instanceView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainView = inflater.inflate(baseView.contentView, container, false)
        ButterKnife.bind(this, mainView)
        baseView.setupLayout(mainView)
        return mainView
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
