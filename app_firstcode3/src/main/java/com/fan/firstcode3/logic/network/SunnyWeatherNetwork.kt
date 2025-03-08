package com.fan.firstcode3.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create<PlaceService>()

    // {"status":"ok","query":"北京","places":[{"formatted_address":"中国 北京市 北京","id":"CY_CN_d9b61a","place_id":"CY_CN_d9b61a","name":"中国 北京市 北京","location":{"lat":39.904989,"lng":116.405285}},{"formatted_address":"中国 北京市 延庆","id":"CY_CN_d95423","place_id":"CY_CN_d95423","name":"中国 北京市 延庆","location":{"lat":40.465325,"lng":115.985006}}]}
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()


    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    // {"status":"ok","api_version":"v2.5","api_status":"stable","lang":"zh_CN","unit":"metric","tzshift":28800,"timezone":"Asia/Shanghai","server_time":1740662960,"location":[39.904989,116.405285],"result":{"daily":{"status":"ok","astro":[{"date":"2025-02-27T00:00+08:00","sunrise":{"time":"06:50"},"sunset":{"time":"18:03"}},{"date":"2025-02-28T00:00+08:00","sunrise":{"time":"06:48"},"sunset":{"time":"18:04"}},{"date":"2025-03-01T00:00+08:00","sunrise":{"time":"06:47"},"sunset":{"time":"18:05"}}],"precipitation":[{"date":"2025-02-27T00:00+08:00","max":0.0,"min":0.0,"avg":0.0},{"date":"2025-02-28T00:00+08:00","max":0.0,"min":0.0,"avg":0.0},{"date":"2025-03-01T00:00+08:00","max":0.0,"min":0.0,"avg":0.0}],"temperature":[{"date":"2025-02-27T00:00+08:00","max":12.43,"min":2.0,"avg":4.45},{"date":"2025-02-28T00:00+08:00","max":16.0,"min":2.0,"avg":9.25},{"date":"2025-03-01T00:00+08:00","max":12.0,"min":3.0,"avg":7.98}],"wind":[{"date":"2025-02-27T00:00+08:00","max":{"speed":6.33,"direction":3.09},"min":{"speed":1.42,"direction":317.64},"avg":{"speed":4.46,"direction":353.67}},{"date":"2025-02-28T00:00+08:00","max":{"speed":10.29,"direction":355.43},"min":{"speed":1.99,"direction":282.34},"avg":{"speed":6.1,"direction":9.86}},{"date":"2025-03-01T00:00+08:00","max":{"speed":6.22,"direction":57.12},"min":{"speed":1.19,"direction":6.72},"avg":{"speed":3.63,"direction":36.5}}],"humidity":[{"date":"2025-02-27T00:00+08:00","max":0.45,"min":0.14,"avg":0.31},{"date":"2025-02-28T00:00+08:00","max":0.28,"min":0.11,"avg":0.19},{"date":"2025-03-01T00:00+08:00","max":0.44,"min":0.2,"avg":0.33}],"cloudrate":[{"date":"2025-02-27T00:00+08:00","max":0.06,"min":0.0,"avg":0.0},{"date":"2025-02-28T00:00+08:00","max":1.0,"min":0.0,"avg":0.18},{"date":"2025-03-01T00:00+08:00","max":1.0,"min":0.0,"avg":0.63}],"pressure":[{"date":"2025-02-27T00:00+08:00","max":101292.67,"min":100728.81,"avg":100954.73},{"date":"2025-02-28T00:00+08:00","max":101357.53,"min":100989.48,"avg":101145.9},{"date":"2025-03-01T00:00+08:00","max":101413.83,"min":100870.47,"avg":101041.91}],"visibility":[{"date":"2025-02-27T00:00+08:00","max":33.69,"min":24.13,"avg":32.67},{"date":"2025-02-28T00:00+08:00","max":60.0,"min":32.55,"avg":51.94},{"date":"2025-03-01T00:00+08:00","max":43.71,"min":7.23,"avg":24.19}],"dswrf":[{"date":"2025-02-27T00:00+08:00","max":698.8,"min":0.0,"avg":0.0},{"date":"2025-02-28T00:00+08:00","max":721.8,"min":0.0,"avg":202.3},{"date":"2025-03-01T00:00+08:00","max":547.0,"min":0.0,"avg":131.1}],"air_quality":{"aqi":[{"date":"2025-02-27T00:00+08:00","max":{"chn":186,"usa":194},"avg":{"chn":184,"usa":193},"min":{"chn":65,"usa":124}},{"date":"2025-02-28T00:00+08:00","max":{"chn":182,"usa":193},"avg":{"chn":97,"usa":139},"min":{"chn":60,"usa":76}},{"date":"2025-03-01T00:00+08:00","max":{"chn":198,"usa":198},"avg":{"chn":134,"usa":174},"min":{"chn":104,"usa":162}}],"pm25":[{"date":"2025-02-27T00:00+08:00","max":140,"avg":138,"min":45},{"date":"2025-02-28T00:00+08:00","max":137,"avg":66,"min":24},{"date":"2025-03-01T00:00+08:00","max":148,"avg":101,"min":78}]},"skycon":[{"date":"2025-02-27T00:00+08:00","value":"MODERATE_HAZE"},{"date":"2025-02-28T00:00+08:00","value":"CLEAR_DAY"},{"date":"2025-03-01T00:00+08:00","value":"LIGHT_HAZE"}],"skycon_08h_20h":[{"date":"2025-02-27T00:00+08:00","value":"LIGHT_HAZE"},{"date":"2025-02-28T00:00+08:00","value":"CLEAR_DAY"},{"date":"2025-03-01T00:00+08:00","value":"LIGHT_HAZE"}],"skycon_20h_32h":[{"date":"2025-02-27T00:00+08:00","value":"MODERATE_HAZE"},{"date":"2025-02-28T00:00+08:00","value":"PARTLY_CLOUDY_NIGHT"},{"date":"2025-03-01T00:00+08:00","value":"HEAVY_HAZE"}],"life_index":{"ultraviolet":[{"date":"2025-02-27T00:00+08:00","index":"4","desc":"强"},{"date":"2025-02-28T00:00+08:00","index":"4","desc":"强"},{"date":"2025-03-01T00:00+08:00","index":"1","desc":"最弱"}],"carWashing":[{"date":"2025-02-27T00:00+08:00","index":"2","desc":"较适宜"},{"date":"2025-02-28T00:00+08:00","index":"2","desc":"较适宜"},{"date":"2025-03-01T00:00+08:00","index":"2","desc":"较适宜"}],"dressing":[{"date":"2025-02-27T00:00+08:00","index":"7","desc":"寒冷"},{"date":"2025-02-28T00:00+08:00","index":"6","desc":"冷"},{"date":"2025-03-01T00:00+08:00","index":"6","desc":"冷"}],"comfort":[{"date":"2025-02-27T00:00+08:00","index":"8","desc":"很冷"},{"date":"2025-02-28T00:00+08:00","index":"8","desc":"很冷"},{"date":"2025-03-01T00:00+08:00","index":"8","desc":"很冷"}],"coldRisk":[{"date":"2025-02-27T00:00+08:00","index":"4","desc":"极易发"},{"date":"2025-02-28T00:00+08:00","index":"4","desc":"极易发"},{"date":"2025-03-01T00:00+08:00","index":"4","desc":"极易发"}]}},"primary":0}}
    suspend fun getDailyWeather(lng: String, lat: String) = weatherService.getDailyWeather(lng, lat).await()

    // {"status":"ok","api_version":"v2.5","api_status":"stable","lang":"zh_CN","unit":"metric","tzshift":28800,"timezone":"Asia/Shanghai","server_time":1740662598,"location":[39.904989,116.405285],"result":{"realtime":{"status":"ok","temperature":5.96,"humidity":0.45,"cloudrate":0.0,"skycon":"MODERATE_HAZE","visibility":33.69,"dswrf":0.0,"wind":{"speed":3.72,"direction":350.97},"pressure":100903.74,"apparent_temperature":3.7,"precipitation":{"local":{"status":"ok","datasource":"radar","intensity":0.0},"nearest":{"status":"ok","distance":10000.0,"intensity":0.0}},"air_quality":{"pm25":118,"pm10":173,"o3":79,"so2":11,"no2":69,"co":1.1,"aqi":{"chn":155,"usa":182},"description":{"chn":"中度污染","usa":"中度污染"}},"life_index":{"ultraviolet":{"index":0.0,"desc":"无"},"comfort":{"index":12,"desc":"湿冷"}}},"primary":0}}
    suspend fun getRealtimeWeather(lng: String, lat: String) = weatherService.getRealtimeWeather(lng, lat).await()


    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}