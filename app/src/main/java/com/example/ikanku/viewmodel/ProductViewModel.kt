package com.example.ikanku.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ikanku.R
import com.example.ikanku.model.Product

class ProductViewModel : ViewModel() {
    val products = listOf(
        Product(R.drawable.ikan_bluedevil, "Ikan Blue Devil", "Rp. 500.000", 4.5),
        Product(R.drawable.ikan_fire, "Ikan Fire", "Rp. 700.000", 4.0),
        Product(R.drawable.ikan_mandarin, "Ikan Mandarin", "Rp. 800.000", 4.0),
        Product(R.drawable.ikan_angel, "Ikan Angel", "Rp. 1.000.000", 4.5),
        Product(R.drawable.ikan_guppy, "Ikan Guppy", "Rp. 600.000", 4.0),
        Product(R.drawable.ikan_nila, "Ikan Nila", "Rp. 40.000", 3.5),
        Product(R.drawable.ikan_patin, "Ikan Patin", "Rp. 30.000", 4.0)
    )
}
