package com.example.notzeezaibtech.ui.notes_list.components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PrivacyPolicy() {

    Column(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    when (selectedMenueItem.value) {
                        "Privacy Policy" -> {
                            loadUrl("https://notzeeprivacypolicy.blogspot.com/2025/02/privacy-policy.html")
                        }

                        "Terms and Conditions" -> {
                            loadUrl("https://notzeeprivacypolicy.blogspot.com/2025/02/terms-and-conditions.html")

                        }

                        "Help and Support" -> {
                            loadUrl("https://notzeeprivacypolicy.blogspot.com/2025/02/help-and-support_25.html")

                        }

                        "About" -> {
                            loadUrl(
                                "https://notzeeprivacypolicy.blogspot.com/2025/02/about.html"
                            )
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        )


    }

}
