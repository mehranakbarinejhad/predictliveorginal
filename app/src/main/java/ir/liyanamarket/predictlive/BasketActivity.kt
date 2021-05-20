package ir.liyanamarket.predictlive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import ir.liyanamarket.predictlive.`interface`.SendShowBasket
import ir.liyanamarket.predictlive.adapter.ShowBasketAdapter
import ir.liyanamarket.predictlive.dataclass.ShowBasket

import ir.liyanamarket.predictlive.presenter.shop.basket.PresenterApiConnectShowBasket
import kotlinx.android.synthetic.main.activity_basket.*
import org.koin.android.ext.android.inject


class BasketActivity : AppCompatActivity(), SendShowBasket {
    private val presenterApiConnectShowBasket: PresenterApiConnectShowBasket by inject()
    private val showBasketAdapter: ShowBasketAdapter by inject()

    lateinit var username: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        setSupportActionBar(toolbar_basketactivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        username = intent.getStringExtra("username").toString()
        showBasketAdapter.username=username
        presenterApiConnectShowBasket.sendShowBasket = this
        presenterApiConnectShowBasket.showbasket(username)


    }

    override fun onBackPressed() {
        finish()
    }

    override fun onsuccessShowBasket(list: List<ShowBasket>) {

        insertListToRecyclerViewBasket(list)

    }

    override fun onerrorShowBasket(t: Throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }


    private fun insertListToRecyclerViewBasket(list: List<ShowBasket>) {
        try {
            if (list.isNotEmpty()) {
                txt_no_basket.visibility = View.INVISIBLE
                recycler_basketactivity.visibility = View.VISIBLE
                showBasketAdapter.list = list

                recycler_basketactivity.apply {
                    layoutManager =
                        LinearLayoutManager(
                            this@BasketActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    adapter = showBasketAdapter
                }
            } else {
                txt_no_basket.visibility = View.VISIBLE
                recycler_basketactivity.visibility = View.INVISIBLE
                Toast.makeText(this, "سبد خرید خالی می باشد", Toast.LENGTH_LONG).show()
            }
        } catch (ex: Exception) {
        }
    }
}