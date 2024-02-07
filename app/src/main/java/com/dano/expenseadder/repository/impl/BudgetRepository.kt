package com.dano.expenseadder.repository.impl

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dano.expenseadder.apiKey
import com.dano.expenseadder.model.Expense
import com.dano.expenseadder.repository.interfaces.BudgetApi
import com.dano.expenseadder.repository.utils.RetrofitService
import com.dano.expenseadder.ui.components.centralform.CentralFormViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BudgetRepository {
    private val key: String = apiKey
    private var api: BudgetApi = RetrofitService.getBudgetApi()

    private val _successMessage = MutableLiveData<String>()
    val successMessage: LiveData<String> = _successMessage

    fun sendExpense(expense: Expense){
        _successMessage.value = "ENVIANDO PETICION..."

        val expenseMap = ObjectMapper().registerModule(KotlinModule()).convertValue(expense, Map::class.java) as Map<String, String>

        val call: Call<Expense> = api.sendNewExpense(key, expenseMap)
        call.enqueue(object : Callback<Expense>{

            override fun onResponse(call: Call<Expense>, response: Response<Expense>) {
                _successMessage.value = if (response.isSuccessful) {
                    "El gasto se envio correctamente al presupuesto :)"
                } else {
                    "Me ñañe, error ${response.code()}"
                }
            }

            override fun onFailure(call: Call<Expense>, t: Throwable) {
                _successMessage.value = "Me ñañe, no se ni que paso"
            }
        })
    }
}