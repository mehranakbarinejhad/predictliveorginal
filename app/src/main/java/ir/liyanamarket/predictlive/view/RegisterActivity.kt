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
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity(),SendRegisterinterface {
    private val presenterApiConnectRegister:PresenterApiConnectRegister by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
              finishAffinity()
            }
            "username" -> {
            Toast.makeText(this,"Username Is Validate! Please Another Username",Toast.LENGTH_LONG).show()
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
                Toast.makeText(this, "password and confirm no equals!", Toast.LENGTH_LONG)
                    .show()

            }
        } else {
            Toast.makeText(this, "inputtext is  empty! please check inputs", Toast.LENGTH_LONG)
                .show()
        }
    }
}