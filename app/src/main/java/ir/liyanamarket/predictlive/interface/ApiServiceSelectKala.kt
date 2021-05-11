package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Kala
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceSelectKala {
    @GET("Main.php")
    fun selectKala(
        @Query("action")action:String,
        @Query("groupkala")group:String

    ):Call<MutableList<Kala>>
}