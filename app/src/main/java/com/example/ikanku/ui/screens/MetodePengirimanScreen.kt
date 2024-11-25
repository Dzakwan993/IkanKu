import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import com.example.ikanku.ui.components.CustomTopAppBar

@Composable
fun MetodePengirimanScreen(
    onBackClick: () -> Unit
) {
    var shippingMethods by remember { mutableStateOf(mutableListOf("Dikirim", "Diambil")) }
    var newShippingMethod by remember { mutableStateOf(TextFieldValue("")) }
    var selectedMethodIndex by remember { mutableStateOf(-1) } // Store the selected method index for editing

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Metode Pengiriman",
                onBackClick = onBackClick
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply contentPadding here
                    .padding(16.dp) // Additional padding for the content
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Display the current shipping methods
                shippingMethods.forEachIndexed { index, method ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = method, fontSize = 18.sp, modifier = Modifier.weight(1f))
                        // Edit button for each method
                        Button(
                            onClick = {
                                selectedMethodIndex = index
                                newShippingMethod = TextFieldValue(method) // Pre-fill the input field with the selected method
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF177BCD)) // Top bar color for the button
                        ) {
                            Text(text = "Edit", color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                if (shippingMethods.size > index) {
                                    shippingMethods.removeAt(index)
                                    // Reset the input and index after deletion
                                    newShippingMethod = TextFieldValue("")
                                    selectedMethodIndex = -1
                                }
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF4238))
                        ) {
                            Text(text = "Hapus", color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Input field to add or edit shipping method
                TextField(
                    value = newShippingMethod,
                    onValueChange = { newShippingMethod = it },
                    label = { Text("Tambah/Edit Metode Pengiriman") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (newShippingMethod.text.isNotBlank()) {
                                if (selectedMethodIndex == -1) {
                                    // Add new shipping method
                                    shippingMethods.add(newShippingMethod.text)
                                } else {
                                    // Edit the selected method
                                    shippingMethods[selectedMethodIndex] = newShippingMethod.text
                                    selectedMethodIndex = -1 // Reset selection after editing
                                }
                                newShippingMethod = TextFieldValue("") // Clear input field
                            }
                        }
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFE1F3FF), // Light blue color similar to topbar background
                        focusedIndicatorColor = Color(0xFF177BCD), // Color for focused border
                        unfocusedIndicatorColor = Color(0xFF177BCD) // Color for unfocused border
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Button to add or edit the shipping method
                Button(
                    onClick = {
                        if (newShippingMethod.text.isNotBlank()) {
                            if (selectedMethodIndex == -1) {
                                // Add new shipping method
                                shippingMethods.add(newShippingMethod.text)
                            } else {
                                // Edit the selected method
                                shippingMethods[selectedMethodIndex] = newShippingMethod.text
                                selectedMethodIndex = -1 // Reset selection after editing
                            }
                            newShippingMethod = TextFieldValue("") // Clear input field
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF177BCD)) // Top bar color for the button
                ) {
                    Text(text = if (selectedMethodIndex == -1) "Tambah Metode Pengiriman" else "Simpan Perubahan", color = Color.White)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMetodePengirimanScreen() {
    MetodePengirimanScreen(
        onBackClick = { /* Handle back action */ }
    )
}
