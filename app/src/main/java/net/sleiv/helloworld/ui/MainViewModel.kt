package net.sleiv.helloworld.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import net.sleiv.helloworld.TextService
import net.sleiv.helloworld.data.model.User
import net.sleiv.helloworld.data.remote.api.GithubApi
import net.sleiv.helloworld.util.chrometabs.ChromeTabsWrapper
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val textService: TextService,
    private val githubApi: GithubApi,
    private val chromeTabsWrapper: ChromeTabsWrapper
) : ViewModel() {
    private val _greeting = MutableLiveData<String>()
    val greeting: LiveData<String> get() = _greeting

    private val _user = MutableLiveData<List<User>>()
    val user: LiveData<List<User>> get() = _user

    fun initGreeting() {
        _greeting.postValue(textService.getGreeting())
    }

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e("Exception: ${throwable.localizedMessage}")
    }

    suspend fun initUserRetrofitCoroutine() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = githubApi.getUser()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _user.value = response.body()
                } else {
                    Timber.d(response.message())
                }
            }
        }
    }

    fun initUserRetrofitRx() {

    }
    
    fun openChromeTabs(url: String) {
        chromeTabsWrapper.open(url)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}