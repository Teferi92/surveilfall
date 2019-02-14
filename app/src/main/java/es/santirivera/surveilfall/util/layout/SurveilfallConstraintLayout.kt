package es.santirivera.surveilfall.util.layout

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.constraintlayout.widget.ConstraintLayout

class SurveilfallConstraintLayout : ConstraintLayout {

    interface OnBackPressedListener {
        fun onBackPressed()
    }

    var onBackPressedListener: OnBackPressedListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun dispatchKeyEventPreIme(event: KeyEvent): Boolean {
        if (onBackPressedListener != null && event.keyCode == KeyEvent.KEYCODE_BACK) {
            val state = keyDispatcherState
            if (state != null) {
                if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) {
                    state.startTracking(event, this)
                    return true
                } else if (event.action == KeyEvent.ACTION_UP
                        && !event.isCanceled && state.isTracking(event)) {
                    onBackPressedListener!!.onBackPressed()
                    return true
                }
            }
        }
        return super.dispatchKeyEventPreIme(event)
    }

}
