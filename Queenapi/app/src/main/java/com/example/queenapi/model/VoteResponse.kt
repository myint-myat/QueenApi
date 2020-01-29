package com.example.queenapi.model


import com.google.gson.annotations.SerializedName


data class VoteResponse(

	@field:SerializedName("message")
	val message: String? = null
)