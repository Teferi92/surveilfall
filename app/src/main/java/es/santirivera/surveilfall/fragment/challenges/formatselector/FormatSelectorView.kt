package es.santirivera.surveilfall.fragment.challenges.formatselector

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.FormatAdapter
import es.santirivera.surveilfall.adapter.viewholder.FormatViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Format

class FormatSelectorView(activity: BaseActivity, presenter: FormatSelectorListener) : BaseView<FormatSelectorListener>(activity, presenter), FormatViewHolder.OnFormatClickedListener {

    override val contentView: Int get() = R.layout.fragment_simple_list
    private var formatAdapter = FormatAdapter(this)
    private lateinit var recyclerView: RecyclerView

    override fun prepareView() {
        recyclerView = mainView.findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(baseActivity, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = formatAdapter
    }

    override fun onFormatClicked(format: Format, view: View) {
        presenter.onFormatSelected(format.n)
    }

    fun onFormatsLoaded(formats: List<Format>) {
        formatAdapter.addFormats(formats)
    }

}