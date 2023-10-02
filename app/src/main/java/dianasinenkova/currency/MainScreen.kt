package dianasinenkova.currency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dianasinenkova.currency.ui.theme.CurrencyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
){
    Scaffold (
        modifier = Modifier.padding(8.dp),
        bottomBar = {
            BottomAppBar(modifier = Modifier, tonalElevation = 0.dp) {
                BottomNavigationBar(navController = navController)
            }
        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = {}) {
//                Icon(Icons.Outlined.Home, "List")
//            }
//        }
    ){
        innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())
            )
        ){
            Navigations(navController = navController)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    CurrencyTheme {
        MainScreen(navController = rememberNavController())
    }
}