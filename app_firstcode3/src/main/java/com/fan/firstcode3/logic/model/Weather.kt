package com.fan.firstcode3.logic.model
// 实时天气和未来几天的天气
data class Weather(val realtime: RealtimeResponse.RealTime, val daily: DailyResponse.Daily)