package com.example.netch.domain

import com.example.netch.domain.items.feedItems
import com.example.netch.repository.FirebaseRepository

class getFeedUseCase constructor(val feedRepository: FirebaseRepository){
    suspend operator fun invoke(): List<feedItems>{
        return feedRepository.getFeed().shuffled()
    }
}