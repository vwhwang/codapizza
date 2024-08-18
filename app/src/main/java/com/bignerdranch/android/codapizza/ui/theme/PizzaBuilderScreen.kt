package com.bignerdranch.android.codapizza.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bignerdranch.android.codapizza.R
import com.bignerdranch.android.codapizza.model.Pizza
import com.bignerdranch.android.codapizza.model.PizzaSize
import com.bignerdranch.android.codapizza.model.Topping
import java.text.NumberFormat

@Preview
@Composable
fun PizzaBuilderScreen(
    modifier: Modifier = Modifier
) {
    var pizza by rememberSaveable {
        mutableStateOf(Pizza())
    }

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {

        (if (pizza.size == null) {
            PizzaSize.SMALL
        } else {
            pizza.size
        })?.let {
            PizzaSizeDropdownMenu(
                size = it,
                expanded = expanded,
                onDismissRequest = { expanded = false },
                onIconClicked = { expanded = true },
                onSetPizzaSize = {size ->
                    pizza = pizza.chooseSize(size)
                }
            )
        }

        ToppingsList(
            pizza = pizza,
            onEditPizza = { pizza = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )
        OrderButton(
            pizza = pizza,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
private fun ToppingsList(
    pizza: Pizza,
    onEditPizza: (Pizza) -> Unit,
    modifier: Modifier
) {
    var toppingBeingAdded by rememberSaveable {
        mutableStateOf<Topping?>(null)
    }
    toppingBeingAdded?.let { topping ->
        ToppingPlacementDialog(
            topping = topping,
            onDismissRequest = {
                toppingBeingAdded = null
            },
            onSetToppingPlacement = { placement ->
                onEditPizza(
                    pizza.withToppings(
                        topping = topping,
                        placement = placement
                    )
                )
            }
        )
    }
    LazyColumn(modifier = modifier) {
        item {
            PizzaHeroImage(pizza = pizza, modifier = Modifier.padding(16.dp))
        }
        items(Topping.values()) { topping ->
            ToppingCell(
                topping = topping,
                placement = pizza.toppings[topping],
                onClickTopping = {
                    toppingBeingAdded = topping
                }
            )
        }
    }
}

@Composable
private fun OrderButton(
    pizza: Pizza,
    modifier: Modifier
) {
    Button(
        modifier = modifier,
        onClick = { }) {
        val currencyFormatter = remember {
            NumberFormat.getCurrencyInstance()
        }
        val price = currencyFormatter.format(pizza.price)
        val buttonText = stringResource(R.string.place_order_button, price)
        Text(text = buttonText.toUpperCase(Locale.current))
    }
}