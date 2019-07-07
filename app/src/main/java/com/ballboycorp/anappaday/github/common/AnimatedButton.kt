package com.ballboycorp.anappaday.github.common

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.ScaleAnimation
import android.widget.Button


/**
 * Created by musooff on 2019-07-05.
 */

class AnimatedButton : Button {

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr)

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        if (!isEnabled) {
            return super.onTouchEvent(motionEvent)
        }
        when (motionEvent.action) {
            0 -> {
                animateClick(1.0f, 0.95f, 1.0f, 0.95f)
            }
            1, 3, 10 -> {
                animateClick(0.95f, 1.0f, 0.95f, 1.0f)
            }
        }
        invalidate()
        return super.onTouchEvent(motionEvent)
    }

    override fun onFocusChanged(z: Boolean, i: Int, rect: Rect?) {
        super.onFocusChanged(z, i, rect)
        if (z) {
            animateClick(0.95f, 1.0f, 0.95f, 1.0f)
            return
        }
        animateClick(1.0f, 0.95f, 1.0f, 0.95f)
    }

    private fun animateClick(f: Float, f2: Float, f3: Float, f4: Float) {
        val scaleAnimation = ScaleAnimation(f, f2, f3, f4, 1, 0.5f, 1, 0.5f)
        scaleAnimation.fillAfter = true
        scaleAnimation.duration = 150
        startAnimation(scaleAnimation)
    }
}