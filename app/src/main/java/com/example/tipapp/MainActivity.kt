package com.example.tipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipapp.ui.theme.TipAppTheme
import com.example.tipapp.uicomponents.BillCard
import com.example.tipapp.uicomponents.BillCardPreview
import com.example.tipapp.uicomponents.MainContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Column {

                    Spacer(modifier = Modifier.height(1.dp))
                    MainContent(modifier = Modifier)
                }
                
            }
        }
    }
}

@Composable
fun MyApp(
    content: @Composable () -> Unit
) {
    TipAppTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}