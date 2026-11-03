package com.mmgpt.shoplistgpt

import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

internal fun RecyclerView.setOnItemDoubleTapListener(onItemDoubleTap: (Long) -> Boolean) {
    val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(event: MotionEvent): Boolean {
            val child = findChildViewUnder(event.x, event.y) ?: return super.onDoubleTap(event)
            if (getChildAdapterPosition(child) == RecyclerView.NO_POSITION) {
                return super.onDoubleTap(event)
            }
            return onItemDoubleTap(getChildViewHolder(child).itemId) || super.onDoubleTap(event)
        }
    })

    setOnTouchListener { _, event ->
        gestureDetector.onTouchEvent(event)
        false
    }
}
