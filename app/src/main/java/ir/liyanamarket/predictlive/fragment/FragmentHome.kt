package ir.liyanamarket.predictlive.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendSelectRankingUser
import ir.liyanamarket.predictlive.dataclass.RankingUser
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectRankingUser
import ir.liyanamarket.predictlive.utils.MyMessage
import ir.liyanamarket.predictlive.view.ShopActivity
import kotlinx.android.synthetic.main.homefragment.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import kotlin.Exception

class FragmentHome : Fragment(), SendSelectRankingUser {
    lateinit var activity2: AppCompatActivity
    private val presenterApiConnectRankingUser: PresenterApiConnectRankingUser by inject()
    private val myMessage: MyMessage by inject()
    private val picasso = get<Picasso>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.homefragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        try {
            myMessage.activity = activity2

            presenterApiConnectRankingUser.sendSelectRankingUser = this
            presenterApiConnectRankingUser.selectuserranking(
                activity2.intent.getStringExtra("usernameloginuser").toString()
            )
            name_userprofile.text = activity2.intent.getStringExtra("nameuser").toString()
            username.text = activity2.intent.getStringExtra("usernameloginuser").toString()
            phonenumber_user.text =
                activity2.intent.getStringExtra("phonenumberloginuser").toString()

            picasso.load(activity2.intent.getStringExtra("imageloginuser")).fit().centerCrop()
                .into(img_userprofile)
            picasso.load("http://dl.liyanamarket.ir/Images/shop.webp").fit().into(img_todaymatch)
            picasso.load("http://dl.liyanamarket.ir/Images/tableleague.webp").fit().into(img_tablelegaue)
            picasso.load("http://dl.liyanamarket.ir/Images/topscorerleague.webp").fit().into(img_topscorelegaue)
            picasso.load("http://dl.liyanamarket.ir/Images/videofootball.jpg").fit().into(img_video)


        } catch (ex: Exception) {
        }
        img_todaymatch.setOnClickListener {
            val intent = Intent(activity2.applicationContext, ShopActivity::class.java)
            intent.putExtra(
                "imageprofile",
                activity2.intent.getStringExtra("imageloginuser").toString()
            )
            intent.putExtra("nameuser", activity2.intent.getStringExtra("nameuser").toString())
            activity2.startActivity(intent)
        }


    }

    override fun onsuccessrankinguser(list: List<RankingUser>) {
        try {
            score_text.text = list[0].Score
            txt_ranking.text = list[0].rank
        } catch (ex: Exception) {
        }
    }

    override fun onerrorrankinguser(t: Throwable) {
        myMessage.show("لطفا وضعیت اینترنت را بررسی نمایید.")

    }


}
