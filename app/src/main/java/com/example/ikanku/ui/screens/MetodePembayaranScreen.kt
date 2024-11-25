import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.example.ikanku.ui.components.CustomTopAppBar

@Composable
fun MetodePembayaranScreen(
    onBackClick: () -> Unit
) {
    var paymentMethods by remember { mutableStateOf(mutableListOf("Bayar Ditempat", "Bank Transfer")) }
    var newPaymentMethod by remember { mutableStateOf(TextFieldValue("")) }
    var selectedMethodIndex by remember { mutableStateOf(-1) } // Store the selected method index for editing

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Metode Pembayaran",
                onBackClick = onBackClick
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply content padding
                    .padding(16.dp) // Additional padding
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Display the current payment methods
                paymentMethods.forEachIndexed { index, method ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = method,
                            fontSize = 18.sp,
                            modifier = Modifier.weight(1f)
                        )
                        // Edit button for each method
                        Button(
                            onClick = {
                                selectedMethodIndex = index
                                newPaymentMethod = TextFieldValue(method) // Pre-fill the input field
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF177BCD)) // Match top bar color
                        ) {
                            Text(text = "Edit", color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                paymentMethods.removeAt(index) // Delete method immediately
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF4238)) // Red color for delete button
                        ) {
                            Text(text = "Hapus", color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Input field to add or edit payment methods
                TextField(
                    value = newPaymentMethod,
                    onValueChange = { newPaymentMethod = it },
                    label = { Text("Tambah/Edit Metode Pembayaran") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (newPaymentMethod.text.isNotBlank()) {
                                if (selectedMethodIndex == -1) {
                                    // Add new payment method
                                    paymentMethods.add(newPaymentMethod.text)
                                } else {
                                    // Edit the selected method
                                    paymentMethods[selectedMethodIndex] = newPaymentMethod.text
                                    selectedMethodIndex = -1 // Reset selection
                                }
                                newPaymentMethod = TextFieldValue("") // Clear input field
                            }
                        }
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFE1F3FF), // Light blue
                        focusedIndicatorColor = Color(0xFF177BCD), // Match theme
                        unfocusedIndicatorColor = Color(0xFF177BCD)
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Button to add or edit payment method
                Button(
                    onClick = {
                        if (newPaymentMethod.text.isNotBlank()) {
                            if (selectedMethodIndex == -1) {
                                // Add new payment method
                                paymentMethods.add(newPaymentMethod.text)
                            } else {
                                // Edit the selected method
                                paymentMethods[selectedMethodIndex] = newPaymentMethod.text
                                selectedMethodIndex = -1 // Reset selection
                            }
                            newPaymentMethod = TextFieldValue("") // Clear input field
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF177BCD)) // Match top bar color
                ) {
                    Text(
                        text = if (selectedMethodIndex == -1) "Tambah Metode Pembayaran" else "Simpan Perubahan",
                        color = Color.White
                    )
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewMetodePembayaranScreen() {
    MetodePembayaranScreen(
        onBackClick = { /* Handle back action */ }
    )
}
