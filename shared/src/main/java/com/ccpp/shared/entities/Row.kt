package com.ccpp.shared.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Row(
    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("imageHref")
    @Expose
    val imageHref: String? = null
) {

    fun imageUrl(): String
    {
        imageHref?.let{
            return it
        }
        return ""
    }
}