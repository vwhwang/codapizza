package com.bignerdranch.android.codapizza.model

import androidx.annotation.StringRes
import com.bignerdranch.android.codapizza.R

enum class PizzaSize(
    @StringRes val label: Int,
    val price: Double
) {
    SMALL(R.string.small, 5.00),
    MEDIUM(R.string.medium, 10.00),
    LARGE(R.string.large, 20.00)
}