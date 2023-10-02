package dianasinenkova.currency

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import java.util.Currency

internal fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}

@Composable
fun ListScreen() {
    var context = LocalContext.current
    var data: MutableList<SecuritiesLocalDataModel> by remember { mutableStateOf(mutableListOf()) }
    LaunchedEffect(String) {
        data = readFile(context)
    }
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ){
        items(
            items = data,
            itemContent = {
                ListItem(security = it)
            }
        )
    }
}

@Composable
fun ListItem(security: SecuritiesLocalDataModel) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ){
        Row(
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 12.dp)
        ){
            Box (
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(color = MaterialTheme.colorScheme.onTertiary)
            ){
                Text(
                    text = security.symbol,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = security.name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.width(200.dp)
                )
//                Spacer(modifier = Modifier.height(4.dp))

            Spacer(modifier = Modifier.width(16.dp))
            if (security.isActive == true){
                Box(modifier = Modifier
                    .size(10.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.Green)
                )
            } else {
                Box(modifier = Modifier
                    .size(20.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.Red)
                )
            }

        }
    }
}


suspend fun readFile(context: Context): MutableList<SecuritiesLocalDataModel> {
    val scope = CoroutineScope(Dispatchers.IO)
    val result = scope.async {
        return@async dataFileReader(context.findActivity())
    }.await()
    return result
}

