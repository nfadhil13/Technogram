package com.fdev.technogram.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dev.chrisbanes.accompanist.glide.GlideImage


//When you want to define custom event
@Composable
fun NetworkImage(
        imageUrl : String,
        modifier: Modifier =  Modifier,
        backgroundColor : Color = Color.Gray

){
        GlideImage(
                data = imageUrl,
                modifier = modifier,
                requestBuilder = {
                        val options = RequestOptions()
                        options.centerCrop()
                        options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        apply(options)
                },
                loading = {
                        ConstraintLayout(
                                modifier = Modifier.fillMaxSize().background(backgroundColor)
                        ) {
                                val indicator = createRef()
                                CircularProgressIndicator(
                                        modifier = Modifier.constrainAs(indicator) {
                                                top.linkTo(parent.top)
                                                bottom.linkTo(parent.bottom)
                                                start.linkTo(parent.start)
                                                end.linkTo(parent.end)
                                        }
                                )
                        }
                },
                error = {
                    ConstraintLayout(
                            modifier = Modifier.fillMaxSize().background(backgroundColor)
                    ) {
                        val text = createRef()
                        Text(
                                text = "Error Loading Image",
                                modifier = Modifier.constrainAs(text) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }

                }
        )
}

