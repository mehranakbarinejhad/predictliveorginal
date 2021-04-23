package ir.liyanamarket.predictlive.`interface`

import ir.liyanamarket.predictlive.dataclass.Register
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceRegister {
    @GET("Main.php")
    fun register(
        @Query("action") action: String,
        @Query("name") Name: String,
        @Query("username") Username: String,
        @Query("password") Password: String,
        @Query("phonenumber") Phonenumber: String

    ): Call<List<Register>>
}