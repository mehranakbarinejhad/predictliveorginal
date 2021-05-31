package ir.liyanamarket.predictlive.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendChangeProfileInterface
import ir.liyanamarket.predictlive.dataclass.ChangeProfile
import ir.liyanamarket.predictlive.presenter.changeprofile.PresenterApiconnectChangeProfile
import ir.liyanamarket.predictlive.utils.EncodeAndDecodeImage
import kotlinx.android.synthetic.main.settingfragment.*
import org.koin.android.ext.android.inject

class FragmentSetting:Fragment(),SendChangeProfileInterface {
    private val picasso:Picasso by inject()
    lateinit var activity: AppCompatActivity
    private val presenterApiconnectChangeProfile:PresenterApiconnectChangeProfile by inject()
    private val encodeAndDecodeImage:EncodeAndDecodeImage by inject()
    lateinit var strimage:String
    lateinit var username:String
    private lateinit var userimage:String
    lateinit var completename:String
    lateinit var phonenumber:String
    lateinit var password:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settingfragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        completename=activity.intent.getStringExtra("nameuser").toString()
        username=activity.intent.getStringExtra("usernameloginuser").toString()
        phonenumber=activity.intent.getStringExtra("phonenumberloginuser").toString()
        userimage=activity.intent.getStringExtra("imageloginuser").toString()
        password=activity.intent.getStringExtra("passwordloginuser").toString()
        txt_completename_settingactivity.setText(completename)
        txt_username_settingactivity.setText(username)
        txt_phonenumber_settingactivity.setText(phonenumber)
        txt_password_settingactivity.setText(password)
        picasso.load(userimage).fit().into(img_user_settingactivity)

        presenterApiconnectChangeProfile.sendChangeProfileInterface=this
        btn_changeprofile.setOnClickListener {
            val intent= Intent()
            intent.type="image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent,21)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==21 && resultCode==-1 && data!=null)
        {
            val path=data.data
             strimage=encodeAndDecodeImage.encodeimage(activity,path!!)
            presenterApiconnectChangeProfile.changeprofile(strimage,username)
        }
    }

    override fun onsuccess(list: List<ChangeProfile>) {



    }

    override fun onerror(t: Throwable) {
        Toast.makeText(activity,"Error",Toast.LENGTH_LONG).show()
    }


}
