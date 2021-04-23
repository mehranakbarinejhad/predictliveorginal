package ir.liyanamarket.predictlive.utils

import android.content.Context
import android.widget.Toast

class CheckValidateInputPhoneNumber(private val context: Context) {
    fun validatenumber(phonenumber:String):Boolean {
        var bool=true
        when {
            phonenumber.isEmpty() -> {
              //  MessageFragment("Warning","Please Enter PhoneNumber", R.drawable.ic_warning).show(activity.supportFragmentManager,"Message")
                Toast.makeText(context,"Please Enter PhoneNumber",Toast.LENGTH_LONG).show()
                bool=false

            }
            phonenumber.startsWith('0') -> {
             //   MessageFragment("Warning","Please Enter PhoneNumber without 0 number", R.drawable.ic_warning).show(activity.supportFragmentManager,"Message")
                Toast.makeText(context,"Please Enter PhoneNumber without 0 number",Toast.LENGTH_LONG).show()

                bool=false
            }
            phonenumber.length!=10 -> {
              //  MessageFragment("Warning","Phone Number Should Lenght 10", R.drawable.ic_warning).show(activity.supportFragmentManager,"Message")
                Toast.makeText(context,"Phone Number Should Lenght 10",Toast.LENGTH_LONG).show()

                bool=false
            }

        }
        return bool

    }
}