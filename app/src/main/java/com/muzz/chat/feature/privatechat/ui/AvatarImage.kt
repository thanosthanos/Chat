package com.muzz.chat.feature.privatechat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muzz.chat.R
import com.muzz.chat.ui.theme.spacing.spacing

@Composable
fun AvatarImage(
    imageResource: Int,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.size(48.dp).clickable { onClick() },
        shape = CircleShape,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        elevation = MaterialTheme.spacing.default
    ) {
        val image: Painter = painterResource(id = imageResource)
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = MaterialTheme.spacing.small),
            painter = image,
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AvatarImagePreview() {
    AvatarImage(
        imageResource = R.drawable.avatar_other_no_background
    ) {}
}
