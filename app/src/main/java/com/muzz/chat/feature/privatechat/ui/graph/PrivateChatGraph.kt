package com.muzz.chat.feature.privatechat.ui.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.muzz.chat.feature.privatechat.ui.PrivateChatScreen
import com.muzz.chat.navigation.NavigationRoute
import com.muzz.chat.navigation.routeComposable

object PrivateChatGraph : NavigationRoute() {
    override val route: String = "private_chat_graph"

    fun NavGraphBuilder.privateChatGraph() {
        navigation(
            startDestination = Screens.PrivateChat.route,
            route = PrivateChatGraph.route
        ) {
            routeComposable(Screens.PrivateChat) {
                PrivateChatScreen()
            }
        }
    }

    internal sealed class Screens(override val route: String) : NavigationRoute() {
        object PrivateChat : Screens("private_chat")
    }

}
