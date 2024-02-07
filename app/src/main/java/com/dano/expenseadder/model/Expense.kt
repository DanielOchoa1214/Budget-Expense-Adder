package com.dano.expenseadder.model

data class Expense (
    var type: String,
    var expenseName: String,
    var price: String,
    var sheetName: String
)