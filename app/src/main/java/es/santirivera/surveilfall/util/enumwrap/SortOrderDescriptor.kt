package es.santirivera.surveilfall.util.enumwrap

import android.content.Context
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.domain.usecases.GetCardsForQueryUseCase
import java.util.ArrayList

enum class SortOrderDescriptor(val sortOrder: GetCardsForQueryUseCase.SortOrder, val description: Int) {

    AUTO(GetCardsForQueryUseCase.SortOrder.AUTO, R.string.order_automatic),
    ASC(GetCardsForQueryUseCase.SortOrder.ASC, R.string.order_ascending),
    DESC(GetCardsForQueryUseCase.SortOrder.DESC, R.string.order_descending);

    companion object {

        fun getDescriptorsList(context: Context): Array<CharSequence> {
            val descriptors = ArrayList<CharSequence>()
            for (descriptor in SortOrderDescriptor.values()) {
                descriptors.add(context.getString(descriptor.description))
            }
            val array = arrayOfNulls<CharSequence>(descriptors.size)
            return descriptors.toArray(array)
        }

        fun forDescription(context: Context, desc: CharSequence): SortOrderDescriptor {
            for (descriptor in SortOrderDescriptor.values()) {
                if (desc == context.getString(descriptor.description)) {
                    return descriptor
                }
            }
            return AUTO
        }
    }
}
