package com.example.ageinminute.utils

import com.example.ageinminute.R

object IconPicker {
    val Icons = listOf<Int>(R.drawable.ic_icon_1,R.drawable.ic_icon_2,R.drawable.ic_icon_3,R.drawable.ic_icon_4,R.drawable.ic_icon_5,R.drawable.ic_icon_6,R.drawable.ic_icon_7)
    var currentIconIndex:Int = 0

    fun getIcon():Int{
        currentIconIndex = (currentIconIndex +1) % Icons.size
        return Icons[currentIconIndex]
    }
}