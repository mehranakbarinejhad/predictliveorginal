package ir.liyanamarket.predictlive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendUsersInterface
import ir.liyanamarket.predictlive.dataclass.Users
import ir.liyanamarket.predictlive.fragment.FragmentProgressBar
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectUser
import ir.liyanamarket.predictlive.utils.SaveLoginInfo

import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject


class LoginActivity : AppCompatActivity(), SendUsersInterface {
    private val fragmentProgress: FragmentProgressBar by inject()
    private val saveLoginInfo: SaveLoginInfo by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //region get info login
        val logininfo = saveLoginInfo.load()
        val usernameshared = logininfo.first
        val passwordshared = logininfo.second
        if (usernameshared != "" && passwordshared != "") {
            edt_username.setText(usernameshared)
            edt_password.setText(passwordshared)
        } else {
            swch_remmemberme.isChecked = false
        }
        //endregion

        //region Login button clicked
        btn_login.setOnClickListener {
            if (edt_username.text.toString().isEmpty() || edt_password.text.toString().isEmpty()) {
                Toast.makeText(this, "username or password is empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            fragmentProgress.show(supportFragmentManager, "progressbar")
            val presenterApiConnectUser: PresenterApiConnectUser by inject()
            presenterApiConnectUser.getusers(edt_username.text.toString())
            presenterApiConnectUser.sendUsersInterface = this

        }
        //endregion
        //region Text Recovery Click
        txt_revovery.setOnClickListener {
            val intent = Intent(applicationContext, RecoveryActivity::class.java)
            startActivity(intent)
        }
        //endregion
        //region text register click
            txt_createaccountclick.setOnClickListener {
                val intent=Intent(applicationContext,NumberActivity::class.java)
                startActivity(intent)
            }
        //endregion
    }


    // region callback On Success Data
    override fun onsuccess(list: List<Users>) {
        fragmentProgress.dismiss()
        if (list.count() != 0) {
            if (edt_password.text.toString() == list[0].password) {
                if (swch_remmemberme.isChecked) {
                    saveLoginInfo.save(edt_username.text.toString(), edt_password.text.toString())
                } else {
                    saveLoginInfo.save("", "")
                }
                val intentlogin = Intent(applicationContext, HomeActivity::class.java)
                putDataUserToIntent(list, intentlogin)
                startActivity(intentlogin)
                finishAffinity()

            } else {
                Toast.makeText(this, "password wrong", Toast.LENGTH_LONG).show()

            }
        } else {
            Toast.makeText(this, "نام کاربری اشتباه می باشد", Toast.LENGTH_LONG).show()

        }
    }
    //endregion


    //region Callback On error data
    override fun onerror(t: Throwable) {
        fragmentProgress.dismiss()
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()

    }
    //endregion


    //region Function Put user Data To Intent
    private fun putDataUserToIntent(list: List<Users>, intent: Intent) {

        intent.putExtra("usernameloginuser", edt_username.text.toString())
        intent.putExtra("nameuser", list[0].name)
        intent.putExtra("passwordloginuser", edt_password.text.toString())
        intent.putExtra("imageloginuser", list[0].image)
        intent.putExtra("phonenumberloginuser", list[0].phonenumber)
        intent.putExtra("scoreloginuser", list[0].Score)
    }
    //endregion
}