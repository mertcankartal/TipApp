package com.example.tipapp.uicomponents

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SplitRow() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(4.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Text(text = "Split", modifier = Modifier.align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.width(160.dp))
            Text(text = "Split")
        }

    }
}

@Preview
@Composable
fun SplitRowPrev() {
    SplitRow()
}