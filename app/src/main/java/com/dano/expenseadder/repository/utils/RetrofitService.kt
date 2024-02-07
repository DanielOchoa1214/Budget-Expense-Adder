package com.dano.expenseadder.repository.utils

import com.dano.expenseadder.budgetUrl
import com.dano.expenseadder.repository.interfaces.BudgetApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitService {
    companion object {
        private var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(budgetUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getBudgetApi(): BudgetApi {
            return retrofit.create(BudgetApi::class.java)
        }
    }
}