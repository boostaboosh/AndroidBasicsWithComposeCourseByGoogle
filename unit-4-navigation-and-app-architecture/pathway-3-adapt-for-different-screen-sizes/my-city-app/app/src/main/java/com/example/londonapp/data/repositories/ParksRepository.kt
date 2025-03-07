package com.example.londonapp.data.repositories

import com.example.londonapp.data.sources.local.ParksLocalDataSource
import com.example.londonapp.data.sources.local.dataSourceModels.Park

class ParksRepository(
    private val parksLocalDataSource: ParksLocalDataSource
) {
    fun fetchParks(): Set<Park> { return parksLocalDataSource.parks}
}
