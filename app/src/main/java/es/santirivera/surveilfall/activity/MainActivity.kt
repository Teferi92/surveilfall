package es.santirivera.surveilfall.activity


import android.view.MenuItem
import android.widget.Toast
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
import es.santirivera.surveilfall.fragment.artists.list.ArtistListFragment
import es.santirivera.surveilfall.fragment.artists.list.ArtistListListener
import es.santirivera.surveilfall.fragment.cards.CardListFragment
import es.santirivera.surveilfall.fragment.cards.detail.CardDetailFragment
import es.santirivera.surveilfall.fragment.setlist.CardListListener
import es.santirivera.surveilfall.fragment.setlist.SetListFragment
import es.santirivera.surveilfall.fragment.setlist.SetListListener

class MainActivity : BaseActivity(),
        SetListListener,
        ArtistListListener,
        CardListListener,
        DrawerViewHolder.OnDrawerItemClickedListener {

    override fun onDrawerItemClicked(item: DrawerItem) {
        when (item) {
            DrawerItem.SEARCH -> openSets()
            DrawerItem.SETS -> openSets()
            DrawerItem.ARTISTS -> openArtists()
            DrawerItem.SETTINGS -> openSets()
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

        if (intent == null || intent.data == null) {
            openArtists()
        } else {
            val uri = intent.data!!
            val path = uri.path!!
            when {
                path == "/search" -> executeQuery(uri.getQueryParameter("q")!!, "Query from URL", false)
                path.startsWith("/card") -> {
                    try {
                        executeCardQuery(uri.pathSegments[1], uri.pathSegments[2].toInt(), false)
                    } catch (e: Exception) {
                        // Malformed URI
                        Toast.makeText(this, "Malformed scryfall URI", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                else -> openArtists()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onSetClicked(set: Set) {
        executeQuery("e:${set.code}", set.name, true)
    }

    override fun onArtistClicked(artist: String) {
        executeQuery("a:\"$artist\"", artist, true)
    }

    override fun onCardClicked(card: Card) {
        Toast.makeText(this, card.name, Toast.LENGTH_SHORT).show()
    }

    override fun onBottomReached(currentPage: Int) {
        // Never called
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
    }

    private fun executeQuery(query: String, title: String, addToBackStack: Boolean) {
        val fragment = CardListFragment()
        fragment.query = query
        fragment.fragmentTitle = title
        var transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction = transaction.addToBackStack("cardListQuery")
        } else {
            transaction.disallowAddToBackStack()
        }
        transaction = transaction.replace(R.id.content, fragment)
        transaction.commit()
    }

    private fun executeCardQuery(setCode: String, cardInSet: Int, addToBackStack: Boolean) {
        val fragment = CardDetailFragment()
        fragment.setCode = setCode
        fragment.cardInSet = cardInSet
        var transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction = transaction.addToBackStack("cardQuery")
        } else {
            transaction.disallowAddToBackStack()
        }
        transaction = transaction.replace(R.id.content, fragment)
        transaction.commit()
    }

}
