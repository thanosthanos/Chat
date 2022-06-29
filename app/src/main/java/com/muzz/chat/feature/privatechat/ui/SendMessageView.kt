package com.muzz.chat.feature.privatechat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muzz.chat.R
import com.muzz.chat.ui.theme.spacing.spacing


@Composable
fun SendMessageView(
    modifier: Modifier,
    message: String,
    hint: String = stringResource(id = R.string.send_message_hint),
    onValueChange: (String) -> Unit,
    sendMessage: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = onValueChange,
            placeholder = { Text(hint) },
            modifier = Modifier.fillMaxWidth(fraction = 0.8f),
            shape = CircleShape,
            textStyle = MaterialTheme.typography.body1,
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Card(
                shape = CircleShape,
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 0.dp
            ) {
                IconButton(
                    onClick = {
                        sendMessage()
                        onValueChange("")
                    },
                ) {
                    Icon(
                        Icons.Default.Send,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.medium),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SendMessageViewPreview() {
    SendMessageView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
            .background(color = MaterialTheme.colors.background),
        message = "First message",
        hint = "Send message",
        onValueChange = {},
        sendMessage = {})
}
