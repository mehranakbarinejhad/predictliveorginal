package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.ResultPredict
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInsertPredict {
    @GET("Main.php")
    fun insertpredict(
        @Query("action")action:String,
        @Query("username")username:String,
        @Query("matchid")matchid:Int,
        @Query("hometeamgols")hometeamgols:Int,
        @Query("guestteamgols")guestteamgols:Int

    ): Call<List<ResultPredict>>

}