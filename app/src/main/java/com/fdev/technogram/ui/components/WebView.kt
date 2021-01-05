package com.fdev.technogram.ui.components

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ArticleWebView(
        htmlString: String,
        modifier: Modifier = Modifier
) {
    val fullHtmlString = """
     <head>
        <style>
        @font-face {
            font-family: 'Poppins';
            src: url('https://fonts.googleapis.com/css2?family=Poppins&display=swap')
        }
        body {
            font-family: 'Poppins', sans-serif;
        }
        </style>
    </head>
    <body>
        ${htmlString}
    </body>
    """.trimIndent()

    AndroidView(
            modifier = modifier, viewBlock = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    val code = """javascript:(function() { 
   
                        var node = document.createElement('style');
               
                            
                        node.type = 'text/css';
                        node.innerHTML = '
                        img{
                            width : 100%;
                        }
                        br {
                            content: " ";
                            display: block;
                            margin: 10px 10;
                            line-height: 20px;
                        }
                        ';
                
                        document.head.appendChild(node);
                     
                    })()""".trimIndent()

                    loadUrl(code)
                }
            }
            loadDataWithBaseURL("", fullHtmlString, "text/html", "UTF-8", "")
        }
    })
}
