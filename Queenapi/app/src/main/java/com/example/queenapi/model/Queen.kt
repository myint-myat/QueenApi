package com.example.queenapi.model


import com.google.gson.annotations.SerializedName


data class Queen(

	@field:SerializedName("img_url")
	val imgUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("vote_time_status")
	val voteTimeStatus: Int? = null,

	@field:SerializedName("class")
	val jsonMemberClass: String? = null,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null
)