package com.ccpp.shared.network

import com.ccpp.shared.entities.*
import retrofit2.Call
import retrofit2.http.*

internal interface ApiClient {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getList():Call<ListExample>
}