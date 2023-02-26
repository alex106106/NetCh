package com.example.netch.remote.models

data class feedModel (
    var name: String = "",
    var title: String = "",
    var content: String = "",
    var feedPostID: String = ""
) {
    constructor() : this("", "", "", "")
}