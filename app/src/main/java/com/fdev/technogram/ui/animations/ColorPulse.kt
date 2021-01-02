package com.fdev.technogram.ui.animations

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.core.*
import androidx.compose.ui.graphics.Color
import com.fdev.technogram.ui.animations.ColorPulse.ShimmerState.FINAL
import com.fdev.technogram.ui.animations.ColorPulse.ShimmerState.INITIAL

object ColorPulse{

    enum class ShimmerState{
        INITIAL , FINAL
    }

    val shimmerKey = ColorPropKey(
            label = "shimmerKey"
    )

    val shimmerDefinition = transitionDefinition<ShimmerState> {
        state(INITIAL){
            this[shimmerKey] = Color.Gray.copy(alpha = 0.25f)
        }
        state(FINAL){
            this[shimmerKey] = Color.Gray.copy(alpha = 0.6f)
        }

        transition(
                INITIAL to FINAL
        ){
            shimmerKey using infiniteRepeatable(
                    animation = tween(
                            durationMillis = 1500,
                            easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
            )
        }
    }



}