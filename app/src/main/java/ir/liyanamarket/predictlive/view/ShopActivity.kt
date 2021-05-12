package ir.liyanamarket.predictlive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
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

import kotlin.collections.ArrayList

class ShopActivity : AppCompatActivity(), SendGroupInterface, SendSelectKalaInterface {

    //region variable array list  send to searchactivity
   private var arraylist = ArrayList<Kala>()


    //endregion
    //region SpinerList Item
    private val listspiner = listOf(
        SpinerList("جدیدترین", R.drawable.newicon),
        SpinerList("ارزانترین", R.drawable.arzan),
        SpinerList("گرانترین", R.drawable.geran)

    )
    //endregion
    //region Call Class With koin
    private val presenterApiConnectSelectKala: PresenterApiConnectSelectKala by inject()
    private val presenterApiConnectGroup: PresenterApiConnectGroup by inject()
    private val groupKalaAdapter: GroupKalaAdapter by inject()
    private val spinerAdapter: SpinerAdapter by inject()
    private val kalaAdapter: KalaAdapter by inject()
    private val picasso: Picasso by inject()
    //endregion
    //region onCreate Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        //region set customtoolbar
        setSupportActionBar(m_toolbar_shop)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title="فروشگاه"

//endregion

        //region set profile image and name user to header navigation view
        val view = navigation_view_shop.getHeaderView(0)
        val txtNameHeadershop = view.findViewById<TextView>(R.id.txt_nameuser_shop_header)
        val imageprofileHeaderShop = view.findViewById<ImageView>(R.id.img_profileshop_header)
        txtNameHeadershop.text = intent.getStringExtra("nameuser").toString()
        picasso.load(intent.getStringExtra("imageprofile").toString()).fit().centerCrop()
            .into(imageprofileHeaderShop)
        //endregion
        //region set spinerlist item to SpinerView
        spinerAdapter.list = listspiner
        spineritem.adapter = spinerAdapter
        //endregion
        //region set All List Kala To recyclerview Kala
       presenterApiConnectSelectKala.sendSelectKalaInterface = this
        presenterApiConnectSelectKala.selectkala("All")

        //endregion
        //region set Group To RecyclerView Group
        presenterApiConnectGroup.sendGroupInterface = this
        presenterApiConnectGroup.getgroup()
        //endregion

    }
    //endregion
    //region Onsuccess Or onError Callback Kala List
    override fun onsuccess(list: MutableList<Kala>) {
        arraylist = list as ArrayList<Kala>
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

    //endregion
    //region Onsuccess Or onError Callback Group List
    override fun onsuccessgroup(list: List<GroupKala>) {

        groupKalaAdapter.list = list
        groupKalaAdapter.recyclerView = recyclerkala
        recycler_groupkala.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, true)
            adapter = groupKalaAdapter
        }
    }

    override fun onerrorgroup(t: Throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    //endregion
    //region On back pressed Click
    override fun onBackPressed() {

        if (drawer_layout_shop.isDrawerOpen(GravityCompat.END)) {
            drawer_layout_shop.closeDrawer(GravityCompat.END)
        } else {

            finish()
        }
    }
    //endregion

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.mnu_show_drawerlayout ->{
                drawer_layout_shop.openDrawer(GravityCompat.END)
            }
            R.id.mnu_searchactivity ->{
                val intent=Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra("listf",arraylist)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
