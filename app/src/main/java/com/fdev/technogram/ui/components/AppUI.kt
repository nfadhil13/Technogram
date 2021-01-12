package com.fdev.technogram.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fdev.technogram.R

//Define all apps ui things like top app bar , drawer , bottom navigation etc

@Composable
fun TechnogramTopAppBar(
    onBurgerClicked: () -> Unit,
    onSearchClicked: () -> Unit
) {
    Surface{
        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 5.dp
        ) {
            Surface(
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            {
                Row {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Burger(
                            modifier = Modifier
                                .width(22.dp)
                                .align(Alignment.CenterStart)
                                .fillMaxHeight(0.3f)
                                .clickable(onClick = onBurgerClicked)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        imageResource(id = R.drawable.technogram_logo),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )
                    Box(
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight()
                    ){
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            tint = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
                            modifier = Modifier
                                .fillMaxHeight()
                                .align(Alignment.CenterEnd)
                                .clickable(onClick = onSearchClicked)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Burger(modifier: Modifier = Modifier, burgerHeight: Dp = 1.dp) {

    val burgerModifier = Modifier
        .fillMaxWidth()
        .height(burgerHeight)
        .background(MaterialTheme.colors.onBackground.copy(alpha = 0.6f))
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

