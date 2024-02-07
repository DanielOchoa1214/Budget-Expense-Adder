package com.dano.expenseadder.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dano.expenseadder.R
import com.dano.expenseadder.ui.components.AppTitle
import com.dano.expenseadder.ui.components.centralform.CentralForm
import com.dano.expenseadder.ui.components.centralform.CentralFormViewModel

@Composable
fun AddExpenseScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background))
            .paint(
                painter = painterResource(id = R.drawable.appbackground),
                contentScale = ContentScale.Fit
            )
            .padding(40.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical =16.dp ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CentralForm(CentralFormViewModel())
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfAddExpenseScreen(){
    AddExpenseScreen()
}