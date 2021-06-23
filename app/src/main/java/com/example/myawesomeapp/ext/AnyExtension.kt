package com.example.myawesomeapp.ext

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes

fun Context.dpToPix(dpValue: Int): Float {
    return dpValue * this.resources.displayMetrics.density
}

fun Context.getScreenHeightInPixels(): Int {
    val metrics = DisplayMetrics()
    this.windowManager().defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}

fun Context.getScreenWidthInPixels(): Int {
    val metrics = DisplayMetrics()
    this.windowManager().defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

fun Context.windowManager(): WindowManager {
    return this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
}

fun ViewGroup.inflate(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return context.inflate(resource, root, attachToRoot)
}

fun Context.inflate(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
    val inflater = LayoutInflater.from(this)
    return inflater.inflate(resource, root, attachToRoot)
}

fun ValueAnimator.addOnEndAction(action: () -> Unit) {
    this.addListener(object : Animator.AnimatorListener {
        override fun onAnimationEnd(p0: Animator?) { action.invoke() }
        override fun onAnimationRepeat(p0: Animator?) {}
        override fun onAnimationCancel(p0: Animator?) {}
        override fun onAnimationStart(p0: Animator?) {}
    })
}

inline fun Animator.addEndListener(
    crossinline onEnd: (animator: Animator) -> Unit = {}
): Animator.AnimatorListener {
    val listener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator) {}
        override fun onAnimationEnd(p0: Animator) = onEnd(p0)
        override fun onAnimationCancel(p0: Animator) {}
        override fun onAnimationStart(p0: Animator) {}
    }
    addListener(listener)
    return listener
}

fun Int.isOdd(): Boolean {
    return (this % 2 != 0)
}
fun Int.isEven(): Boolean {
    return (this % 2 == 0)
}