package com.fdev.technogram.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@Composable
fun SwipeRefreshCompose(
    modifier : Modifier = Modifier,
    isEnable : Boolean = false,
    onRefresh : () -> Unit = {},
    content : @Composable () -> Unit

){
    val context = AmbientContext.current
    val swipeRefreshLayout = remember{SwipeRefreshLayout(context).also{
        println("recompose")
        it.addView(ComposeView(
            it.context
        ).also { child ->
            child.setContent(content)
        })
    }}
    val backgroundColor = MaterialTheme.colors.background.toArgb()
    val mainColor = MaterialTheme.colors.primary.toArgb()

    AndroidView({swipeRefreshLayout} , modifier = modifier){ it ->
        it.setProgressBackgroundColorSchemeColor(backgroundColor)
        it.setColorSchemeColors(mainColor)
        it.isEnabled = isEnable
        it.setOnRefreshListener{
            it.isRefreshing = false
            onRefresh()
        }
    }
}
