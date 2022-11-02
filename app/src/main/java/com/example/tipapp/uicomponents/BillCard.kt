package com.example.tipapp.uicomponents

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipapp.ui.theme.BillCardBackground


@Composable
fun BillCard(
    modifier: Modifier= Modifier,
    totalPersonAmount: Double = 12.0
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(12.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color = BillCardBackground
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val total = "%.2f".format(totalPersonAmount)
            Text(
                text = "Toplam Kişi Sayısı",
                modifier = modifier,
                fontSize = 24.sp,
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = "$total $",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Preview
@Composable
fun BillCardPreview() {
    BillCard(modifier = Modifier,)
}