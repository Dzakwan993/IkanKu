import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import com.example.ikanku.ui.components.CustomTopAppBar

data class BankAccount(
    var bankName: String,
    var accountNumber: String
)

@Composable
fun BankManagementScreen(
    onBackClick: () -> Unit
) {
    var bankList by remember {
        mutableStateOf(
            mutableListOf(
                BankAccount("Bank BCA", "123456789"),
                BankAccount("Bank Mandiri", "987654321"),
                BankAccount("Bank BRI", "456123789")
            )
        )
    }
    var newBankName by remember { mutableStateOf(TextFieldValue("")) }
    var newAccountNumber by remember { mutableStateOf(TextFieldValue("")) }
    var selectedBankIndex by remember { mutableStateOf(-1) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Pengaturan Bank",
                onBackClick = onBackClick
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Display the list of banks with account numbers
                bankList.forEachIndexed { index, bankAccount ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Bank: ${bankAccount.bankName}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Rekening: ${bankAccount.accountNumber}",
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                        Button(
                            onClick = {
                                selectedBankIndex = index
                                newBankName = TextFieldValue(bankAccount.bankName)
                                newAccountNumber = TextFieldValue(bankAccount.accountNumber)
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF177BCD))
                        ) {
                            Text(text = "Edit", color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                bankList.removeAt(index) // Hapus bank berdasarkan indeks
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF4238))
                        ) {
                            Text(text = "Hapus", color = Color.White)
                        }

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Input fields for bank name and account number
                TextField(
                    value = newBankName,
                    onValueChange = { newBankName = it },
                    label = { Text("Nama Bank") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFE1F3FF),
                        focusedIndicatorColor = Color(0xFF177BCD),
                        unfocusedIndicatorColor = Color(0xFF177BCD)
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = newAccountNumber,
                    onValueChange = { newAccountNumber = it },
                    label = { Text("Nomor Rekening") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (newBankName.text.isNotBlank() && newAccountNumber.text.isNotBlank()) {
                                if (selectedBankIndex == -1) {
                                    // Add new bank account
                                    bankList.add(
                                        BankAccount(newBankName.text, newAccountNumber.text)
                                    )
                                } else {
                                    // Edit existing bank account
                                    bankList[selectedBankIndex] =
                                        BankAccount(newBankName.text, newAccountNumber.text)
                                    selectedBankIndex = -1 // Reset index
                                }
                                newBankName = TextFieldValue("") // Clear input
                                newAccountNumber = TextFieldValue("") // Clear input
                            }
                        }
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFE1F3FF),
                        focusedIndicatorColor = Color(0xFF177BCD),
                        unfocusedIndicatorColor = Color(0xFF177BCD)
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Button to save new or edited bank account
                Button(
                    onClick = {
                        if (newBankName.text.isNotBlank() && newAccountNumber.text.isNotBlank()) {
                            if (selectedBankIndex == -1) {
                                // Add new bank account
                                bankList.add(
                                    BankAccount(newBankName.text, newAccountNumber.text)
                                )
                            } else {
                                // Edit existing bank account
                                bankList[selectedBankIndex] =
                                    BankAccount(newBankName.text, newAccountNumber.text)
                                selectedBankIndex = -1 // Reset index
                            }
                            newBankName = TextFieldValue("") // Clear input
                            newAccountNumber = TextFieldValue("") // Clear input
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF177BCD))
                ) {
                    Text(
                        text = if (selectedBankIndex == -1) "Tambah Bank" else "Simpan Perubahan",
                        color = Color.White
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBankManagementScreen() {
    BankManagementScreen(
        onBackClick = { /* Handle back action */ }
    )
}
