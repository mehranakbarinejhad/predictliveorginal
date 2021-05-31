package ir.liyanamarket.predictlive.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.NewsActivity
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.TopScoresActivity
import ir.liyanamarket.predictlive.`interface`.SendSelectItem
import ir.liyanamarket.predictlive.`interface`.SendSelectRankingUser
import ir.liyanamarket.predictlive.adapter.ItemAdapter
import ir.liyanamarket.predictlive.dataclass.Item
import ir.liyanamarket.predictlive.dataclass.RankingUser
import ir.liyanamarket.predictlive.presenter.item.PresenterApiConnectSelectItem
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectRankingUser
import ir.liyanamarket.predictlive.utils.MyMessage
import ir.liyanamarket.predictlive.view.ShopActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.homefragment.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import kotlin.Exception

class FragmentHome : Fragment(), SendSelectRankingUser,SendSelectItem {
    private val presenterApiConnectSelectItem:PresenterApiConnectSelectItem by inject()
    private val itemAdapter:ItemAdapter by inject()
    lateinit var activity2: AppCompatActivity
    private val presenterApiConnectRankingUser: PresenterApiConnectRankingUser by inject()
    private val myMessage: MyMessage by inject()
    private val picasso = get<Picasso>()
    lateinit var usernamelogin:String
    lateinit var completeusername:String
    lateinit var imageuser:String
    lateinit var bottomNavigationView: BottomNavigationView

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
            usernamelogin=activity2.intent.getStringExtra("usernameloginuser").toString()
            completeusername=activity2.intent.getStringExtra("nameuser").toString()
            imageuser=activity2.intent.getStringExtra("imageloginuser").toString()
            itemAdapter.activity=activity2
            itemAdapter.username=usernamelogin
            itemAdapter.imageuser=imageuser
            itemAdapter.completenameuser=completeusername
            itemAdapter.bottomNavigationView=bottomNavigationView
            presenterApiConnectSelectItem.sendSelectItem=this
            presenterApiConnectSelectItem.selectitem()
            myMessage.activity = activity2

            presenterApiConnectRankingUser.sendSelectRankingUser = this
            presenterApiConnectRankingUser.selectuserranking(usernamelogin)
            name_userprofile.text = completeusername
            username.text = usernamelogin
            phonenumber_user.text = activity2.intent.getStringExtra("phonenumberloginuser").toString()
            picasso.load(imageuser).fit().centerCrop().into(img_userprofile)

        } catch (ex: Exception) { }





    }

  /*  override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        bottomNavigationView.menu.getItem(0).isChecked=true
    }*/

    override fun onResume() {
        super.onResume()
        bottomNavigationView.menu.getItem(0).isChecked = true
    }



    override fun onsuccessrankinguser(list: List<RankingUser>) {
        try {
            score_text.text = list[0].Score
            txt_ranking.text = list[0].rank
        } catch (ex: Exception) {
        }
    }

    override fun onerrorrankinguser(t: Throwable) {
     //   myMessage.show("لطفا وضعیت اینترنت را بررسی نمایید.")

    }

    override fun onsuccessitem(list: List<Item>) {
        itemAdapter.list=list
        recycler_item.apply {
            layoutManager=GridLayoutManager(activity2,4,GridLayoutManager.VERTICAL,false)
            adapter=itemAdapter
        }

    }

    override fun onerroritem(t: Throwable) {
            showInternetConnectionDialog()
    }




    private fun showInternetConnectionDialog(){
        val builder= AlertDialog.Builder(activity)
        builder.setTitle("خطا در اتصال به اینترنت")
        builder.setMessage("لطفا وضعیت اینترنت را بررسی نمایید")
            .setCancelable(false)
        builder.setPositiveButton("تلاش دوباره"){_,_ ->
            presenterApiConnectRankingUser.selectuserranking(usernamelogin)
            presenterApiConnectSelectItem.selectitem()
        }
        builder.setNegativeButton("خروج از برنامه"){_,_->
            activity2.finishAffinity()
        }
        builder.show()
    }

}
