package com.example.londonapp.data.sources.local.dataSourceModels.price

private const val CHEAP_THRESHOLD = 20
private const val EXPENSIVE_THRESHOLD = 50

class SinglePrice(
    val value: Double,
) : Price(), Comparable<SinglePrice> {
    init {
        if (value < 0.0) throw IllegalArgumentException("Price value must be >= 0")
    }

    override fun compareTo(other: SinglePrice): Int {
        /*
        If the price has a currency property I would check if this price and the other used the
        same currency, and if they did not I would convert the other price to this currency using
        a currency converter and then I would compare values, but I don't have the ability or time
        to implement a currency converter class that looks up currencies online and checks whether
        they are real and what their exchange rate is, or to make a mock converter class that
        checks for a few currencies. I don't need it right now in this program anyway
        because all I'm doing is using prices for places in the same city which use the same currency.
         */
        return this.value.compareTo(other.value)
    }

    /**
     * Gets the affordability of this price.
     * Either free if price is 0,
     * cheap if less than 20,
     * normal if between 20 and 50,
     * or expensive if more than 50.
     */
    override fun getAffordabilityLevel(): AffordabilityLevel {
        val tinyNum = 1E-14
        val affordabilityLevel = when {
            (value in -tinyNum..tinyNum) -> AffordabilityLevel.FREE
            (value in tinyNum..CHEAP_THRESHOLD + tinyNum) -> AffordabilityLevel.CHEAP
            (value in CHEAP_THRESHOLD + tinyNum..EXPENSIVE_THRESHOLD - tinyNum) -> AffordabilityLevel.NORMAL
            (value > EXPENSIVE_THRESHOLD - tinyNum) -> AffordabilityLevel.EXPENSIVE
            else -> throw IllegalArgumentException("Unexpected value: $value.")
        }
        return affordabilityLevel
    }

    fun isFree(): Boolean
    {
        /* impossible to compare exact values of floating point
        because they can't be represented accurately in base 2 number systems
        which the computer uses to store them.
        Instead we check if their different is close enough to zero by checking
        that their difference is less than a very small number that is greater
        than the inaccuracy is floating point base 2 representation.
         */
        val tinyNumber = 1E-14
        return Math.abs(this.value - 0.0) < tinyNumber // if true the number is close enough to zero
    }
}