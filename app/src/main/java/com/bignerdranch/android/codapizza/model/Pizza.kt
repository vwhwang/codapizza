package com.bignerdranch.android.codapizza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pizza(
    val size: PizzaSize? = null,
    val toppings: Map<Topping, ToppingPlacement> = emptyMap()
) : Parcelable {
    val sizePrice = size?.price ?: PizzaSize.SMALL.price
    val price: Double
        get() = sizePrice + toppings.asSequence()
            .sumOf { (_, toppingPlacement) ->
                when (toppingPlacement) {
                    ToppingPlacement.Left, ToppingPlacement.Right -> 0.5
                    ToppingPlacement.All -> 1.0
                }
            }

    fun withToppings(topping: Topping, placement: ToppingPlacement?): Pizza {
        return copy(
            toppings = if (placement == null) {
                toppings - topping
            } else {
                toppings + (topping to placement)
            }
        )
    }

    fun chooseSize(size: PizzaSize): Pizza {
        return copy(
            size = size,
            toppings = toppings
        )
    }
}
