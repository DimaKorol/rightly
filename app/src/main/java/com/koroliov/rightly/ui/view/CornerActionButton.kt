package com.koroliov.rightly.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageButton
import by.liveboard.android.utils.toPx
import com.koroliov.rightly.R


/**
 * Created by dima_korolev on 03/07/2017.
 */

class CornerActionButton : ImageButton {
    enum class Corner(val i: Int) {
        topLeft(0), topRight(1), bottomRight(2), bottomLeft(3);

        companion object {
            fun valueOf(i: Int): Corner = when(i) {
                0 -> topLeft
                1 -> topRight
                2 -> bottomRight
                3 -> bottomLeft
                else -> topRight
            }
        }
    }

    private var clipPath = Path()
    private var maskPaint = Paint()
    private var corner: Corner = Corner.topRight

    constructor(context: Context) : this(context, null as AttributeSet)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        maskPaint.color = Color.WHITE
        maskPaint.isAntiAlias = true
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CornerActionButton,
                0, 0)

        try {
            corner = Corner.valueOf(a.getInt(R.styleable.CornerActionButton_corner, 1))
        } finally {
            a.recycle()
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val verticalPadding = (h*0.25).toInt()
        val horizontalPadding = (w*0.25).toInt()
        setPadding(
                when(corner) {
                    Corner.bottomRight, Corner.topRight -> horizontalPadding
                    else -> 0
                },
                when(corner) {
                    Corner.bottomRight, Corner.bottomLeft -> verticalPadding
                    else -> 0
                },
                when(corner) {
                    Corner.topLeft, Corner.bottomLeft -> horizontalPadding
                    else -> 0
                },
                when(corner) {
                    Corner.topLeft, Corner.topRight -> verticalPadding
                    else -> 0
                })

        val width = w.toFloat()
        val height = h.toFloat()

        val radii = when(corner) {
            Corner.topLeft -> floatArrayOf(0f,0f,0f,0f,width, height,0f,0f)
            Corner.topRight -> floatArrayOf(0f,0f,0f,0f,0f,0f,width, height)
            Corner.bottomRight -> floatArrayOf(width,height,0f,0f,0f,0f,0f,0f)
            Corner.bottomLeft -> floatArrayOf(0f,0f,width,height,0f,0f,0f,0f)
        }

        clipPath.reset()
        clipPath.addRoundRect(
                0f, 0f, w.toFloat(), h.toFloat(), // rectangle
                radii, // corner
                Path.Direction.CW)
        clipPath.close()
    }

    override fun draw(canvas: Canvas?) {
        canvas?.saveLayer(null, null)
        super.draw(canvas)
        canvas?.drawPath(clipPath, maskPaint)
        canvas?.restore()
    }
}