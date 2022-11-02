package com.example.tipapp.uicomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    modifier: Modifier
) {
    val tipAmountState = remember { mutableStateOf(0.0) }
    val totalPerPersonState = remember { mutableStateOf(0.0) }
    val splitCounter = remember { mutableStateOf(1) }

    BillForm(
        splitCounter = splitCounter,
        totalPerPersonState = totalPerPersonState,
        tipAmountState = tipAmountState
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = { },
    splitCounter: MutableState<Int>,
    tipAmountState: MutableState<Double>,
    totalPerPersonState: MutableState<Double>
) {
    val totalBillState = remember { mutableStateOf("") }
    val validState = remember(totalBillState.value) { totalBillState.value.trim().isNotEmpty() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val sliderPosition = remember { mutableStateOf(0f) }

    val tipPercentage = (sliderPosition.value * 100).toInt()


    Surface(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(7.dp)), // ovalleştirme
        border = BorderStroke(
            1.dp, Color.LightGray //çevredeki çizgi
        )
    ) {
        Column(
            modifier = modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            BillCard(totalPersonAmount = totalPerPersonState.value)
            OutlinedTF(
                valueState = totalBillState,
                labelId = "Toplam Hesap",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (validState) { //alttaki comp'ları if içine alırsak sayı girmeden görünmeyecekler
                //ücret girince alt kısım açılacak if'i eğer yorumdan çıkarırsak sayı girmeden split kısmı açılmayacak
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text(text = "Split", modifier = Modifier.align(Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(163.dp))
                    Row(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        SplitButton(
                            imageVector = Icons.Default.Delete,
                            onClick = {
                                if (splitCounter.value > 1) {
                                    splitCounter.value--
                                    totalPerPersonState.value = calculateTotalPerPerson(
                                        totalBill = totalBillState.value.toDouble(),
                                        splitBy = splitCounter.value,
                                        tipPercentage = tipPercentage
                                    )
                                } else {
                                    splitCounter.value = 1
                                    totalPerPersonState.value = calculateTotalPerPerson(
                                        totalBill = totalBillState.value.toDouble(),
                                        splitBy = splitCounter.value,
                                        tipPercentage = tipPercentage
                                    )
                                }
                            },
                        )

                        Text(
                            text = "${splitCounter.value}",
                            Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 8.dp, end = 8.dp)
                        )

                        SplitButton(
                            imageVector = Icons.Default.Add,
                            onClick = {
                                splitCounter.value++
                                totalPerPersonState.value = calculateTotalPerPerson(
                                    totalBill = totalBillState.value.toDouble(),
                                    splitBy = splitCounter.value,
                                    tipPercentage = tipPercentage
                                )
                            },
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, top = 12.dp)
                ) {
                    Text(text = "Bahşiş", modifier = Modifier.align(Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(212.dp))
                    Text(
                        text = "% ${tipAmountState.value}",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "%${tipPercentage}",
                        modifier = Modifier.padding(top = 18.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Slider(value = sliderPosition.value,
                        onValueChange = {
                            sliderPosition.value = it
                            tipAmountState.value =
                                calculateTotalTip(totalBillState.value.toDouble(), tipPercentage)
                            totalPerPersonState.value =
                                calculateTotalPerPerson(
                                    totalBill = totalBillState.value.toDouble(),
                                    splitBy = splitCounter.value,
                                    tipPercentage = tipPercentage
                                )
                        },
                        steps = 9,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                        onValueChangeFinished = {

                        })
                }
            } else {
                Box {

                }
            }
        }
    }
}

private fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {
    return if (totalBill > 1 && totalBill.toString().isNotEmpty()) {
        (totalBill * tipPercentage) / 100
    } else 0.0
}

private fun calculateTotalPerPerson(
    totalBill: Double,
    splitBy: Int,
    tipPercentage: Int
): Double {
    val bill = calculateTotalTip(totalBill, tipPercentage) + totalBill
    return bill / splitBy
}


@Composable
fun SplitSide() {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(start = 12.dp)
    ) {
        Text(text = "Split", modifier = Modifier.align(Alignment.CenterVertically))
        Spacer(modifier = Modifier.width(163.dp))
        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            SplitButton(
                imageVector = Icons.Default.Delete,
                onClick = {},
            )

            Text(
                text = "12",
                Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp, end = 8.dp)
            )

            SplitButton(
                imageVector = Icons.Default.Add,
                onClick = { /*TODO*/ },
            )
        }
    }
}

@Composable
fun TipRow(split: Int = 1) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 12.dp)
    ) {
        Text(text = "Bahşiş", modifier = Modifier.align(Alignment.CenterVertically))
        Spacer(modifier = Modifier.width(195.dp))
        Text(text = "$split", modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@Preview
@Composable
fun MainContentPrev() {
    MainContent(modifier = Modifier)
}

