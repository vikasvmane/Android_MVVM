package com.vikas.android_mvvm.models.dataclasses

import com.google.gson.annotations.SerializedName

data class Row(
        @SerializedName("description")
        var description: String?,
        @SerializedName("imageHref")
        var imageHref: Any?,
        @SerializedName("title")
        var title: String?
)