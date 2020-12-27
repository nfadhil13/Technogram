package com.fdev.technogram.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fdev.technogram.ui.gray
import com.fdev.technogram.R


@Composable
fun TechnogramTopAppBar() {
    TopAppBar(
            backgroundColor = Color.White,
            elevation = 5.dp
    ) {
        Surface(
                modifier = Modifier.padding(horizontal = 10.dp)
        )
        {
            Row{
                Box(
                        modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                ){
                    Burger(
                            modifier = Modifier
                                    .width(22.dp)
                                    .align(Alignment.CenterStart)
                                    .fillMaxHeight(0.3f)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(
                        imageResource(id = R.drawable.technogram_logo),
                        modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                )
                Spacer(
                        modifier = Modifier
                                .weight(2f)
                                .fillMaxHeight()
                )
            }
        }
    }
}

@Composable
fun Burger(modifier: Modifier = Modifier , burgerHeight : Dp = 1.dp) {

    val burgerModifier = Modifier
            .fillMaxWidth()
            .height(burgerHeight)
            .background(gray)
    Column(
            modifier = modifier
    ) {
        Box(modifier = burgerModifier.weight(1f))
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = burgerModifier.weight(1f))
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = burgerModifier.weight(1f))
    }
}