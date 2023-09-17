package com.example.ageinminute.utils

object ColorPicker{
    val colors = listOf<String>("#00CBA9","#00B0A7","#E06F2B","#737FEF","#68548D","#4A6EAE","#0089C3","#6948BC")
    var currentColorIndex:Int=0

    fun getColor():String{
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}