package com.fdev.technogram.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.AndroidView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@Composable
fun SwipeRefreshCompose(
    modifier : Modifier = Modifier,
    isRefreshing : Boolean = false,
    onRefresh : () -> Unit = {},
    isEnable : Boolean = false,
    content : @Composable () -> Unit

){
    AndroidView(
        modifier = modifier,
        viewBlock = { context ->
        SwipeRefreshLayout(context).apply {
            setContent(
                content = content
            )

            this.isRefreshing = isRefreshing

            this.isEnabled = isEnable

            setOnRefreshListener {
                onRefresh()
            }
        }
    })
}
