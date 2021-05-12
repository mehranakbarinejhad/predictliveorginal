package ir.liyanamarket.predictlive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendRegisterinterface
import ir.liyanamarket.predictlive.dataclass.Register
import ir.liyanamarket.predictlive.presenter.register.PresenterApiConnectRegister
import ir.liyanamarket.predictlive.utils.MyMessage
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity(),SendRegisterinterface {
    private val presenterApiConnectRegister:PresenterApiConnectRegister by inject()
    private val myMessage: MyMessage by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myMessage.activity=this

        setContentView(R.layout.activity_register)
        val phonenumber=intent.getStringExtra("phonenumber").toString()
        edt_phonenumber_loginprofileactivity.setText(phonenumber)
        presenterApiConnectRegister.sendRegisterinterface=this


        btn_register_loginprofileactivity.setOnClickListener {

            register(
                edt_name_loginprofileactivity,
                edt_username_loginprofileactivity,
                edt_password_loginprofileactivity,
                edt_confirm_loginprofileactivity,
                edt_phonenumber_loginprofileactivity
            )
        }


        }

    override fun onsuccess(list: List<Register>) {
        when (list[0].result) {
            "success" -> {
                val intent= Intent(applicationContext, SuccessRegisterActivityActivity::class.java)
              startActivity(intent)
             finish()
            }
            "username" -> {
                myMessage.show("این نام کاربری آزاد نمی باشد!لطفا نام کاربری دیگری را وارد نمایید. ")
            }

        }
    }

    override fun onerror(t: Throwable) {
        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()

    }



    private fun register(
        name: EditText,
        username: EditText,
        password: EditText,
        confirm: EditText,
        phonenumber: EditText
    ) {
        if (name.text.isNotEmpty() && username.text.isNotEmpty() && password.text.isNotEmpty() && confirm.text.isNotEmpty()) {
            if (password.text.toString() == confirm.text.toString()) {
                presenterApiConnectRegister.register(name.text.toString(),username.text.toString(),password.text.toString(),phonenumber.text.toString())

            } else {


                myMessage.show("رمز عبور با تکرار آن یکسان نمی باشد.")

            }
        } else {

            myMessage.show("لطفا تمام ورودی ها را پر نمایید.")
        }
    }
}