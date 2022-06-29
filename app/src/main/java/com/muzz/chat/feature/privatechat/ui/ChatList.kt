package com.muzz.chat.feature.privatechat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import com.muzz.chat.feature.privatechat.domain.model.Message
import com.muzz.chat.ui.theme.spacing.spacing

@Composable
fun ChatList(
    messages: List<Message>
) {
    val listState = rememberLazyListState()
    LaunchedEffect(key1 = messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(
            top = MaterialTheme.spacing.small,
            bottom = MaterialTheme.spacing.small
        ),
    ) {
        items(messages) { message ->
            message.header?.let {
                Header(header = it)
            }
            MessageCell(message = message)
        }
    }
}

@Composable
fun Header(
    header: String,
) {
    val dateFormatted = header.split(" ")
    if (dateFormatted.size != 2) {
        return
    }

    getAnnotatedDate(
        day = dateFormatted[0],
        time = dateFormatted[1],
    )?.let {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = it,
            )
        }
    }
}

@Composable
fun getAnnotatedDate(
    day: String?,
    time: String?,
): AnnotatedString? {
    if (day == null || time == null) return null

    val source = "$day $time"
    val builder = AnnotatedString.Builder()
    builder.append(source)
    val startDay = source.indexOf(day)
    val endDay = startDay + day.length
    val dayStyle = SpanStyle(
        color = Color.Gray,
        fontWeight = FontWeight.SemiBold,
    )
    builder.addStyle(dayStyle, startDay, endDay)
    val timeStyle = SpanStyle(
        color = Color.Gray,
    )
    builder.addStyle(timeStyle, endDay + 1, source.length)
    builder.addStringAnnotation("TAG", source, startDay, endDay)

    return builder.toAnnotatedString()
}
