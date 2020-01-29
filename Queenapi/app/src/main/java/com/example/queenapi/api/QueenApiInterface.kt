package com.example.queenapi.api

import com.example.queenapi.model.Queen
import com.example.queenapi.model.VoteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QueenApiInterface{
    @GET("queen")
    fun getQueens(): Call<List<Queen>>

    @POST("kingvote")
    fun voteKing (
        @Query("code") code: String,
        @Query("king_id") king_id: String
    ): Call<VoteResponse>

    @POST("queenvote")
    fun voteQueen (
        @Query("code") code: String,
        @Query("queen_id") queen_id: String
    ): Call<VoteResponse>
}