package com.example.retrofitresimcekme

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView'u buluyoruz
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrofit kurulumu
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        // Veri çekimi ve RecyclerView'a aktarımı
        service.getData().enqueue(object : Callback<List<Images>> {
            override fun onFailure(call: Call<List<Images>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Bir hata oluştu", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Images>>, response: Response<List<Images>>) {
                if (response.isSuccessful) {
                    response.body()?.let { users ->
                        // Adapter ile veriyi RecyclerView'a bağlıyoruz
                        val adapter = CustomAdapter(users)
                        recyclerView.adapter = adapter
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Hata oluştu", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
