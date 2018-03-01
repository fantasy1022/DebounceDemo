package com.fantasy1022.debouncedemo

import android.os.SystemClock
import android.view.View
import java.util.*


/**
 * Ref:https://gist.github.com/rfreedman/5573388
 * A Debounced OnClickListener
 * Rejects clicks that are too close together in time.
 * This class is safe to use as an OnClickListener for multiple views, and will debounce each one separately.
 */

/**
 * The one and only constructor
 *
 * @param minimumIntervalMsec The minimum allowed time between clicks - any click sooner than this after a previous click will be rejected
 */
abstract class DebouncedOnClickListener(val minimumInterval: Long,
                                        val lastClickMap: WeakHashMap<View, Long> = WeakHashMap()) : View.OnClickListener {

    /**
     * Implement this in your subclass instead of onClick
     *
     * @param v The view that was clicked
     */
    abstract fun onDebouncedClick(v: View)


    override fun onClick(clickedView: View) {
        val previousClickTimestamp = lastClickMap[clickedView]
        val currentTimestamp = SystemClock.uptimeMillis()
        if (previousClickTimestamp == null || (currentTimestamp - previousClickTimestamp.toLong() > minimumInterval)) {
            lastClickMap.put(clickedView, currentTimestamp)
            onDebouncedClick(clickedView)
        }
    }
}