package com.fdev.technogram.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fdev.technogram.ui.TechnogramTheme

@Composable
fun TechnogramDrawer(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean,
    onSignInClicked: () -> Unit,
    onSearch : () -> Unit,
    searchKey: String,
    onValueChange : (newValue : String)  -> Unit
){
    TechnogramTheme() {
        Column(
            modifier = modifier
        ){
            DrawerSearch(
                modifier = Modifier.fillMaxWidth(),
                onSearch = onSearch,
                searchKey = searchKey,
                onValueChange = onValueChange
            )
            DrawerUserLayout(
                isLoggedIn =  isLoggedIn ,
                onSignInClicked = onSignInClicked
            )
            DrawerNavigationButtons(

            )
        }
    }

}


@Composable
fun DrawerNavigationButtons() {
    val buttonTexts = listOf("Home" , "App" , "Software", "General" , "More")
    FlowRow() {
        buttonTexts.forEach {
            Text(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = it
            )
        }
    }
}

@Composable
fun DrawerSearch(
    modifier: Modifier = Modifier,
    onSearch : () -> Unit = {},
    searchKey : String,
    onValueChange : (newValue : String)  -> Unit
){
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = searchKey,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        leadingIcon =  {
            Icon(Icons.Outlined.Search , tint = MaterialTheme.colors.onBackground.copy(alpha = 0.6f))
        }
    )
}


@Composable
fun DrawerUserLayout(isLoggedIn : Boolean , onSignInClicked : () -> Unit){
    if(isLoggedIn){
        Button(onClick = onSignInClicked) {
            Text(text = "SIGN IN")
        }
    }else{
        Text("Logged In")
    }
}