import android.util.Log
import com.example.ikanku.network.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.firebase.auth.FirebaseAuth

object RetrofitInstance {
    private val authInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()

        // Ambil token Firebase
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val token = firebaseUser?.getIdToken(false)?.result?.token

        // Tambahkan log untuk memeriksa token
        if (token == null) {
            Log.e("RetrofitInstance", "Token Firebase tidak ditemukan")
        } else {
            Log.d("RetrofitInstance", "Token Firebase: $token")
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        chain.proceed(requestBuilder.build())
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.4:5000/") // Pastikan URL server Anda benar
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}
