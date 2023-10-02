package dianasinenkova.currency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dianasinenkova.currency.ui.theme.CurrencyTheme

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.List,
        NavigationItem.Chart,
        NavigationItem.Profile,
    )
    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItem.List.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }
    NavigationBar(
        modifier = Modifier.clip(shape = RoundedCornerShape(24.dp))
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(
                    item.icon!!,
                    contentDescription = item.title,
                    tint = if(selectedItem == index)
                        MaterialTheme.colorScheme.tertiary
                        else
                        MaterialTheme.colorScheme.primary) },
                //label = { Text(item.title) },
                selected =  selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    CurrencyTheme {
        BottomNavigationBar(navController = rememberNavController())
    }
}