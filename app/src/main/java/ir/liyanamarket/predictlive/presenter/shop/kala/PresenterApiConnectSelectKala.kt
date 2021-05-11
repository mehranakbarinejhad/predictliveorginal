package ir.liyanamarket.predictlive.presenter.shop.kala

import ir.liyanamarket.predictlive.`interface`.SendSelectKalaInterface
import ir.liyanamarket.predictlive.dataclass.Kala
import ir.liyanamarket.predictlive.model.shop.kala.ApiConnectToSelectKala
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectSelectKala:KoinComponent,Callback<MutableList<Kala>> {
    private val apiConnectToSelectKala:ApiConnectToSelectKala by inject()
    lateinit var sendSelectKalaInterface: SendSelectKalaInterface
    fun selectkala(group:String){
        apiConnectToSelectKala.select().selectKala("selectkalawithgroup",group).enqueue(this)

    }

    override fun onResponse(call: Call<MutableList<Kala>>, response: Response<MutableList<Kala>>) {
        val data=response.body()
        if(data!=null)
        {
            sendSelectKalaInterface.onsuccess(data)
        }

    }

    override fun onFailure(call: Call<MutableList<Kala>>, t: Throwable) {
        sendSelectKalaInterface.onerror(t)
    }
}