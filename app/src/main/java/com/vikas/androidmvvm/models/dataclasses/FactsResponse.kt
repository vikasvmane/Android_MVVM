package com.vikas.androidmvvm.models.dataclasses

import com.google.gson.annotations.SerializedName

data class FactsResponse(
        @SerializedName("rows")
        var rows: List<Row?>?,
        @SerializedName("title")
        var title: String?
)