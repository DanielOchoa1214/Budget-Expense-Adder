package com.dano.expenseadder.ui.components.centralform

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CentralFormViewModel : ViewModel(){
    var expense by mutableStateOf("")
    var cost by mutableStateOf("")
    var month by mutableStateOf("")
    var expenseType by mutableStateOf("")

    fun updateExpense(value: String) {
        expense = value
    }

    fun updateCost(value: String) {
        cost = value
    }

    fun updateMonth(value: String) {
        month = value
    }

    fun updateExpenseType(value: String) {
        expenseType = value
    }

    fun sendExpenseToBudget(){
        Log.d("HOLI", "$expense $cost $month $expenseType")
    }

}