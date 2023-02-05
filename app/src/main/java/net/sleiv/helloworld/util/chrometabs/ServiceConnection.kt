package net.sleiv.helloworld.util.chrometabs

import android.content.ComponentName
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsServiceConnection
import java.lang.ref.WeakReference

class ServiceConnection(serviceConnectionCallback: ServiceConnectionCallback) :
    CustomTabsServiceConnection() {
    private val refServiceConnectionCallback: WeakReference<ServiceConnectionCallback>

    override fun onServiceDisconnected(name: ComponentName?) {
        refServiceConnectionCallback.get()?.onServiceDisconnected()
    }

    override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
        refServiceConnectionCallback.get()?.onServiceConnected(client)
    }

    init {
        refServiceConnectionCallback = WeakReference(serviceConnectionCallback)
    }
}