package net.sleiv.helloworld.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.sleiv.helloworld.databinding.ActivityMainBinding
import net.sleiv.helloworld.di.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)
        binding.viewModel = viewModel


//        viewModel.greeting.observe(this) {
//            binding.tvHello.text = it
//        }
        viewModel.user.observe(this) {
            binding.btnEvent.text = it.toString()
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.initUserRetrofitCoroutine()
        }
    }
}