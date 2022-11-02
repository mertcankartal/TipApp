package com.example.tipapp.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val IconbuttonSizeModifier = Modifier.size(40.dp)

@Composable
fun SplitButton(
    imageVector: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = Color.Black.copy(alpha = .6f),
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 4.dp,
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .clickable { onClick.invoke() }
            .then(IconbuttonSizeModifier),
        shape = CircleShape,
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        Icon(imageVector = imageVector, contentDescription = "", tint = tint, modifier = Modifier.requiredSize(25.dp))
        //required size ile içerdeki iconun boyutunu düşürüyoruz.

    }

}