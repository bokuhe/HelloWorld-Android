package net.sleiv.helloworld.di

import android.app.Application
import dagger.Module
import dagger.Provides
import net.sleiv.helloworld.util.chrometabs.ChromeTabsWrapper

@Module
class UtilsModule {
    @Provides
    fun providesChromeTabsWrapper(app: Application): ChromeTabsWrapper {
        return ChromeTabsWrapper(app.applicationContext)
    }
}