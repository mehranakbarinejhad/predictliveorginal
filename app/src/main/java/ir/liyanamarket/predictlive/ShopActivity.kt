package ir.liyanamarket.predictlive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.`interface`.SendGroupInterface
import ir.liyanamarket.predictlive.`interface`.SendSelectKalaInterface
import ir.liyanamarket.predictlive.adapter.GroupKalaAdapter
import ir.liyanamarket.predictlive.adapter.KalaAdapter
import ir.liyanamarket.predictlive.adapter.SpinerAdapter
import ir.liyanamarket.predictlive.dataclass.GroupKala
import ir.liyanamarket.predictlive.dataclass.Kala
import ir.liyanamarket.predictlive.dataclass.SpinerList
import ir.liyanamarket.predictlive.presenter.shop.group.PresenterApiConnectGroup
import ir.liyanamarket.predictlive.presenter.shop.kala.PresenterApiConnectSelectKala
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.toolbar_shop.*
import org.koin.android.ext.android.inject

class ShopActivity : AppCompatActivity(), SendSelectKalaInterface, SendGroupInterface {
    private val listspiner = listOf(
        SpinerList("جدیدترین",R.drawable.newicon),
        SpinerList("ارزانترین", R.drawable.arzan),
        SpinerList("گرانترین", R.drawable.geran)

    )

    private val presenterApiConnectSelectKala: PresenterApiConnectSelectKala by inject()
    private val presenterApiConnectGroup: PresenterApiConnectGroup by inject()
    private val groupKalaAdapter: GroupKalaAdapter by inject()
    private val spinerAdapter: SpinerAdapter by inject()
    private val kalaAdapter: KalaAdapter by inject()
    private val picasso:Picasso by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val view=navigation_view_shop.getHeaderView(0)
        val txtNameHeadershop=view.findViewById<TextView>(R.id.txt_nameuser_shop_header)
        val imageprofileHeaderShop=view.findViewById<ImageView>(R.id.img_profileshop_header)
        txtNameHeadershop.text = intent.getStringExtra("nameuser").toString()
        picasso.load(intent.getStringExtra("imageprofile").toString()).fit().centerCrop().into(imageprofileHeaderShop)
        spinerAdapter.list = listspiner
        spineritem.adapter=spinerAdapter
        presenterApiConnectSelectKala.sendSelectKalaInterface = this
        presenterApiConnectSelectKala.selectkala("All")
        presenterApiConnectGroup.sendGroupInterface = this
        presenterApiConnectGroup.getgroup()
        img_navigation_show.setOnClickListener {
           drawer_layout_shop.openDrawer(GravityCompat.END)
        }


    }

    override fun onsuccess(list: List<Kala>) {

        kalaAdapter.list = list
        recyclerkala.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, true)
            adapter = kalaAdapter
        }
    }

    override fun onerror(t: Throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()

    }

    override fun onsuccessgroup(list: List<GroupKala>) {
        groupKalaAdapter.list = list
        recycler_groupkala.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, true)
            adapter = groupKalaAdapter
        }
    }

    override fun onerrorgroup(t: Throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {

        if(drawer_layout_shop.isDrawerOpen(GravityCompat.END))
        {
            drawer_layout_shop.closeDrawer(GravityCompat.END)
        }
        else
        {
            super.onBackPressed()
        }
    }
}
