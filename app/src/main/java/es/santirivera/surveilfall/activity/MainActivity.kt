package es.santirivera.surveilfall.activity


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.LeftDrawerAdapter
import es.santirivera.surveilfall.adapter.drawer.DrawerItem
import es.santirivera.surveilfall.adapter.viewholder.DrawerViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Deck
import es.santirivera.surveilfall.data.model.ImageUris
import es.santirivera.surveilfall.data.model.Set
import es.santirivera.surveilfall.domain.usecases.implementation.bitmap.GetBitmapFromURLUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.cards.GetCardsForQueryUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.domain.usecases.implementation.favorite.ClearFavoritesUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.wordbank.UpdateWordBankUseCase
import es.santirivera.surveilfall.fragment.artists.list.ArtistListFragment
import es.santirivera.surveilfall.fragment.artists.list.ArtistListListener
import es.santirivera.surveilfall.fragment.cards.detail.CardDetailFragment
import es.santirivera.surveilfall.fragment.cards.detail.CardDetailListener
import es.santirivera.surveilfall.fragment.cards.favorites.FavoritesListFragment
import es.santirivera.surveilfall.fragment.cards.favorites.FavoritesListListener
import es.santirivera.surveilfall.fragment.cards.list.CardListFragment
import es.santirivera.surveilfall.fragment.challenges.detail.FormatTournamentListFragment
import es.santirivera.surveilfall.fragment.challenges.detail.FormatTournamentListListener
import es.santirivera.surveilfall.fragment.challenges.formatselector.FormatSelectorFragment
import es.santirivera.surveilfall.fragment.challenges.formatselector.FormatSelectorListener
import es.santirivera.surveilfall.fragment.challenges.list.ChallengeDecksListFragment
import es.santirivera.surveilfall.fragment.challenges.list.ChallengeDecksListListener
import es.santirivera.surveilfall.fragment.momir.MomirFragment
import es.santirivera.surveilfall.fragment.search.SearchFragment
import es.santirivera.surveilfall.fragment.search.SearchListener
import es.santirivera.surveilfall.fragment.setlist.SetListFragment
import es.santirivera.surveilfall.fragment.setlist.SetListListener
import es.santirivera.surveilfall.fragment.settings.SettingsFragment
import java.io.File
import java.io.FileOutputStream

class MainActivity : BaseActivity(),
        SetListListener,
        ArtistListListener,
        FavoritesListListener,
        CardDetailListener,
        SearchListener,
        FormatSelectorListener,
        FormatTournamentListListener,
        ChallengeDecksListListener,
        DrawerViewHolder.OnDrawerItemClickedListener {


    var currentFragment: Fragment = SearchFragment()

    override val contentView: Int
        get() = R.layout.activity_main


    override fun onDrawerItemClicked(item: DrawerItem) {
        when (item) {
            DrawerItem.SEARCH -> openSearch()
            DrawerItem.SETS -> openSets()
            DrawerItem.ARTISTS -> openArtists()
            DrawerItem.MOMIR -> openMomir()
            DrawerItem.FAVORITES -> openFavorites()
            DrawerItem.SETTINGS -> openSettings()
            DrawerItem.CHALLENGES -> openChallenges()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }


    private lateinit var drawerList: RecyclerView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar

    private var lastUris: ImageUris? = null
    private var lastDownloadCardRequestName: String? = null

    private val codePermissionWriteStorage = 1337


    override fun prepareInterface() {
        setupToolbar()

        drawerList = findViewById(R.id.leftDrawer)
        drawerLayout = findViewById(R.id.drawerLayout)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name)

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        drawerLayout.addDrawerListener(drawerToggle)

        drawerList.layoutManager = LinearLayoutManager(this)
        drawerList.adapter = LeftDrawerAdapter(this)

        openSearch()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            drawerToggle.onOptionsItemSelected(item) -> true
            item.itemId == android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSetClicked(set: Set) {
        executeQuery(
                query = "e:${set.code}",
                title = set.name,
                addToBackStack = true,
                isQueryEditable = false)
    }

    override fun onArtistClicked(artist: String) {
        executeQuery(
                query = "a:\"$artist\"",
                title = artist,
                addToBackStack = true,
                isQueryEditable = false,
                prints = GetCardsForQueryUseCase.PrintsToInclude.PRINTS)
    }

    override fun onShowReprintsClicked(card: Card?) {
        executeQuery(
                query = "oracleId:${card!!.oracleId}",
                title = card.name,
                addToBackStack = true,
                isQueryEditable = false,
                prints = GetCardsForQueryUseCase.PrintsToInclude.PRINTS,
                sortMethod = GetCardsForQueryUseCase.SortMethod.RELEASED
        )
    }

    override fun onCardClicked(card: Card, view: View) {
        openCard(card, true, view)
    }

    override fun onNewQuery(query: String) {
        // Do nothing
    }

    override fun isFavorite() {
        // Never called
    }

    override fun toggleFavoriteAction(favorite: Boolean) {
        // Never called
    }


    override fun onSaveCardArtRequested(uris: ImageUris, cardName: String) {
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            val qualities = arrayOf("Complete card (png)", "Complete card (jpg)", "Card art crop")
            val dialog = AlertDialog.Builder(this).setItems(qualities) { dialog, which ->
                when (which) {
                    0 -> downloadImage(uris.png, cardName, ".png")
                    1 -> downloadImage(uris.large, cardName, ".jpg")
                    2 -> downloadImage(uris.artCrop, cardName, ".jpg")
                }
                dialog.dismiss()
            }.setTitle(R.string.choose_card_quality)
            dialog.show()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                val dialog = AlertDialog.Builder(this)
                        .setMessage(R.string.request_permission_storage)
                        .setTitle(R.string.app_name)
                        .setPositiveButton(R.string.ok) { dialog, _ ->
                            dialog.dismiss()
                        }
                dialog.show()
            } else {
                lastDownloadCardRequestName = cardName
                lastUris = uris
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        codePermissionWriteStorage)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            codePermissionWriteStorage -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (lastUris != null && lastDownloadCardRequestName != null) {
                        onSaveCardArtRequested(lastUris!!, lastDownloadCardRequestName!!)
                    }
                }
                return
            }
        }
    }

    private fun downloadImage(url: String, cardName: String, extension: String) {
        useCaseHandler.execute(
                useCaseProvider.getBitmapFromURLUseCase,
                GetBitmapFromURLUseCase.Input(url),
                object : UseCasePartialCallback<GetBitmapFromURLUseCase.OkOutput, GetBitmapFromURLUseCase.ErrorOutput>() {

                    override fun onSuccess(tag: String?, response: GetBitmapFromURLUseCase.OkOutput) {
                        saveBitmap(response.bitmap, cardName, extension)
                    }
                })
    }

    private fun saveBitmap(bitmap: Bitmap, cardName: String, extension: String) {
        val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val myDir = File("$root/surveilfall")
        myDir.mkdirs()
        val fileName = cardName + System.currentTimeMillis() + extension
        val file = File(myDir, fileName)
        if (file.exists()) file.delete()

        val compress = when (extension) {
            ".png" -> Bitmap.CompressFormat.PNG
            else -> Bitmap.CompressFormat.JPEG
        }
        try {
            val out = FileOutputStream(file)
            bitmap.compress(compress, 90, out)
            out.flush()
            out.close()
            MediaScannerConnection.scanFile(this,
                    arrayOf(file.toString()),
                    null
            ) { _, _ -> }
            Toast.makeText(this, "Saved to gallery", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onSearchClicked(query: String, listener: SearchListener) {
        hideKeyboard()
        if (query != "") {
            executeQuery(
                    query = query,
                    title = getString(R.string.search),
                    addToBackStack = true,
                    isQueryEditable = true,
                    prints = GetCardsForQueryUseCase.PrintsToInclude.CARDS,
                    sortMethod = GetCardsForQueryUseCase.SortMethod.NAME,
                    sortOrder = GetCardsForQueryUseCase.SortOrder.AUTO,
                    listener = listener)

        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    private fun openSets() {
        supportFragmentManager.popBackStack()
        val fragment = SetListFragment()
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun openArtists() {
        supportFragmentManager.popBackStack()
        val fragment = ArtistListFragment()
        currentFragment = fragment
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit()
        setDrawerEnabled(true)
    }

    private fun openSearch() {
        supportFragmentManager.popBackStack()
        val fragment = SearchFragment()
        currentFragment = fragment
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun openMomir() {
        supportFragmentManager.popBackStack()
        val fragment = MomirFragment()
        currentFragment = fragment
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun openFavorites() {
        supportFragmentManager.popBackStack()
        val fragment = FavoritesListFragment()
        currentFragment = fragment
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun openSettings() {
        supportFragmentManager.popBackStack()
        val fragment = SettingsFragment()
        currentFragment = fragment
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun openChallenges() {
        supportFragmentManager.popBackStack()
        val fragment = FormatSelectorFragment()
        currentFragment = fragment
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit()
    }

    override fun onFormatSelected(format: String) {
        val fragment = FormatTournamentListFragment()
        fragment.format = format
        supportFragmentManager
                .beginTransaction()
                .addToBackStack("format")
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit()
    }

    override fun onTournamentClicked(format: String, date: String) {
        val fragment = ChallengeDecksListFragment()
        fragment.format = format
        fragment.date = date
        supportFragmentManager
                .beginTransaction()
                .addToBackStack("tournament")
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.content, fragment)
                .commit()
    }

    override fun onDeckClicked(deck: Deck) {

    }

    private fun executeQuery(
            query: String,
            title: String,
            addToBackStack: Boolean,
            isQueryEditable: Boolean,
            prints: GetCardsForQueryUseCase.PrintsToInclude = GetCardsForQueryUseCase.PrintsToInclude.CARDS,
            sortMethod: GetCardsForQueryUseCase.SortMethod = GetCardsForQueryUseCase.SortMethod.NAME,
            sortOrder: GetCardsForQueryUseCase.SortOrder = GetCardsForQueryUseCase.SortOrder.AUTO,
            listener: SearchListener? = null) {
        val fragment = CardListFragment()
        fragment.query = query
        fragment.fragmentTitle = title
        fragment.prints = prints
        fragment.sortMethod = sortMethod
        fragment.sortOrder = sortOrder
        fragment.isQueryEditable = isQueryEditable
        fragment.searchListener = listener
        var transaction = supportFragmentManager.beginTransaction()
        transaction = transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        transaction = if (addToBackStack) {
            transaction.addToBackStack("cardListQuery")
        } else {
            transaction.disallowAddToBackStack()
        }
        transaction = transaction.replace(R.id.content, fragment)
        transaction.commit()
    }

    private fun openCard(card: Card, addToBackStack: Boolean, view: View) {
        val fragment = CardDetailFragment()
        currentFragment.sharedElementReturnTransition = TransitionInflater.from(this).inflateTransition(R.transition.transition)
        currentFragment.exitTransition = null
        fragment.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.transition)
        fragment.enterTransition = null
        fragment.card = card
        var transaction = supportFragmentManager.beginTransaction()
        transaction = if (addToBackStack) {
            transaction.addToBackStack("cardDetail")
        } else {
            transaction.disallowAddToBackStack()
        }
        transaction = transaction.addSharedElement(view, card.id)
        transaction = transaction.replace(R.id.content, fragment)
        transaction.commit()
    }

    override fun setDrawerEnabled(show: Boolean) {
        val lockMode = if (show)
            DrawerLayout.LOCK_MODE_UNLOCKED
        else
            DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        drawerLayout.setDrawerLockMode(lockMode)
        drawerToggle.isDrawerIndicatorEnabled = show
        drawerToggle.syncState()
    }

    override fun finish() {
        if (currentFragment is SearchFragment) {
            super.finish()
        } else {
            openSearch()
        }
    }

    fun resetWordBank() {
        useCaseHandler.execute(
                useCaseProvider.updateWordBankUseCase,
                object : UseCasePartialCallback<UpdateWordBankUseCase.OkOutput, UpdateWordBankUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String?, response: UpdateWordBankUseCase.OkOutput?) {
                        Toast.makeText(this@MainActivity, R.string.word_bank_has_been_reset, Toast.LENGTH_LONG).show()
                    }

                    override fun onGenericError(tag: String?) {
                        Toast.makeText(this@MainActivity, "onGenericError", Toast.LENGTH_LONG).show()
                    }
                })
    }

    fun resetFavorites() {
        useCaseHandler.execute(
                useCaseProvider.clearFavoritesUseCase,
                object : UseCasePartialCallback<ClearFavoritesUseCase.OkOutput, ClearFavoritesUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String?, response: ClearFavoritesUseCase.OkOutput?) {
                        Toast.makeText(this@MainActivity, R.string.favorites_have_been_deleted, Toast.LENGTH_LONG).show()
                    }
                })
    }

}