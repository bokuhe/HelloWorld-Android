package net.sleiv.helloworld

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.sleiv.helloworld.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}