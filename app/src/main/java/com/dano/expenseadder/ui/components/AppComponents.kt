package com.dano.expenseadder.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dano.expenseadder.R

@Composable
fun AppTitle(value: String) {
    Text(
        text = value,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
            .padding(bottom = 5.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            shadow = Shadow(
                blurRadius = 1.0f
            )
        )
    )
}

@Composable
fun AppBackground() {
    Image(
        painter = painterResource(id = R.drawable.appbackground),
        modifier = Modifier.fillMaxSize(),
        contentDescription = "Fondo de pantalla",
        contentScale = ContentScale.Inside)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExpenseInput(modifier: Modifier,
                   value: String,
                   placeholder: String,
                   onTextFieldChanged: (String) -> Unit,
                   label: String,
                   icon:  @Composable (() -> Unit),
                   keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                   readOnly: Boolean = false,
                   trailingIcon:  @Composable (() -> Unit)? = null, ){
    OutlinedTextField(
        modifier = modifier,
        value = value,
        readOnly = readOnly,
        placeholder = { Text(text = placeholder)},
        onValueChange = {
            onTextFieldChanged(it)
        },
        trailingIcon = trailingIcon,
        label = { Text(text = label)},
        keyboardOptions = keyboardOptions,
        leadingIcon = icon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black,
            placeholderColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedLeadingIconColor = Color.Black,
            unfocusedLeadingIconColor = Color.Black,
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExpenseDropdown(value: String,
                      placeholder: String,
                      label: String,
                      onTextFieldChanged: (String) -> Unit,
                      icon:  @Composable (() -> Unit),
                      dropdownOptions: Array<String>){
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { newValue ->
            isExpanded = newValue
        },
    ) {
        MyExpenseInput(
            modifier = Modifier
                .menuAnchor()
                .padding(vertical = 3.dp)
                .fillMaxWidth(),
            value = value,
            placeholder = placeholder,
            onTextFieldChanged = {},
            label = label,
            icon = icon,
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }) {
            for (i in dropdownOptions){
                DropdownMenuItem(
                    text = {
                        Text(text = i)
                    },
                    onClick = {
                        onTextFieldChanged(i)
                        isExpanded = false
                    }
                )
            }
        }
    }
}