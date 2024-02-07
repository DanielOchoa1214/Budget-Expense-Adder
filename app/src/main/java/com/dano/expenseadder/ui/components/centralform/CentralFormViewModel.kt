package com.dano.expenseadder.ui.components.centralform

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dano.expenseadder.model.Expense
import com.dano.expenseadder.repository.impl.BudgetRepository

class CentralFormViewModel : ViewModel(){
    private var repository: BudgetRepository = BudgetRepository()

    private val _expense = MutableLiveData<String>()
    val expense: LiveData<String> = _expense

    private val _price = MutableLiveData<String>()
    val price: LiveData<String> = _price

    private val _month = MutableLiveData<String>()
    val month: LiveData<String> = _month

    private val _expenseType = MutableLiveData<String>()
    val expenseType: LiveData<String> = _expenseType

    private val _submitEnabled = MutableLiveData<Boolean>()
    val submitEnabled: LiveData<Boolean> = _submitEnabled

    val successMessage: LiveData<String> = repository.successMessage

    fun onFormChange(expense: String, price: String, month: String, expenseType: String){
        _expense.value = expense
        _price.value = price
        _month.value = month
        _expenseType.value = expenseType
        _submitEnabled.value = isValidField(expense) && isValidField(month) &&
                isValidField(expenseType) && isValidPrice(price)
    }

    private fun isValidField(expense: String): Boolean{
        return expense !== ""
    }

    private fun isValidPrice(price: String): Boolean{
        return price !== "" && price.toInt() > 100
    }

    fun onSubmitClick(){
        Log.d("WENAS", "${expense.value} ${price.value} ${month.value} ${expenseType.value} ${successMessage.value}")
        repository.sendExpense(Expense(expenseType.value as String, expense.value as String,
            price.value as String, month.value as String))
    }

}