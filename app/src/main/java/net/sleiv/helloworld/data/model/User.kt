package net.sleiv.helloworld.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    private var login: String,

    @SerializedName("id")
    private var id: Int,

    @SerializedName("html_url")
    private var htmlUrl: String
)