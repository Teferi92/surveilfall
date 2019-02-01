package es.santirivera.surveilfall.util.enumwrap

import android.content.Context
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.domain.usecases.GetCardsForQueryUseCase
import java.util.*

enum class SortMethodDescriptor(val method: GetCardsForQueryUseCase.SortMethod, val descriptor: Int) {

    NAME(GetCardsForQueryUseCase.SortMethod.NAME, R.string.sort_method_name),
    SET(GetCardsForQueryUseCase.SortMethod.SET, R.string.sort_method_set),
    RELEASED(GetCardsForQueryUseCase.SortMethod.RELEASED, R.string.sort_method_release),
    RARITY(GetCardsForQueryUseCase.SortMethod.RARITY, R.string.sort_method_rarity),
    COLOR(GetCardsForQueryUseCase.SortMethod.COLOR, R.string.sort_method_color),
    CMC(GetCardsForQueryUseCase.SortMethod.CMC, R.string.sort_method_cmd),
    POWER(GetCardsForQueryUseCase.SortMethod.POWER, R.string.sort_method_power),
    TOUGHNESS(GetCardsForQueryUseCase.SortMethod.TOUGHNESS, R.string.sort_method_toughness),
    EDHREC(GetCardsForQueryUseCase.SortMethod.EDHREC, R.string.sort_method_edhrec),
    ARTIST(GetCardsForQueryUseCase.SortMethod.ARTIST, R.string.sort_method_artist);


    companion object {

        fun getDescriptorsList(context: Context): Array<CharSequence> {
            val descriptors = ArrayList<CharSequence>()
            for (descriptor in SortMethodDescriptor.values()) {
                descriptors.add(context.getString(descriptor.descriptor))
            }
            val array = arrayOfNulls<CharSequence>(descriptors.size)
            return descriptors.toArray(array)
        }

        fun forDescription(context: Context, desc: CharSequence): SortMethodDescriptor {
            for (descriptor in SortMethodDescriptor.values()) {
                if (desc == context.getString(descriptor.descriptor)) {
                    return descriptor
                }
            }
            return NAME
        }
    }

}
