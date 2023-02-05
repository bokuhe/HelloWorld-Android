package net.sleiv.helloworld

import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.sleiv.helloworld.di.DaggerAppComponent
import net.sleiv.helloworld.util.timber.ReportTree
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReportTree())
        }
    }
}