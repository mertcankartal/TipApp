package com.example.tipapp.uicomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedTF(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,//yazılacak text
    labelId: String,//textfield label texti
    enabled: Boolean,
    isSingleLine: Boolean,//tek satır mı olacak
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(value = valueState.value, onValueChange = {
        valueState.value = it
    },
    label = { Text(text = labelId) },
    leadingIcon = { Icon(imageVector = Icons.Rounded.Person, contentDescription = "")}, //soldaki icon
    singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 14.sp, color = Color.Red),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        modifier = modifier.padding(10.dp).fillMaxWidth()
    )

}