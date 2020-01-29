package com.example.queenapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.queenapi.adapter.QueenAdapter
import com.example.queenapi.api.QueenApiInterface
import com.example.queenapi.model.Queen
import com.example.queenapi.model.VoteResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() ,QueenAdapter.ClickListener{

    val BASE_URL = "https://ucsmonywaonlinevote.000webhostapp.com/api/"

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //voteKing()
        getQueen()

    }

    fun voteKing() {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitService = retrofit.create(QueenApiInterface::class.java)

        var apiCall = retrofitService.voteKing("JJoHHgA","K1")

        apiCall.enqueue(object : Callback<VoteResponse> {
            override fun onFailure(call: Call<VoteResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(),Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<VoteResponse>,
                response: Response<VoteResponse>
            ) {
                Toast.makeText(applicationContext, response.body().toString(),Toast.LENGTH_LONG).show()
            }

        })
    }

    fun loadQueenList(queenList: List<Queen>) {
        recyclerView = findViewById(R.id.recyclerQueen)
        recyclerQueen.apply {
            layoutManager = LinearLayoutManager(context)
            val queenAdapter = QueenAdapter(queenList,context,this@MainActivity)
            adapter = queenAdapter
        }
    }

    fun getQueen() {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitService = retrofit.create(QueenApiInterface::class.java)

        var apiCall = retrofitService.getQueens()

        apiCall.enqueue(object : Callback<List<Queen>> {
            override fun onFailure(call: Call<List<Queen>>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<List<Queen>>,
                response: Response<List<Queen>>
            ) {
                var queenList = response.body()!!

                loadQueenList(queenList)
            }
        }
        )
    }

    override fun onClick(queen: Queen) {
        Toast.makeText(this,"${queen.name}",Toast.LENGTH_LONG).show()
        val intent = Intent(this,DetailsActivity::class.java)
        intent.apply {
            putExtra("IMAGE",queen.imgUrl)
            putExtra("ID",queen.id)
        }
        startActivity(intent)
    }


}
