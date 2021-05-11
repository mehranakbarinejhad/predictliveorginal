package ir.liyanamarket.predictlive.utils
import androidx.appcompat.app.AppCompatActivity
import ir.liyanamarket.predictlive.fragment.FragmentMessage

import org.koin.core.KoinComponent
import org.koin.core.inject


class MyMessage:KoinComponent {
    lateinit var  activity: AppCompatActivity
    private val fragmentmessage: FragmentMessage by inject()
    fun show(textmessage:String)
    {
        fragmentmessage.message=textmessage
        fragmentmessage.show(activity.supportFragmentManager,"message")
    }


}