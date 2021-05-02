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
import ir.liyanamarket.predictlive.ShopActivity


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
            picasso.load("http://dl.liyanamarket.ir/Images/shop.png").fit().into(img_todaymatch)
            picasso.load("http://dl.liyanamarket.ir/Images/tableleagueimage.png").fit().into(img_tablelegaue)
            picasso.load("http://dl.liyanamarket.ir/Images/topscorerleague.jpg").fit().into(img_topscorelegaue)
            picasso.load("http://dl.liyanamarket.ir/Images/videofootball.jpg").fit().into(img_video)


        }
        catch (ex:Exception){}
        img_todaymatch.setOnClickListener {
            val intent=Intent(activity2.applicationContext,ShopActivity::class.java)
            intent.putExtra("imageprofile",activity2.intent.getStringExtra("imageloginuser").toString())
            intent.putExtra("nameuser",activity2.intent.getStringExtra("nameuser").toString())
            activity2.startActivity(intent)
        }


}


    }
