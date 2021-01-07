package com.fdev.technogram.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TechnogramDrawer(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean,
    onSignInClicked: () -> Unit,
    onSearch: () -> Unit,
    searchKey: String,
    onValueChange: (newValue: String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        DrawerSearch(
            modifier = Modifier.fillMaxWidth(),
            onSearch = onSearch,
            searchKey = searchKey,
            onValueChange = onValueChange
        )
        Spacer(modifier = Modifier.height(42.dp))
        DrawerUserLayout(
            isLoggedIn = isLoggedIn,
            onSignInClicked = onSignInClicked
        )
        Spacer(modifier = Modifier.height(28.dp))
        DrawerNavigationButtons(

        )
    }

}


@Composable
fun DrawerNavigationButtons() {
    val buttonTexts = listOf("Home", "App", "Software", "General", "More")
    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.onBackground.copy(alpha = 0.6f))
        )
        Spacer(modifier = Modifier.height(28.dp))
        FlowRow() {
            buttonTexts.forEach {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(vertical = 12.dp),
                    style = MaterialTheme.typography.caption,
                    text = it
                )
            }
        }
    }
}

@Composable
fun DrawerSearch(
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {},
    searchKey: String,
    onValueChange: (newValue: String) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        value = searchKey,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        leadingIcon = {
            Icon(Icons.Outlined.Search, tint = MaterialTheme.colors.onBackground.copy(alpha = 0.6f))
        },
        singleLine = true,
        backgroundColor = MaterialTheme.colors.background,
        activeColor = MaterialTheme.colors.onBackground,
        inactiveColor = MaterialTheme.colors.onBackground
    )
}


@Composable
fun DrawerUserLayout(isLoggedIn: Boolean, onSignInClicked: () -> Unit) {
    if (!isLoggedIn) {
        Row(
        ) {
            Icon(imageVector = Icons.Filled.Login, tint = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "SIGN IN",
                style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.primary),
                modifier = Modifier.align(
                    Alignment.CenterVertically
                )
            )
        }
    } else {
        Text("Logged In")
    }
}