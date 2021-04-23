package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ValidatePhoneNumber
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceValidatePhoneNumber
{
    @GET("Main.php")
    fun resultnumber(
        @Query("action") action: String,
        @Query("phonenumber") phonenumber: String
    ): Call<List<ValidatePhoneNumber>>
}