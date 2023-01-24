@file:Suppress("unused")

package net.sleiv.helloworld.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.sleiv.helloworld.ui.MainActivity

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    internal abstract fun injectMainActivity(): MainActivity
}