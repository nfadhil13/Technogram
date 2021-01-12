package com.fdev.technogram.ui.screen.main.categories

import androidx.compose.animation.transition
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fdev.technogram.model.Category
import com.fdev.technogram.ui.*
import com.fdev.technogram.ui.animations.ColorPulse
import kotlin.random.Random


@Composable
fun Categories(
        categoriesViewModel: CategoriesViewModel,
        onCategoryClicked : (category : Category) -> Unit
){
    Surface() {
        if(categoriesViewModel.isLoading){
            CategoryListSkeleton()
        }else{
            Categorylist(categories = categoriesViewModel.categories, onCategoryClicked = onCategoryClicked)
        }
    }
}


fun randomColor() : Color {
    val staticColors = listOf(technogramRed300 , technogramRed500 , complementary200 , complementary400  , triadic600 , technogramRed700 , complementary300)
    val randomNumber = Random.nextInt(0 , staticColors.size)
    println(randomNumber)
    return staticColors[randomNumber]

}

@Composable
fun Categorylist(
        categories : List<Category>,
        onCategoryClicked : (category : Category) -> Unit
) {

    ScrollableColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(top = 24.dp)
    ) {
        Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            FlowRow(
                    mainAxisAlignment = FlowMainAxisAlignment.Center,
                    mainAxisSpacing = 5.dp,
                    crossAxisSpacing = 5.dp
            ) {
                categories.forEach {  category ->
                    Text(
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            text = "#${category.categoryName}",
                            style = MaterialTheme.typography.subtitle1.copy(MaterialTheme.colors.onPrimary),
                            modifier = Modifier
                                .background(
                                    color = randomColor(),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(8.dp)
                                .clickable(onClick = { onCategoryClicked(category) })
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryListSkeleton(){


    val widths = listOf(60.dp , 64.dp , 77.dp , 120.dp , 75.dp , 95.dp)

    val colorPulse = transition(
            definition = ColorPulse.shimmerDefinition,
            initState = ColorPulse.ShimmerState.INITIAL,
            toState = ColorPulse.ShimmerState.FINAL
    )

    val pulseColor = colorPulse[ColorPulse.shimmerKey]

    ScrollableColumn(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
    ) {
        Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            FlowRow(
                    mainAxisAlignment = FlowMainAxisAlignment.Center,
                    mainAxisSpacing = 5.dp,
                    crossAxisSpacing = 5.dp
            ) {
                for(width in widths){
                    Box(
                            modifier = Modifier
                                    .background(
                                            color = pulseColor,
                                            shape = RoundedCornerShape(4.dp)
                                    )
                                    .height(32.dp)
                                    .width(width)
                                    .padding(8.dp)
                    )
                }
            }
        }
    }
}