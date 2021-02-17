package com.example.fullfledgedfeed_dunets_l25_27.shared.extensions

import android.view.View

fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}