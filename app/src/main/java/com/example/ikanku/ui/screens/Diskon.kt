@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ikanku.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.ui.components.CustomTopAppBar
import java.util.*

@Composable
fun DiscountScreen(
    navController: NavController,
    onBackClick: () -> Unit = {navController.popBackStack()},
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,

) {
    var startDate by remember { mutableStateOf("2024-11-01") }
    var endDate by remember { mutableStateOf("2024-11-30") }
    var discount by remember { mutableStateOf("50%") }
    var purchaseLimit by remember { mutableStateOf("100 Items") }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Diskon",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onCancelClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Batal", color = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = onSaveClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD))
                ) {
                    Text(text = "Simpan", color = Color.White)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Detail Diskon",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            DateInputField(
                label = "Waktu Mulai",
                selectedDate = startDate,
                onDateSelected = { startDate = it }
            )

            DateInputField(
                label = "Waktu Berakhir",
                selectedDate = endDate,
                onDateSelected = { endDate = it }
            )

            EditableField(
                label = "Diskon",
                value = discount,
                onValueChange = { discount = it }
            )

            EditableField(
                label = "Batas Pembelian",
                value = purchaseLimit,
                onValueChange = { purchaseLimit = it }
            )
        }
    }
}

@Composable
fun DateInputField(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            val formattedDate =
                                String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                            onDateSelected(formattedDate)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = selectedDate,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun EditableField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Gray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiscountScreen() {
    DiscountScreen(
        onBackClick = { /* Handle back action */ },
        onCancelClick = { /* Handle cancel action */ },
        onSaveClick = { /* Handle save action */ },
        navController = rememberNavController()
    )
}