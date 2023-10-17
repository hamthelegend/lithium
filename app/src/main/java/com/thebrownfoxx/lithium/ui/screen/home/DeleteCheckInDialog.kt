package com.thebrownfoxx.lithium.ui.screen.home

import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thebrownfoxx.lithium.R
import com.thebrownfoxx.lithium.domain.CheckIn
import com.thebrownfoxx.lithium.ui.extension.TimedDateFormatter
import com.thebrownfoxx.lithium.ui.theme.LithiumIcons

@Composable
fun DeleteCheckInDialog(
    checkInToDelete: CheckIn?,
    onCommitDeleteCheckIn: () -> Unit,
    onCancelDeleteCheckIn: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (checkInToDelete != null) {
        AlertDialog(
            onDismissRequest = onCancelDeleteCheckIn,
            icon = {
                Icon(
                    imageVector = LithiumIcons.Delete,
                    contentDescription = stringResource(R.string.delete_check_in),
                )
            },
            title = {
                Text(text = stringResource(R.string.delete_check_in))
            },
            text = {
                Text(
                    text = stringResource(
                        R.string.delete_check_in_confirmation,
                        checkInToDelete.feeling.title,
                        TimedDateFormatter.format(checkInToDelete.instant),
                    ),
                )
            },
            confirmButton = {
                Button(onClick = onCommitDeleteCheckIn) {
                    Text(text = stringResource(R.string.yes))
                }
            },
            dismissButton = {
                TextButton(onClick = onCancelDeleteCheckIn) {
                    Text(text = stringResource(R.string.no))
                }
            },
        )
    }
}