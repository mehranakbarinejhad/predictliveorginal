package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Match
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicesSelectMatch {
    @GET("Main.php")
    fun selectmatch(
        @Query("action")action:String,
        @Query("matchdate")matchdate:String

    ): Call<List<Match>>
}