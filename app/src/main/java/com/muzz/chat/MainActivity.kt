package com.muzz.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.muzz.chat.feature.privatechat.ui.graph.PrivateChatGraph
import com.muzz.chat.feature.privatechat.ui.graph.PrivateChatGraph.privateChatGraph
import com.muzz.chat.ui.theme.ChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatTheme {
                ChatApp()
            }
        }
    }
}

@Composable
fun ChatApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = PrivateChatGraph.route) {
        privateChatGraph()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatTheme {
        ChatApp()
    }
}
