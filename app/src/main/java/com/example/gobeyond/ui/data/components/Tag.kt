package com.example.gobeyond.ui.data.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tag(text: String, color: Color) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 10.sp,
        modifier = Modifier
            .background(color, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 1.dp)
    )
}