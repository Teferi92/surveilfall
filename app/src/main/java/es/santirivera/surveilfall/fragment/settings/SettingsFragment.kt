package es.santirivera.surveilfall.fragment.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        val bank = findPreference<Preference>("wordbank")
        val favs = findPreference<Preference>("favs")

        bank.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            (activity as MainActivity).resetWordBank()
            true
        }
        bank.isIconSpaceReserved = false

        favs.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            (activity as MainActivity).resetFavorites()
            true
        }
        favs.isIconSpaceReserved = false
    }

}