package net.sleiv.helloworld.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import net.sleiv.helloworld.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}