package com.che.githubusers.ui.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.che.githubusers.R

@Composable
@NonRestartableComposable
fun FailureDialog(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        icon = {
            Icon(
                ImageVector.vectorResource(id = R.drawable.ic_warning),
                contentDescription = stringResource(id = R.string.alertDialog)
            )
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = text)
        },
        onDismissRequest = onDismiss,
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(stringResource(id = R.string.dismiss))
            }
        }
    )
}
