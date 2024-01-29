package com.dano.expenseadder.ui.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dano.expenseadder.ui.screens.AddExpenseScreen

@Composable
fun ExpenseAdderApp(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AddExpenseScreen()
    }
}