package com.example.queenapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.queenapi.api.QueenApiInterface
import com.example.queenapi.model.VoteResponse
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {

    val BASE_URL = "https://ucsmonywaonlinevote.000webhostapp.com/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val queenImg=intent.getStringExtra("IMAGE")
        val queenID=intent.getStringExtra("ID")

        Glide.with(applicationContext)
            .load(queenImg)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageQueen)

        btnSubmit.setOnClickListener{
            var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var retrofitService = retrofit.create(QueenApiInterface::class.java)

            var apiCall = retrofitService.voteKing(txtCode.text.toString(),queenID)

            apiCall.enqueue(object : Callback<VoteResponse> {
                override fun onFailure(call: Call<VoteResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<VoteResponse>,
                    response: Response<VoteResponse>
                ) {
                    Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}
