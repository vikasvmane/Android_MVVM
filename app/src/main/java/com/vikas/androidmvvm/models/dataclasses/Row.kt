package com.vikas.androidmvvm.models.dataclasses

import com.google.gson.annotations.SerializedName

data class Row(
        @SerializedName("description")
        var description: String?,
        @SerializedName("imageHref")
        var imageHref: String?,
        @SerializedName("title")
        var title: String?
)