package ir.liyanamarket.predictlive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendValidatePhoneNumberinterface
import ir.liyanamarket.predictlive.dataclass.ValidatePhoneNumber
import ir.liyanamarket.predictlive.presenter.validatephonenumber.PresenterApiConnectValidatePhoneNumber
import ir.liyanamarket.predictlive.utils.CheckValidateInputPhoneNumber
import kotlinx.android.synthetic.main.activity_recovery.*
import org.koin.android.ext.android.inject

class RecoveryActivity : AppCompatActivity(), SendValidatePhoneNumberinterface {
    private val checkValidateInputPhoneNumber: CheckValidateInputPhoneNumber by inject()
    private val presenterApiConnectValidatePhoneNumber: PresenterApiConnectValidatePhoneNumber by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)
        presenterApiConnectValidatePhoneNumber.sendValidatePhoneNumberinterface=this
        btn_next_RecoveryActivity.setOnClickListener {
            if (checkValidateInputPhoneNumber.validatenumber(edt_phonenumber_recoveryactivity.text.toString())) {

                presenterApiConnectValidatePhoneNumber.getresult(edt_phonenumber_recoveryactivity.text.toString())
            }
        }

    }

    override fun onsuccess(list: List<ValidatePhoneNumber>) {
        if(!list[0].result){
            Toast.makeText(this,"این شماره تلفن ثبت نام نکرده است ", Toast.LENGTH_LONG).show()
        }
        else
        {
            val intent= Intent(applicationContext, ValidateCodeRecoveryActivity::class.java)
            intent.putExtra("phonenumber",edt_phonenumber_recoveryactivity.text.toString())
            startActivity(intent)
            finish()

        }

    }

    override fun onerror(t: Throwable) {

    }

    override fun onBackPressed() {
        finish()
    }
}