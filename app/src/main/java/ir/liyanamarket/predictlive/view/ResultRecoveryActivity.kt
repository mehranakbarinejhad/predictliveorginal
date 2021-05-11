package ir.liyanamarket.predictlive.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendUsersInterface
import ir.liyanamarket.predictlive.dataclass.Users
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectUser
import kotlinx.android.synthetic.main.activity_result_recovery.*
import org.koin.android.ext.android.inject

class ResultRecoveryActivity : AppCompatActivity(),SendUsersInterface {
    private val presenterApiConnectUser:PresenterApiConnectUser by inject()
    private val picasso:Picasso by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_recovery)
        presenterApiConnectUser.sendUsersInterface=this
        val number=intent.getStringExtra("phonenumber").toString()
        presenterApiConnectUser.getusersbynumber(number)

        btn_login_resultrecoveryactivity.setOnClickListener {
            finish()
        }
    }

    override fun onsuccess(list: List<Users>) {
        username_resultrecoveryactivity.text=list[0].username
        password_resultrecoveryactivity.text=list[0].password
        picasso.load(list[0].image).fit().into(img_userprofile_resultrecoveryactivity)
    }

    override fun onerror(t: Throwable) {
        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()

    }

    override fun onBackPressed() {
        finish()
    }
}