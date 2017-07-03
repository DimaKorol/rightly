package com.koroliov.rightly.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageButton
import by.liveboard.android.utils.toPx


/**
 * Created by dima_korolev on 03/07/2017.
 */

class CornerActionButton : ImageButton {
    private var clipPath = Path()
    private var maskPaint = Paint()

    constructor(context: Context) : this(context, null as AttributeSet)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        maskPaint.color = Color.WHITE
        maskPaint.isAntiAlias = true
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setPadding((w*0.25).toInt(), 0, 0, (h*0.25).toInt())
        clipPath.reset()
        clipPath.addRoundRect(
                0f, 0f, w.toFloat(), h.toFloat(), // rectangle
                floatArrayOf(0f,0f,0f,0f,0f,0f,w.toPx().toFloat(), h.toPx().toFloat()), // corner
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