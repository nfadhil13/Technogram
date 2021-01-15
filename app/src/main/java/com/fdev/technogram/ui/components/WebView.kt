package com.fdev.technogram.ui.components

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ArticleWebView(
    htmlString: String,
    darkTheme: Boolean,
) {

    val backgroundColor = MaterialTheme.colors.surface.toArgb()
    val textColor = if(darkTheme) "white" else "rgb(0,0,0)"
    val context = AmbientContext.current

    val fullHtmlString = """
     <head>
        <style>
        body {
            font-family: sans-serif;
        }
        </style>
    </head>
    <body>
        ${htmlString}
    </body>
    """.trimIndent()


    val code = """javascript:(function() { 
   
                        var node = document.createElement('style');
                        
                        var spanElements = document.getElementsByTagName('span');
                        console.log(spanElements.length);
                        for (var i = 0; i < spanElements.length; i++) {
                            spanElements[i].style.color = '$textColor';
                        }
                            
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
    val webView = remember {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundColor(backgroundColor)
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    loadUrl(code)
                }
            }
            loadDataWithBaseURL("", fullHtmlString, "text/html", "UTF-8", "")
        }
    }
    AndroidView({ webView }) {
        it.setBackgroundColor(backgroundColor)
        it.loadUrl(code)
    }

}
