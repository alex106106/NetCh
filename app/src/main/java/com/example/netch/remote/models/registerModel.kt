package com.example.netch.remote.models

import androidx.annotation.Keep

@Keep
data class registerModel (
    val email: String? = null,
    val pass: String? = null,
    val name: String? = null,
    val UUID: String? = null
        ){
    constructor() : this("", "", "", "")
}