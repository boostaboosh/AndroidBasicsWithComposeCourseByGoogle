package com.example.londonapp.data.sources.local.dataSourceModels.price

import java.util.Locale

enum class AffordabilityLevel {
    EXPENSIVE,
    NORMAL,
    CHEAP,
    FREE
}

fun AffordabilityLevel.toCapitalisedString() : String {
    return this.name
        .lowercase(Locale.ROOT)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}
