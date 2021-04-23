package ir.liyanamarket.predictlive.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R


import kotlinx.android.synthetic.main.homefragment.*
import org.koin.android.ext.android.get

import java.lang.Exception

class FragmentHome():Fragment() {
    lateinit var activity2: AppCompatActivity

    private val picasso=get<Picasso>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.homefragment,container,false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        try {
            name_userprofile.text = activity2.intent.getStringExtra("nameuser").toString()
            score_text.text = activity2.intent.getIntExtra("scoreloginuser", -1).toString()
           username.text= activity2.intent.getStringExtra("usernameloginuser").toString()
            phonenumber_user.text = activity2.intent.getStringExtra("phonenumberloginuser").toString()

            picasso.load(activity2.intent.getStringExtra("imageloginuser")).fit().centerCrop()
                .into(img_userprofile)

        }
        catch (ex:Exception){}

}



    }
