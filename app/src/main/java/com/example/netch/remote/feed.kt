package com.example.netch.remote

import com.example.netch.remote.models.feedModel

interface feed {
    suspend fun getFeed(): feedModel
}