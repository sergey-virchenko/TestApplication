package com.rounds.myapplication.domain.sample

import com.rounds.myapplication.domain.model.Item

interface JsonSampleProvider {
    suspend fun loadSampleItems(): List<Item>

    companion object {
        const val FILE_NAME = "sample.json"
    }
}
