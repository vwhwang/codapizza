package com.bignerdranch.android.codapizza.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bignerdranch.android.codapizza.model.PizzaSize

@Composable
fun PizzaSizeDropdownMenu(
    size: PizzaSize,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onIconClicked: () -> Unit,
    onSetPizzaSize: (size: PizzaSize) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize(Alignment.TopStart)
    ) {
        IconButton(onClick = { onIconClicked() }, modifier = Modifier.padding(8.dp)) {
            Icon(Icons.Default.MoreVert, contentDescription = "Pizza Size")
        }
        Text(text = "current selected Pizza size is " + stringResource(size.label))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismissRequest() }) {
            PizzaSize.values().forEach {
                DropdownMenuItem(onClick = {
                    onSetPizzaSize(it)
                    onDismissRequest()
                }) {
                    Text(text = stringResource(it.label))
                }
            }
        }
    }
}