package net.sleiv.helloworld.util.chrometabs

import androidx.browser.customtabs.CustomTabsClient

interface ServiceConnectionCallback {
    fun onServiceConnected(client: CustomTabsClient)
    fun onServiceDisconnected()
}