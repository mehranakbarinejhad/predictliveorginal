package ir.liyanamarket.predictlive.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendChangeProfileInterface
import ir.liyanamarket.predictlive.dataclass.ChangeProfile
import ir.liyanamarket.predictlive.presenter.changeprofile.PresenterApiconnectChangeProfile
import ir.liyanamarket.predictlive.utils.EncodeAndDecodeImage
import kotlinx.android.synthetic.main.settingfragment.*
import org.koin.android.ext.android.inject

class FragmentSetting:Fragment(),SendChangeProfileInterface {
    lateinit var activity: AppCompatActivity
    private val presenterApiconnectChangeProfile:PresenterApiconnectChangeProfile by inject()
    private val encodeAndDecodeImage:EncodeAndDecodeImage by inject()
    lateinit var strimage:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settingfragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        val username=activity.intent.getStringExtra("usernameloginuser").toString()
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
