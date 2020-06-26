package com.ccpp.shared.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListExample(
    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("rows")
    @Expose
    val rows: List<Row>? = null

) {
    fun toListRes() = ListExample(
        title = title?.let { it },
        rows = rows.let { it }
    )

    companion object {
        fun empty() = ListExample(
            title = null,
            rows = null
        )
    }
}