package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.SmsCode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicesendCode {
    @GET("Main.php")
    fun sendcode(
        @Query("action") action: String,
        @Query("phonenumber") phonenumber: String
    ): Call<List<SmsCode>>
}