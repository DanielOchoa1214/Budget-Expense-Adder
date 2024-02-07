package com.dano.expenseadder.ui.components.centralform

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dano.expenseadder.ui.theme.Primary
import com.dano.expenseadder.R
import com.dano.expenseadder.ui.components.AppTitle
import com.dano.expenseadder.ui.components.MyExpenseDropdown
import com.dano.expenseadder.ui.components.MyExpenseInput
import com.dano.expenseadder.ui.theme.BackgroundColor
import com.dano.expenseadder.ui.theme.ErrorText


var months = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
var expenseTypes = arrayOf("Necesidad", "Ocio / No planeado", "Planeado")

@Composable
fun CentralForm(viewModel: CentralFormViewModel) {
    val expense: String by viewModel.expense.observeAsState(initial = "")
    val price: String by viewModel.price.observeAsState(initial = "")
    val month: String by viewModel.month.observeAsState(initial = "")
    val expenseType: String by viewModel.expenseType.observeAsState(initial = "")
    val submitEnabled: Boolean by viewModel.submitEnabled.observeAsState(initial = false)
    val successMessage: String by viewModel.successMessage.observeAsState(initial = "")

    Column(
        modifier = Modifier
            .background(Primary)
            .border(2.dp, Color.Black)
            .padding(16.dp)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTitle(value = "¿En que te gastaste la platica?")
        ExpenseInput(expense) {viewModel.onFormChange(it, price, month, expenseType)}
        PriceInput(price) {viewModel.onFormChange(expense, it, month, expenseType)}
        MonthDropdown(month) {viewModel.onFormChange(expense, price, it, expenseType)}
        ExpenseTypeDropdown(expenseType) {viewModel.onFormChange(expense, price, month, it)}
        SubmitExpenseButton(submitEnabled) {viewModel.onSubmitClick()}
        ResponseMessageText(successMessage)
    }
}

@Composable
fun ExpenseInput(expense: String, onTextFieldChanged: (String) -> Unit){
    MyExpenseInput(
        modifier = Modifier
            .padding(vertical = 3.dp)
            .fillMaxWidth(),
        value = expense,
        placeholder = "En que te gastaste la plata?",
        onTextFieldChanged = {
            onTextFieldChanged(it)
        },
        label = "Expense",
        icon = {
            Icon(painter = painterResource(id = R.drawable.expense_icon),
                contentDescription = "Expense Icon",
                modifier = Modifier.size(32.dp))
        },
        )
}

@Composable
fun PriceInput(price: String, onTextFieldChanged: (String) -> Unit){
    MyExpenseInput(
        modifier = Modifier
            .padding(vertical = 3.dp)
            .fillMaxWidth(),
        value = price,
        placeholder = "Cuanto se me gasto?",
        onTextFieldChanged = {
            onTextFieldChanged(it)
        },
        label = "Price",
        icon = {
            Icon(painter = painterResource(id = R.drawable.price_icon),
                contentDescription = "Price Icon",
                modifier = Modifier.size(32.dp))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        )
}

@Composable
fun MonthDropdown(month: String, onTextFieldChanged: (String) -> Unit){
    MyExpenseDropdown(
        value = month,
        placeholder = "¿En que mes estamos we?",
        label = "Month",
        onTextFieldChanged = {
            onTextFieldChanged(it)
        },
        icon = {
            Icon(painter = painterResource(id = R.drawable.month_icon),
                contentDescription = "Month Icon",
                modifier = Modifier.size(32.dp))
        },
        dropdownOptions = months
    )
}

@Composable
fun ExpenseTypeDropdown(expenseType: String, onTextFieldChanged: (String) -> Unit){
    MyExpenseDropdown(
        value = expenseType,
        placeholder = "¿Y que tipo de gasto es?",
        label = "Type",
        onTextFieldChanged = {
            onTextFieldChanged(it)
        },
        icon = {
            Icon(painter = painterResource(id = R.drawable.expense_type_icon),
                contentDescription = "Expense Type Icon",
                modifier = Modifier.size(32.dp))
        },
        dropdownOptions = expenseTypes
    )
}

@Composable
fun SubmitExpenseButton(submitEnabled: Boolean, onSubmitSelected: () -> Unit){
    Button(
        modifier = Modifier
            .padding(vertical = 3.dp)
            .fillMaxWidth(),
        onClick = { onSubmitSelected() },
        enabled = submitEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black),
        shape = RoundedCornerShape(10)
    ) {
        Text(
            text = "Send",
            style = TextStyle(
                fontSize = 20.sp
            )
        )
    }
}

@Composable
fun ResponseMessageText(message: String){
    Text(
        text = message,
        style = TextStyle(
            shadow = Shadow(
                blurRadius = 1.0f
            ),
            fontWeight = FontWeight.Bold
        )
    )
}