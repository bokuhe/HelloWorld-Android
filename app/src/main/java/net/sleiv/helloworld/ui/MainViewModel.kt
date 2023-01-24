package net.sleiv.helloworld.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.sleiv.helloworld.TextService
import javax.inject.Inject

class MainViewModel @Inject constructor(
    textService: TextService
) : ViewModel() {
    private val _greeting = MutableLiveData<String>()
    val greeting: LiveData<String> get() = _greeting

    init {
        _greeting.value = textService.getGreeting()
    }
}