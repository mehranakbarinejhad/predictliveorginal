package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Users

import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiservicesUsers {
        @GET("Main.php")
        fun getusers(
            @Query("action")action:String,
            @Query("username")username:String
        ):Call<List<Users>>


}