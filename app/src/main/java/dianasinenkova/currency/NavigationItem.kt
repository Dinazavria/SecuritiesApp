package dianasinenkova.currency

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, val icon: ImageVector?, var title: String) {
    object List: NavigationItem("List", Icons.Rounded.List, "List")
    object Chart: NavigationItem("Chart", Icons.Rounded.Info, "Chart")
    object Profile: NavigationItem("Profile", Icons.Rounded.AccountCircle, "Profile")
}