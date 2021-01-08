package com.fdev.technogram.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fdev.technogram.R
import com.fdev.technogram.ui.screen.main.MainNavigation

@Composable
fun TechnogramDrawer(
        modifier: Modifier = Modifier,
        isLoggedIn: Boolean,
        onSignInClicked: () -> Unit,
        navigations: List<MainNavigation>,
        onNavigationItemClicked : (Int) -> Unit,
        selectedItem : Int
) {


    Column(
            modifier = modifier.padding(10.dp)
    ) {
        Providers(AmbientContentAlpha provides ContentAlpha.medium) {
            Spacer(modifier = Modifier.height(42.dp))
            DrawerUserLayout(
                    isLoggedIn = isLoggedIn,
                    onSignInClicked = onSignInClicked
            )
            Spacer(modifier = Modifier.height(28.dp))
            DrawerNavigationButtons(
                    navigations = navigations,
                    onItemClicked = onNavigationItemClicked,
                    activeIndex = selectedItem
            )
        }

    }

}


@Composable
fun DrawerNavigationButtons(
        navigations: List<MainNavigation>,
        onItemClicked: (clickedItem: Int) -> Unit,
        activeIndex : Int
) {
    Column() {
        Box(
                modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.onBackground.copy(alpha = 0.6f))
        )
        Spacer(modifier = Modifier.height(28.dp))
        Column(
                modifier = Modifier.fillMaxWidth()
        ) {
            navigations.forEachIndexed { index , navigation ->
                println(index == activeIndex)
                Surface(
                        modifier = Modifier.clickable(onClick = { onItemClicked(index) })

                ) {
                    when (navigation) {
                        is MainNavigation.Home -> {
                            DrawerNavigationItem(
                                    icon = R.drawable.ic_home,
                                    text = "Home", isActive = index == activeIndex
                            )
                        }
                        is MainNavigation.Search -> {
                            DrawerNavigationItem(icon = Icons.Outlined.Search, text = "Search")
                        }
                        is MainNavigation.SearchWithQuery -> {
                            DrawerNavigationItem(icon = navigation.icon, text = navigation.query , isActive = index == activeIndex)
                        }
                        is MainNavigation.More -> {
                            DrawerNavigationItem(icon = R.drawable.ic_more, text = "More" , isActive = index == activeIndex)
                        }
     
                    }
                }
                Spacer(modifier = Modifier.height(28.dp))
            }


        }
    }
}


@Composable
fun DrawerNavigationItem(
        @DrawableRes icon: Int,
        text: String,
        modifier: Modifier = Modifier,
        isActive: Boolean = false

) {
    Row(
            modifier = modifier
                    .fillMaxWidth()
    ) {
        Icon(
                imageVector = vectorResource(id = icon),
                modifier = Modifier.size(24.dp),
                tint = if (isActive) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
                text = text,
                style = if (isActive) MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary)
                else MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun DrawerNavigationItem(
        icon: ImageVector,
        text: String,
        modifier: Modifier = Modifier,
        isActive: Boolean = false

) {
    Row(
            modifier = modifier
                    .fillMaxWidth()
    ) {
        Icon(
                imageVector = icon,
                modifier = Modifier.size(24.dp),
                tint = if (isActive) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
                text = text,
                style = if (isActive) MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary)
                else MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun DrawerUserLayout(isLoggedIn: Boolean, onSignInClicked: () -> Unit , modifier: Modifier = Modifier) {
    if (!isLoggedIn) {
        Row(
                modifier = modifier
        ) {
            Icon(imageVector = Icons.Filled.Login)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                    text = "SIGN IN",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(
                            Alignment.CenterVertically
                    )
            )
        }
    } else {
        Text("Logged In")
    }
}