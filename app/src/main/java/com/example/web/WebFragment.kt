package com.example.web

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.http.SslError
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.web_view_fragment.*


class WebFragment: Fragment() {

    private val MAX_PROGRESS = 100
    private var webView: WebView? = null
    private var webUrl: String? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.web_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webViewFrag)
        progressBar = view.findViewById(R.id.progressBar)
        webUrl = arguments?.getString("url")
       // requireActivity().supportFragmentManager.popBackStack()

        initWebView()
        setWebClient()
        handlePullToRefresh()
        webView!!.loadUrl(webUrl)
        }

    private fun handlePullToRefresh() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.loadWithOverviewMode = true
        webView!!.settings.useWideViewPort = true
        webView!!.settings.domStorageEnabled = true

        webView!!.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }

    }

    private fun setWebClient() {
        webView?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                progressBar?.progress = newProgress
                if (newProgress < MAX_PROGRESS && progressBar?.visibility == ProgressBar.GONE) {
                    progressBar?.visibility = ProgressBar.VISIBLE
                }

                if (newProgress == MAX_PROGRESS) {
                    progressBar?.visibility = ProgressBar.GONE
                }
            }
        }
    }

}