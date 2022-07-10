package app.olaunchercf.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object AppDrawer {

    @Composable
    fun App(
        name: String,
        onCLick: () -> Unit
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.padding(10.dp)
        ) {
            TextButton(
                onClick = onCLick
            ) {
                Text(name)
            }
        }
    }

    @Composable
    fun Search(
        modifier: Modifier = Modifier
    ) {
        var text by remember { mutableStateOf("Hello") }

        TextField(
            value = text,
            modifier = modifier.fillMaxWidth(),
            onValueChange = { text = it },
        )
    }
}