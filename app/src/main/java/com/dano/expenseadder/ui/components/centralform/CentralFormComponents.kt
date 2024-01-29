package com.dano.expenseadder.ui.components.centralform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType


var months = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
var expenseTypes = arrayOf("Necesidad", "Ocio / No planeado", "Planeado")
var formViewModel = CentralFormViewModel()

@Composable
fun CentralForm() {
    Column {
        ExpenseInput()
        CostInput()
        MonthDropdown()
        ExpenseTypeDropdown()
        SubmitExpenseButton()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseInput(){

    TextField(
        value = formViewModel.expense,
        placeholder = { Text(text = "En que te gastaste la plata?")},
        onValueChange = {
            formViewModel.updateExpense(it)
        },
        label = { Text(text = "Expense")})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostInput(){

    TextField(
        value = formViewModel.cost,
        placeholder = { Text(text = "Cuanto se me gasto?")},
        onValueChange = {
            formViewModel.updateCost(it)
        },
        label = { Text(text = "Cost")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthDropdown(){
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { newValue ->
            isExpanded = newValue
        },
    ) {
        TextField(
            value = formViewModel.month,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                Text(text = "¿En que mes estamos we?")
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor())
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }) {
            for (month in months){
                DropdownMenuItem(
                    text = {
                        Text(text = month)
                    },
                    onClick = {
                        formViewModel.updateMonth(month)
                        isExpanded = false
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseTypeDropdown(){
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { newValue ->
            isExpanded = newValue
        },
    ) {
        TextField(
            value = formViewModel.expenseType,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                Text(text = "¿Y que tipo de gasto es?")
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor())
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }) {
            for (type in expenseTypes){
                DropdownMenuItem(
                    text = {
                        Text(text = type)
                    },
                    onClick = {
                        formViewModel.updateExpenseType(type)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SubmitExpenseButton(){
    Button(
        onClick = { formViewModel.sendExpenseToBudget() },) {
        Text(text = "Enviar")
    }
}