package com.strongpancakes.quest.utils

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.koroliov.rightly.R


/**
 * Created by dima_korolev on 17/06/2017.
 */

fun Activity.start(activity: Class<out Activity>, vararg flags: Int = intArrayOf()) {
    val intent = Intent(this, activity)
    flags.forEach { intent.addFlags(it) }
    startActivity(intent)
}

operator fun ViewGroup.get(index: Int): View = getChildAt(index)

fun ViewGroup.children() = object : Iterable<View> {
    override fun iterator(): Iterator<View> = object : Iterator<View> {
        var index = 0
        override fun hasNext(): Boolean = index < childCount
        override fun next(): View = getChildAt(index++)
    }
}

fun Activity.showProgress() {
    var progressView = progressView
    if (progressView != null) {
        progressView.visibility = View.VISIBLE
    } else {
        progressView = layoutInflater.inflate(R.layout.item_progress_bar, null)
        findViewById<ViewGroup>(android.R.id.content)?.addView(progressView)
    }
}

fun Activity.hideProgress() {
    progressView?.visibility = View.INVISIBLE
}

fun Fragment.hideProgress() {
    activity?.progressView?.visibility = View.INVISIBLE
}

fun Fragment.showProgress() {
    activity?.showProgress()
}

val Activity.progressView: View?
get() {
    val rootContainer = findViewById<ViewGroup>(android.R.id.content)
    if (rootContainer.childCount == 0) return null
    val topView = rootContainer.getChildAt(rootContainer.childCount - 1)
    return if (topView.id == R.id.progressViewScreen) topView else null
}

fun View.hideKeyboard() {
    val view = (context as Activity).currentFocus
    if (view != null) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}