package com.muzz.chat.feature.privatechat.ui.model

import com.muzz.chat.R

data class User(
    val name: String,
    val image: Int,
    val isMe: Boolean,
)

// Create 2 random users for demo purposes,
// is me and the other user (my wife actually!!!)
val thanos = User(
    name = "Thanos",
    image = R.drawable.avatar_me_no_background,
    isMe = true,
)

val labrini = User(
    name = "Labrini",
    image = R.drawable.avatar_other_no_background,
    isMe = false,
)

fun User.getOtherUser(): User {
    return if(this.isMe) labrini else thanos
}
