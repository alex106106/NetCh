package com.example.netch.domain.items

import com.example.netch.remote.models.feedModel

data class feedItems (
    var name: String? = null,
    var title: String? = null,
    var content: String? = null,
    var feedPostID: String? = null
)
fun feedModel.toFeedItems() = feedItems(name, title, content, feedPostID)