package com.example.londonapp.domain

import com.example.londonapp.data.repositories.ParksRepository
import com.example.londonapp.data.sources.local.dataSourceModels.Park

class GetParksUseCase(
    private val parksRepository: ParksRepository
) {
    operator fun invoke(): List<Park> { return parksRepository.fetchParks() }
}
