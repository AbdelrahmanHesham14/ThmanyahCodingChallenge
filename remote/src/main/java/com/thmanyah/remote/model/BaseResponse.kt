package com.thmanyah.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BaseResponse(
    val message: String? = null
)