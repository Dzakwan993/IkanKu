package com.example.ikanku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ikanku.R
import com.example.ikanku.ui.components.BottomNavBar
import com.example.ikanku.ui.components.CustomTopAppBar
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataProfileScreen(navController: NavController) {
    var showGenderBottomSheet by remember { mutableStateOf(false) }
    var showNameBottomSheet by remember { mutableStateOf(false) }
    var showDateOfBirthBottomSheet by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("Laki-Laki") }
    var fullName by remember { mutableStateOf("Miftahul Fazra") }
    var dateOfBirth by remember { mutableStateOf("Atur Sekarang") }

    Scaffold(
        topBar = { CustomTopAppBar(title = "Profil", onBackClick = { navController.popBackStack() }) },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            ProfileImage()
            Spacer(modifier = Modifier.height(16.dp))
            ProfileInfoItems(
                fullName = fullName,
                selectedGender = selectedGender,
                dateOfBirth = dateOfBirth,
                onNameClick = { showNameBottomSheet = true },
                onGenderClick = { showGenderBottomSheet = true },
                onDateOfBirthClick = { showDateOfBirthBottomSheet = true },
                navController = navController
            )
        }
    }

    if (showGenderBottomSheet) GenderSelectionBottomSheet(selectedGender, onGenderSelected = { gender ->
        selectedGender = gender
        showGenderBottomSheet = false
    })

    if (showNameBottomSheet) NameEditBottomSheet(currentName = fullName, onNameChanged = { newName ->
        fullName = newName
        showNameBottomSheet = false
    })

    if (showDateOfBirthBottomSheet) DateOfBirthBottomSheet(currentDate = dateOfBirth, onDateSelected = { selectedDate ->
        dateOfBirth = selectedDate
        showDateOfBirthBottomSheet = false
    })
}

@Composable
fun ProfileImage() {
    Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.size(100.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profil_kucing),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray, shape = CircleShape)
                .clip(CircleShape)
        )
        IconButton(
            onClick = { /* Handle edit picture */ },
            modifier = Modifier
                .background(Color.White, shape = CircleShape)
                .size(24.dp)
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Profile Picture", tint = Color(0xFF177BCD))
        }
    }
}

@Composable
fun ProfileInfoItems(
    fullName: String,
    selectedGender: String,
    dateOfBirth: String,
    onNameClick: () -> Unit,
    onGenderClick: () -> Unit,
    onDateOfBirthClick: () -> Unit,
    navController: NavController // Menambahkan parameter navController
) {
    ProfileInfoItem(title = "Nama Lengkap", value = fullName, showDropdown = true, onClick = onNameClick)
    ProfileInfoItem(title = "Jenis Kelamin", value = selectedGender, showDropdown = true, onClick = onGenderClick)
    ProfileInfoItem(title = "Tanggal Lahir", value = dateOfBirth, showDropdown = true, onClick = onDateOfBirthClick)

    // Menambahkan ProfileInfoItem untuk nomor ponsel dengan navigasi
    ProfileInfoItem(
        title = "Nomor Ponsel", value = "6282387436427", showDropdown = true,
        iconContent = {
            Icon(
                painter = painterResource(id = R.drawable.lihat_detail),
                contentDescription = "Lihat Detail",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        },
        onClick = { navController.navigate("ubah_nomor_ponsel") } // Navigasi ke UbahNomorPonselScreen
    )
}



@Composable
fun ProfileInfoItem(
    title: String,
    value: String,
    showDropdown: Boolean = false,
    iconContent: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { if (showDropdown) onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontSize = 14.sp, color = Color.Black)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                iconContent?.invoke()
                if (showDropdown) Icon(painter = painterResource(id = R.drawable.icon_kebawah), contentDescription = "Dropdown Icon", tint = Color.Black, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderSelectionBottomSheet(selectedGender: String, onGenderSelected: (String) -> Unit) {
    ModalBottomSheet(onDismissRequest = { onGenderSelected(selectedGender) }, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) {
        GenderSelectionBottomSheetContent(selectedGender, onGenderSelected)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameEditBottomSheet(currentName: String, onNameChanged: (String) -> Unit) {
    ModalBottomSheet(onDismissRequest = { onNameChanged(currentName) }, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) {
        NameEditBottomSheetContent(currentName, onNameChanged)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateOfBirthBottomSheet(currentDate: String, onDateSelected: (String) -> Unit) {
    ModalBottomSheet(onDismissRequest = { onDateSelected(currentDate) }, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) {
        DateOfBirthBottomSheetContent(currentDate, onDateSelected, onCancel = { onDateSelected(currentDate) })
    }
}

@Composable
fun GenderSelectionBottomSheetContent(selectedGender: String, onGenderSelected: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        HeaderRow("Jenis Kelamin") { onGenderSelected(selectedGender) }
        Spacer(modifier = Modifier.height(16.dp))
        GenderOptionItem("Laki-Laki", selected = selectedGender == "Laki-Laki", onSelect = onGenderSelected)
        GenderOptionItem("Perempuan", selected = selectedGender == "Perempuan", onSelect = onGenderSelected)
    }
}

@Composable
fun HeaderRow(title: String, onClose: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = title, fontSize = 18.sp, modifier = Modifier.weight(1f))
        IconButton(onClick = onClose, modifier = Modifier.size(24.dp)) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.Gray)
        }
    }
}

@Composable
fun GenderOptionItem(label: String, selected: Boolean, onSelect: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onSelect(label) },
        colors = CardDefaults.cardColors(containerColor = if (selected) Color(0xFFE0E0E0) else Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontSize = 16.sp, color = Color.Black)
            RadioButton(selected = selected, onClick = { onSelect(label) }, colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF177BCD)))
        }
    }
}

@Composable
fun NameEditBottomSheetContent(currentName: String, onNameChanged: (String) -> Unit) {
    var name by remember { mutableStateOf(currentName) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        HeaderRow(title = "Ganti Nama Lengkap") { onNameChanged(currentName) }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Masukkan nama") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNameChanged(name) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Simpan", color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateOfBirthBottomSheetContent(
    currentDate: String,
    onDateSelected: (String) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf(currentDate) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        HeaderRow(title = "Tanggal Lahir") { onCancel() }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk membuka DatePickerDialog
        Button(
            onClick = {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                // Membuka DatePickerDialog
                android.app.DatePickerDialog(
                    context,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        // Format tanggal menjadi "YYYY-MM-DD"
                        selectedDate = "$selectedYear-${
                            (selectedMonth + 1).toString().padStart(2, '0')
                        }-${selectedDay.toString().padStart(2, '0')}"
                        onDateSelected(selectedDate) // Update tanggal yang dipilih
                    },
                    year,
                    month,
                    day
                ).show()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Pilih Tanggal", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan tanggal yang dipilih
        Text(
            text = "Tanggal yang dipilih: $selectedDate",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onDateSelected(selectedDate) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF177BCD)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Simpan", color = Color.White)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedItem,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFE0E0E0),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        )
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = { Text(item) },
                onClick = {
                    onItemSelected(item)
                    expanded = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DataProfileScreenPreview() {
    val navController = rememberNavController()
    DataProfileScreen(navController = navController)
}
