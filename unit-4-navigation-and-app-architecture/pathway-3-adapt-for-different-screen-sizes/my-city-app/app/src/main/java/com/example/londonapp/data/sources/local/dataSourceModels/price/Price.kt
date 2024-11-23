package com.example.londonapp.data.sources.local.dataSourceModels.price

abstract class Price() {
    abstract fun getAffordabilityLevel(): AffordabilityLevel
}