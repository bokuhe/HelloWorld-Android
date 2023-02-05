package net.sleiv.helloworld.data.remote.module

import dagger.Module
import dagger.Provides
import net.sleiv.helloworld.data.remote.api.GithubApi
import net.sleiv.helloworld.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideGithubApi(okHttpClient: OkHttpClient, factory: Converter.Factory): GithubApi {
        return Retrofit.Builder()
            .baseUrl(Constants.GITHUB_URL)
            .addConverterFactory(factory)
            .client(okHttpClient)
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}