package com.example.aurika.ui

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.aurika.R
import com.example.aurika.databinding.FragmentWebBinding


class WebFragment : Fragment() {

    lateinit var bookName: String

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWebBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_web, container, false
        )

        val args: WebFragmentArgs by navArgs()

        val url = args.bookUrl
        bookName = args.bookName

        val fileExtension = MimeTypeMap.getFileExtensionFromUrl(url)

        bookName += ".$fileExtension"

        val webView = binding.webview

        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode
        webView.settings.useWideViewPort = false
        webView.setInitialScale(150)
        webView.settings.builtInZoomControls = true
        webView.scrollTo(0, 50)


        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(url)

        SetUpDownloadListener(webView, bookName)

        return binding.root
    }

    private fun SetUpDownloadListener(webView: WebView, bookName: String) {
        webView.setDownloadListener { url, userAgent, contentDisposition, mimeType, contentLength ->
            val request = DownloadManager.Request(
                Uri.parse(url)
            )
            request.setMimeType(mimeType)
            val cookies =
                CookieManager.getInstance().getCookie(url)
            request.addRequestHeader("cookie", cookies)
            request.addRequestHeader("User-Agent", userAgent)
            request.setDescription("Downloading ${guessTitle(url, contentDisposition, mimeType)}")
            request.setTitle(
                guessTitle(url, contentDisposition, mimeType)
            )
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                guessTitle(url, contentDisposition, mimeType)
            )
            val dm = this.context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            dm.enqueue(request)

            Toast.makeText(
                this.context, "Downloading ${guessTitle(
                    url,
                    contentDisposition,
                    mimeType
                )}", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun guessTitle(
        url: String?,
        contentDisposition: String?,
        mimeType: String?
    ): String? {
        return URLUtil.guessFileName(
            url, contentDisposition,
            mimeType
        )
    }

}


