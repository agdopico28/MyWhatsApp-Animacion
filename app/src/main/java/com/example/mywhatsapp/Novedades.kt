package com.example.mywhatsapp

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

@Composable
fun Novedades(){
    Icono()
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun Icono(){
    val image =
        AnimatedImageVector.animatedVectorResource(R.drawable.pause_play
        )
    var atEnd by remember { mutableStateOf(false) }

        Image(
            painter = rememberAnimatedVectorPainter(image, atEnd),
            contentDescription = "VectorDrawable",
            modifier = Modifier.clickable {
                atEnd = !atEnd
            },
            contentScale = ContentScale.Crop,
        )

}