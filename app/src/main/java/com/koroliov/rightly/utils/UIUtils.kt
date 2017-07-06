package by.liveboard.android.utils

import android.annotation.TargetApi
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import com.koroliov.rightly.appContext

/**
 * Created by dima_korolev on 20/04/16.
 */

class UIUtils {
    companion object {
        fun getSelectableBackground(radius: Float = 0f,
                                    backgroundColor: Int = appContext.resources.getColor(android.R.color.transparent),
                                    focusBackgroundColor: Int = Color.GRAY,
                                    borderWidth: Int = 0,
                                    borderColor: Int = backgroundColor): Drawable {

            // Default Drawable
            val defaultDrawable = GradientDrawable()
            defaultDrawable.setCornerRadius(radius)
            defaultDrawable.setColor(backgroundColor)

            //Focus Drawable
            val focusDrawable = GradientDrawable()
            focusDrawable.setCornerRadius(radius)
            focusDrawable.setColor(focusBackgroundColor)

            // Handle Border
            if (borderWidth != 0) {
                defaultDrawable.setStroke(borderWidth, borderColor)
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                return getRippleDrawable(defaultDrawable, focusDrawable, focusBackgroundColor)

            } else {

                val states = StateListDrawable()

                // Focus/Pressed Drawable
                val drawable2 = GradientDrawable()
                drawable2.setCornerRadius(radius)
                drawable2.setColor(focusBackgroundColor)


                // Handle Button Border
                if (borderWidth != 0) {
                    drawable2.setStroke(borderWidth, borderColor)
                }

                if (focusBackgroundColor != 0) {
                    states.addState(intArrayOf(android.R.attr.state_pressed), drawable2)
                    states.addState(intArrayOf(android.R.attr.state_focused), drawable2)
                }
                states.addState(intArrayOf(), defaultDrawable);

                return states

            }
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        fun getRippleDrawable(defaultDrawable: Drawable, focusDrawable: Drawable? = null, focusColor: Int): Drawable =
                RippleDrawable(ColorStateList.valueOf(focusColor), defaultDrawable, focusDrawable)

    }
}

fun Int.toPx(): Int {
    return ((this * Resources.getSystem().displayMetrics.density).toInt())
}