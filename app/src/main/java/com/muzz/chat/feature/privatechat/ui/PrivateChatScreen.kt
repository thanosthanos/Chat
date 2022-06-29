package com.muzz.chat.feature.privatechat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.muzz.chat.extension.collectAsStateLifecycleAware
import com.muzz.chat.feature.privatechat.domain.model.Message
import com.muzz.chat.feature.privatechat.ui.actions.PrivateChatStateEffect
import com.muzz.chat.feature.privatechat.ui.model.User
import com.muzz.chat.feature.privatechat.ui.viewmodel.PrivateChatViewModel
import com.muzz.chat.ui.theme.spacing.spacing

@Composable
fun PrivateChatScreen(
    viewModel: PrivateChatViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateLifecycleAware().value

    PrivateChatScreen(
        user = state.selectedUser,
        message = state.message,
        messages = state.messages,
        changeUser = { viewModel.onAction(action = PrivateChatStateEffect.Action.OnChangeUser) },
        onMessageValueChanged = {
            viewModel.onAction(
                action = PrivateChatStateEffect.Action.OnMessageValueChanged(
                    it
                )
            )
        },
        sendMessage = { viewModel.onAction(action = PrivateChatStateEffect.Action.OnSendMessage) },
    )
}

@Composable
fun PrivateChatScreen(
    user: User,
    message: String,
    messages: List<Message>,
    changeUser: () -> Unit,
    onMessageValueChanged: (String) -> Unit,
    sendMessage: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)) {
        Card(elevation = 10.dp) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.ArrowBackIos,
                        contentDescription = null,
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                        tint = MaterialTheme.colors.primary,
                    )
                    AvatarImage(
                        imageResource = user.image,
                        onClick = changeUser,
                    )
                    Text(
                        modifier = Modifier.padding(start = MaterialTheme.spacing.small),
                        text = user.name,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Icon(
                    Icons.Filled.MoreHoriz,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                    tint = Color.LightGray,
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = MaterialTheme.spacing.medium),
        ) {
            ChatList(
                messages = messages,
            )
        }

        Card(elevation = 10.dp) {
            SendMessageView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.small)
                    .background(color = MaterialTheme.colors.background),
                message = message,
                onValueChange = onMessageValueChanged,
                sendMessage = sendMessage
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrivateChatScreenPreview() {
    PrivateChatScreen()
}
