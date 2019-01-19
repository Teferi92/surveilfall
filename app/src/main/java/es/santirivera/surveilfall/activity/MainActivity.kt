package es.santirivera.surveilfall.activity


import android.content.Context
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.LeftDrawerAdapter
import es.santirivera.surveilfall.adapter.drawer.DrawerItem
import es.santirivera.surveilfall.adapter.viewholder.DrawerViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Set
import es.santirivera.surveilfall.domain.usecases.GetCardsForQueryUseCase
import es.santirivera.surveilfall.fragment.artists.list.ArtistListFragment
import es.santirivera.surveilfall.fragment.artists.list.ArtistListListener
import es.santirivera.surveilfall.fragment.cards.CardListFragment
import es.santirivera.surveilfall.fragment.cards.detail.CardDetailFragment
import es.santirivera.surveilfall.fragment.cards.detail.CardDetailListener
import es.santirivera.surveilfall.fragment.momir.MomirFragment
import es.santirivera.surveilfall.fragment.search.SearchFragment
import es.santirivera.surveilfall.fragment.search.SearchListener
import es.santirivera.surveilfall.fragment.setlist.CardListListener
import es.santirivera.surveilfall.fragment.setlist.SetListFragment
import es.santirivera.surveilfall.fragment.setlist.SetListListener

class MainActivity : BaseActivity(),
        SetListListener,
        ArtistListListener,
        CardListListener,
        CardDetailListener,
        SearchListener,
        DrawerViewHolder.OnDrawerItemClickedListener {


    override fun onDrawerItemClicked(item: DrawerItem) {
        when (item) {
            DrawerItem.SEARCH -> openSearch()
            DrawerItem.SETS -> openSets()
            DrawerItem.ARTISTS -> openArtists()
            DrawerItem.MOMIR -> openMomir()
        }
        drawerLayout?.closeDrawer(GravityCompat.START)
    }

    private var drawerList: RecyclerView? = null
    private var drawerLayout: DrawerLayout? = null
    private var drawerToggle: ActionBarDrawerToggle? = null

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun prepareInterface() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        drawerList = findViewById(R.id.leftDrawer)
        drawerLayout = findViewById(R.id.drawerLayout)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name)

        drawerToggle!!.isDrawerIndicatorEnabled = true
        drawerToggle!!.syncState()

        drawerLayout!!.addDrawerListener(drawerToggle!!)

        drawerList!!.layoutManager = LinearLayoutManager(this)
        drawerList!!.adapter = LeftDrawerAdapter(this)

        openSearch()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else if (item.itemId == android.R.id.home){
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }


    override fun onSetClicked(set: Set) {
        executeQuery("e:${set.code}", set.name, true, GetCardsForQueryUseCase.PrintsToInclude.CARDS)
    }

    override fun onArtistClicked(artist: String) {
        executeQuery("a:\"$artist\"", artist, true, GetCardsForQueryUseCase.PrintsToInclude.PRINTS)
    }

    override fun onCardClicked(card: Card) {
        openCard(card, true)
    }

    override fun onBottomReached(currentPage: Int) {
        // Never called
    }

    override fun onDownloadRequested() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResolutionSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRulingsRequested() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShareRequested() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAddToFavoritesRequested() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchClicked(query: String) {
        hideKeyboard()
        if (query != "") {
            executeQuery(query, getString(R.string.search), true, GetCardsForQueryUseCase.PrintsToInclude.CARDS)
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onRandomClicked(query: String) {
    }

    private fun openSets() {
        supportFragmentManager.popBackStack()
        val fragment = SetListFragment()
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun openArtists() {
        supportFragmentManager.popBackStack()
        val fragment = ArtistListFragment()
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.content, fragment)
                .commit()
        setDrawerEnabled(true)
    }

    private fun openSearch() {
        supportFragmentManager.popBackStack()
        val fragment = SearchFragment()
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun openMomir(){
        supportFragmentManager.popBackStack()
        val fragment = MomirFragment()
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun executeQuery(query: String, title: String, addToBackStack: Boolean, prints: GetCardsForQueryUseCase.PrintsToInclude) {
        val fragment = CardListFragment()
        fragment.query = query
        fragment.fragmentTitle = title
        fragment.prints = prints
        var transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction = transaction.addToBackStack("cardListQuery")
        } else {
            transaction.disallowAddToBackStack()
        }
        transaction = transaction.replace(R.id.content, fragment)
        transaction.commit()
    }

    private fun openCard(card: Card, addToBackStack: Boolean) {
        val fragment = CardDetailFragment()
        fragment.card = card
        var transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction = transaction.addToBackStack("cardDetail")
        } else {
            transaction.disallowAddToBackStack()
        }
        transaction = transaction.replace(R.id.content, fragment)
        transaction.commit()
    }

    override fun setDrawerEnabled(enable: Boolean) {
        val lockMode = if (enable)
            DrawerLayout.LOCK_MODE_UNLOCKED
        else
            DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        drawerLayout!!.setDrawerLockMode(lockMode)
        drawerToggle!!.isDrawerIndicatorEnabled = enable
        drawerToggle!!.syncState()
    }

}
