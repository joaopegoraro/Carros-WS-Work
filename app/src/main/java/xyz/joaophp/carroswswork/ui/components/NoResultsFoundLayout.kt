package xyz.joaophp.carroswswork.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NoResultsFoundLayout(
    modifier: Modifier = Modifier,
    text: String = "",
    icon: Painter,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (text.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = text,
                textAlign = TextAlign.Center,
            )
        }
        IconButton(onClick = {
            onClick()
        }) {
            Icon(
                painter = icon,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = null
            )
        }
    }
}
