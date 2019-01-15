package es.santirivera.surveilfall.fragment.setlist

import java.util.ArrayList

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.SetAdapter
import es.santirivera.surveilfall.adapter.viewholder.SetViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Set

class SetListView(baseActivity: BaseActivity, presenter: SetListListener) : BaseView<SetListListener>(baseActivity, presenter), SetViewHolder.OnSetClickedListener {

    private var recyclerView: RecyclerView? = null

    override fun getContentView(): Int {
        return R.layout.fragment_simple_list
    }

    override fun prepareView() {
        recyclerView = mainView.findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = GridLayoutManager(baseActivity, 4)
    }

    fun onSetsReceived(sets: List<Set>) {
        recyclerView!!.adapter = SetAdapter(sets, this)
    }

    override fun onSetClicked(set: Set) {
        presenter.onSetClicked(set)
    }
}
