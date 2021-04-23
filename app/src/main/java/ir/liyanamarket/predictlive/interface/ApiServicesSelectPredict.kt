package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Predict
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicesSelectPredict {
    @GET("Main.php")
    fun selectpredicuser(
        @Query("action")action:String,
        @Query("username")username:String,
        ): Call<List<Predict>>
}