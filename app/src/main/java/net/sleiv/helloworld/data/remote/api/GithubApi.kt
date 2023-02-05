package net.sleiv.helloworld.data.remote.api

import net.sleiv.helloworld.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface GithubApi {
    @GET("/users")
    fun getUser(): Response<List<User>>
}