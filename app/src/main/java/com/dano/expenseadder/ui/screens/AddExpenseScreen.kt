package com.dano.expenseadder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dano.expenseadder.R
import com.dano.expenseadder.ui.components.AppTitle
import com.dano.expenseadder.ui.components.centralform.CentralForm

@Composable
fun AddExpenseScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background))
            .paint(
                painter = painterResource(id = R.drawable.appbackground),
                contentScale = ContentScale.Fit
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTitle(value = "Hola BB que mas pues")
        CentralForm()
    }
}

@Preview
@Composable
fun DefaultPreviewOfAddExpenseScreen(){
    AddExpenseScreen()
}