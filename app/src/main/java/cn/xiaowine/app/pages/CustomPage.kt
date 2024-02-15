package cn.xiaowine.app.pages

import android.annotation.SuppressLint
import android.webkit.WebView
import android.widget.LinearLayout
import cn.xiaowine.ui.WinePage
import cn.xiaowine.ui.appcompat.HyperEditText
import cn.xiaowine.ui.data.HyperEditBackgroundStyle
import cn.xiaowine.ui.tools.Tools.dp

@SuppressLint("SetTextI18n")
class CustomPage : WinePage() {

    init {
        initPage {
            toPageText()
            edittext {
                hint = "请输入"
                (layoutParams as LinearLayout.LayoutParams).apply {
                    setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                    }
                    hint = "请输入"
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                    }
                    isSingleLine = true
                    hint = "请输入"
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                    }
                    setBackgroundStyle(HyperEditBackgroundStyle.NORMAL)
                    isSingleLine = true
                    hint = "请输入"
                }
            }
            custom(HyperEditText::class.java) {
                (it as HyperEditText).apply {
                    (layoutParams as LinearLayout.LayoutParams).apply {
                        setMargins(30.dp, 10.dp, 30.dp, 10.dp)
                    }
                    setBackgroundStyle(HyperEditBackgroundStyle.STROKE)
                    isSingleLine = true
                    hint = "请输入"
                }
            }
            custom(WebView::class.java) {
                (it as WebView).apply {
                    scrollY = 550
                    scrollX = 500
                    layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400.dp)
                    loadUrl("https://www.bilibili.com/video/BV1GJ411x7h7/")
                    getSettings().userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36 Edg/121.0.0.0"
                    setWebViewClient(object : android.webkit.WebViewClient() {
                        override fun shouldOverrideUrlLoading(view: WebView, request: android.webkit.WebResourceRequest): Boolean {
                            view.loadUrl(request.url.toString())
                            return true
                        }
                    })
                }
            }
        }
    }
}