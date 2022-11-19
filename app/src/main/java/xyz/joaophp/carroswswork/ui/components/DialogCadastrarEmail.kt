package xyz.joaophp.carroswswork.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import xyz.joaophp.carroswswork.R

@Composable
fun DialogCadastrarEmail(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = stringResource(R.string.ops))
        },
        text = {
            Text(text = stringResource(R.string.email_nao_cadastrado_dilog_texto))
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(R.string.ok))
            }
        }
    )
}