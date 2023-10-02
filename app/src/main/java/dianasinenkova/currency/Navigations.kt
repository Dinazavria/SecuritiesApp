package dianasinenkova.currency

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dianasinenkova.currency.ui.theme.CurrencyTheme



@Composable
fun Navigations(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.List.route) {
        composable(NavigationItem.List.route) {
            ListScreen()
        }
        composable(NavigationItem.Chart.route) {
            ChartScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
    }
}
