package com.dano.expenseadder.repository.interfaces

import com.dano.expenseadder.model.Expense
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface BudgetApi {
    @GET("macros/s/{apiKey}/exec")
    fun sendNewExpense(@Path("apiKey") apiKey: String, @QueryMap expense: Map<String, String>) : Call<Expense>
}