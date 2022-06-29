package com.muzz.chat.feature.privatechat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muzz.chat.feature.privatechat.domain.model.Message
import com.muzz.chat.ui.theme.OtherMessageColor
import com.muzz.chat.ui.theme.spacing.spacing

@Composable
fun MessageCell(
    message: Message,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small,
            ),
        horizontalAlignment = when {
            message.isMine -> Alignment.End
            else -> Alignment.Start
        },
    ) {
        val paddingStart =
            if (message.isMine) MaterialTheme.spacing.veryLarge else MaterialTheme.spacing.default
        val paddingEnd =
            if (message.isMine) MaterialTheme.spacing.default else MaterialTheme.spacing.veryLarge
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .padding(
                    start = paddingStart,
                    end = paddingEnd
                ),
            shape = cardShapeForTail(message.hasTail, message.isMine),
            backgroundColor = when {
                message.isMine -> MaterialTheme.colors.primary
                else -> MaterialTheme.colors.OtherMessageColor
            },
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.medium),
                text = message.content,
                color = when {
                    message.isMine -> MaterialTheme.colors.onPrimary
                    else -> MaterialTheme.colors.onBackground
                },
            )
        }
    }
}

@Composable
fun cardShapeForTail(hasTail: Boolean, isMine: Boolean): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    if(hasTail.not()) {
        return roundedCorners
    }

    return when {
        isMine -> roundedCorners.copy(bottomEnd = CornerSize(0))
        else -> roundedCorners.copy(bottomStart = CornerSize(0))
    }
}

@Preview(showBackground = true)
@Composable
fun MessageCellIsMinePreview() {
    MessageCell(
        message = Message(
            hasTail = true,
            createdAt = 0,
            isMine = true,
            content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun MessageCellIsNotMinePreview() {
    MessageCell(
        message = Message(
            hasTail = true,
            createdAt = 0,
            isMine = false,
            content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        ),
    )
}
