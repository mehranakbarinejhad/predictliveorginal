package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicesUsersByPhoneNumber {
    @GET("Main.php")
    fun getusersbynumber(
        @Query("action")action:String,
        @Query("phonenumber")username:String
    ): Call<List<Users>>
}