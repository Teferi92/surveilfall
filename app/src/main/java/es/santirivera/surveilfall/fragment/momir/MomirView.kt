package es.santirivera.surveilfall.fragment.momir

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.CardAdapter
import es.santirivera.surveilfall.adapter.SimpleStringAdapter
import es.santirivera.surveilfall.adapter.viewholder.CardViewHolder
import es.santirivera.surveilfall.adapter.viewholder.StringViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card

class MomirView(activity: BaseActivity, presenter: MomirListener) : BaseView<MomirListener>(activity, presenter), CardViewHolder.OnCardClickedListener, StringViewHolder.OnStringClickedListener {


    var recyclerView: RecyclerView? = null
    var fab: FloatingActionButton? = null
    private val cardAdapter: CardAdapter = CardAdapter(this, false)
    private val cmcs = generateCmcList()
    private val helpDialog: AlertDialog = AlertDialog.Builder(baseActivity)
            .setTitle(R.string.momir_help)
            .setPositiveButton(R.string.ok) { _: DialogInterface, _: Int ->
                dismiss()
            }
            .setMessage(R.string.momir_message)
            .create()

    private val cmcRecyclerView: RecyclerView = View.inflate(activity, R.layout.fragment_simple_list, null) as RecyclerView
    private val cmcAdapter: SimpleStringAdapter = SimpleStringAdapter(cmcs, this, true)

    init {
        cmcRecyclerView.layoutManager = GridLayoutManager(activity, 4)
        cmcRecyclerView.adapter = cmcAdapter
    }

    private val cmcDialog: AlertDialog = AlertDialog.Builder(baseActivity)
            .setTitle(R.string.choose_cmc)
            .setView(cmcRecyclerView)
            .create()

    override fun getContentView(): Int {
        return R.layout.fragment_momir
    }

    override fun prepareView() {
        fab = mainView.findViewById(R.id.fabAddCard)
        fab!!.setOnClickListener {
            cmcDialog.show()
        }
        recyclerView = mainView.findViewById(R.id.recyclerViewCards)
        recyclerView!!.layoutManager = GridLayoutManager(baseActivity, 2)
        recyclerView!!.adapter = cardAdapter
    }

    fun onCardReceived(card: Card) {
        cardAdapter.addCard(card)
        cardAdapter.notifyDataSetChanged()
    }

    override fun onCardClicked(card: Card) {
        presenter.onCardClicked(card)
    }

    fun showHelp() {
        helpDialog.show()
    }

    private fun dismiss() {
        helpDialog.dismiss()
        cmcDialog.dismiss()
    }

    private fun generateCmcList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..16) {
            list.add(i.toString())
        }
        return list
    }

    override fun onStringClicked(item: String) {
        presenter.onCardRequested(item.toInt())
        dismiss()
    }
}