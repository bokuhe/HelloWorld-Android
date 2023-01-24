package net.sleiv.helloworld

import dagger.Module
import javax.inject.Inject

@Module
class TextService @Inject constructor() {
    fun getGreeting(): String = "Hello!"
}