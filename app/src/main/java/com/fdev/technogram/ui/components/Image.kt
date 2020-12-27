package com.fdev.technogram.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import com.fdev.technogram.R
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.coil.CoilImageState



//When you just want to use a normal event
@Composable
fun ImageWithErrorImage(
        imageUrl : String,
        modifier: Modifier = Modifier,
        contentScale : ContentScale = ContentScale.Crop,
        @DrawableRes errorImage : Int = R.drawable.error_image,
        alignment: Alignment = Alignment.Center
){

    CoilImage(
            modifier = modifier,

            imageModel = imageUrl,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = contentScale,
            // shows an image with a circular revealed animation.
            circularRevealedEnabled = true,
            // shows an error imageAsset when the request failed.
            error = imageResource(errorImage),

            alignment = alignment


    )
}

//When you just want to use a normal event
@Composable
fun ImageWithErrorImage(
        imageUrl : String,
        modifier: Modifier = Modifier,
        contentScale : ContentScale = ContentScale.Crop,
        @DrawableRes errorImage : Int = R.drawable.error_image,
        alignment: Alignment = Alignment.Center,
        shimmerParams: ShimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = Color.Gray,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f
        )
){

    CoilImage(
            modifier = modifier,

            imageModel = imageUrl,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = contentScale,
            // shows an image with a circular revealed animation.
            circularRevealedEnabled = true,
            // shows an error imageAsset when the request failed.
            error = imageResource(errorImage),

            shimmerParams = shimmerParams,

            alignment = alignment
            )
}


//When you want to define custom event
@Composable
fun ImageWithErrorText(
        imageUrl : String,
        modifier: Modifier = Modifier,
        contentScale : ContentScale = ContentScale.Crop,
        loading: @Composable ((imageState: CoilImageState.Loading) -> Unit)? = null,
        success: @Composable ((imageState: CoilImageState.Success) -> Unit)? = null,
        failure: @Composable ((imageState: CoilImageState.Failure) -> Unit)? = { Text("Error loading image") },
        alignment: Alignment = Alignment.Center
){

    CoilImage(
            modifier = modifier,

            imageModel = imageUrl,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = contentScale,
            // shows an image with a circular revealed animation.
            circularRevealedEnabled = true,

            loading = loading,

            success = success,

            failure = failure,

            alignment = alignment

    )
}