package net.sleiv.helloworld.util.chrometabs

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection

class ChromeTabsWrapper constructor(private val context: Context) : ServiceConnectionCallback {
    private var connection: CustomTabsServiceConnection? = null
    private var client: CustomTabsClient? = null

    fun open(url: String) {
        val builder = CustomTabsIntent.Builder().apply {

        }
        builder.build().apply {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            launchUrl(context, Uri.parse(url))
        }
    }

    fun bind() {
        if (client != null) return

        connection ?: ServiceConnection(this).let {
            CustomTabsClient.bindCustomTabsService(context, CUSTOM_TAB_PACKAGE_NAME, it)
        }
    }

    fun unbind() {
        connection?.let {
            context.unbindService(it)
            client = null
            connection = null
        }
    }

    override fun onServiceConnected(client: CustomTabsClient) {
        this.client = client
    }

    override fun onServiceDisconnected() {
        client = null
    }

    companion object {
        const val CUSTOM_TAB_PACKAGE_NAME: String = "com.android.chrome"
    }
}