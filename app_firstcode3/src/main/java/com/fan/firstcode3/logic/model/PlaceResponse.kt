package com.fan.firstcode3.logic.model

import com.google.gson.annotations.SerializedName
// 根据关键字搜索到的多个地址
data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(val name: String, val location: Location,
                 @SerializedName("formatted_address") val address: String)
data class Location(val lng: String, val lat: String)