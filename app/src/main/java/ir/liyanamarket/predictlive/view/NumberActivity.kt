package ir.liyanamarket.predictlive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.ValidateCodeActivity
import ir.liyanamarket.predictlive.`interface`.SendValidatePhoneNumberinterface
import ir.liyanamarket.predictlive.dataclass.ValidatePhoneNumber
import ir.liyanamarket.predictlive.presenter.validatephonenumber.PresenterApiConnectValidatePhoneNumber
import ir.liyanamarket.predictlive.utils.CheckValidateInputPhoneNumber
import kotlinx.android.synthetic.main.activity_number.*
import org.koin.android.ext.android.inject

class NumberActivity : AppCompatActivity(),SendValidatePhoneNumberinterface {
    private val checkValidateInputPhoneNumber: CheckValidateInputPhoneNumber by inject()
    private val presenterApiConnectValidatePhoneNumber:PresenterApiConnectValidatePhoneNumber by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)
        presenterApiConnectValidatePhoneNumber.sendValidatePhoneNumberinterface=this
        btn_next_numberactivity.setOnClickListener {
            if (checkValidateInputPhoneNumber.validatenumber(edt_phonenumber_numberactivity.text.toString())) {

                presenterApiConnectValidatePhoneNumber.getresult(edt_phonenumber_numberactivity.text.toString())
            }


        }
    }

    override fun onsuccess(list: List<ValidatePhoneNumber>) {

        if(list[0].result){
            Toast.makeText(this,"این شماره تلفن قبلا ثبت نام کرده است ",Toast.LENGTH_LONG).show()
        }
        else
        {
          val intent=Intent(applicationContext,ValidateCodeActivity::class.java)
            intent.putExtra("phonenumber",edt_phonenumber_numberactivity.text.toString())
            startActivity(intent)
            finish()

        }
    }

    override fun onerror(t: Throwable) {
        Toast.makeText(this,"اتصال اینترنت را بررسی کنید  ",Toast.LENGTH_LONG).show()

    }

    override fun onBackPressed() {
        finish()
    }
}