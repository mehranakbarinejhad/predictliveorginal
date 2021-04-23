package ir.liyanamarket.predictlive

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import ir.liyanamarket.predictlive.`interface`.SendSmsCodeinterface
import ir.liyanamarket.predictlive.dataclass.SmsCode
import ir.liyanamarket.predictlive.presenter.sendcode.PresenterApiConnectSendCode
import kotlinx.android.synthetic.main.activity_validate_code.*
import org.koin.android.ext.android.inject

class ValidateCodeActivity : AppCompatActivity(),SendSmsCodeinterface {
    private val presenterApiConnectSendCode:PresenterApiConnectSendCode by inject()
    lateinit var smscode:String
    lateinit var phonenumber:String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate_code)

        phonenumber=intent.getStringExtra("phonenumber").toString()
        txt_entercodetext_validateactivity.text=" ما یک کد فعال سازی به شماره  $phonenumber ,ارسال کردیم!لطفا آن را وارد کنید . "
        presenterApiConnectSendCode.sendSmsCodeinterface=this
        presenterApiConnectSendCode.getcode(phonenumber)
        btn_next_validateactivity.setOnClickListener {
            val enteringtext=textCode(edt_numberone_validateactivity,edt_numbertwo_validateactivity,edt_numberthree_validateactivity,edt_numberfor_validateactivity,edt_numberfive_validateactivity)
            if(enteringtext==smscode) {
                val intent= Intent(this,RegisterActivity::class.java)
                intent.putExtra("phonenumber",phonenumber)
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(this,"wrong code", Toast.LENGTH_LONG).show()
            }
        }


        edt_numberone_validateactivity.addTextChangedListener { setfocus(edt_numberone_validateactivity,edt_numbertwo_validateactivity) }
        edt_numbertwo_validateactivity.addTextChangedListener { setfocus(edt_numbertwo_validateactivity,edt_numberthree_validateactivity) }
        edt_numberthree_validateactivity.addTextChangedListener { setfocus(edt_numberthree_validateactivity,edt_numberfor_validateactivity) }
        edt_numberfor_validateactivity.addTextChangedListener { setfocus(edt_numberfor_validateactivity,edt_numberfive_validateactivity) }



    }

    override fun onsuccess(list: List<SmsCode>) {
      smscode = list[0].code
        phonenumber = list[0].phonenumber

    }

    override fun onerror(t: Throwable) {
    }


    private fun textCode(num1: EditText, num2: EditText, num3: EditText, num4: EditText, num5: EditText):String{
        return num1.text.toString()+num2.text.toString()+num3.text.toString()+num4.text.toString()+num5.text.toString()

    }
    private fun setfocus(firstedttext: EditText, lastedttext: EditText) {
        if (firstedttext.text.toString().length == 1) {
            lastedttext.requestFocus()

        }
    }
}