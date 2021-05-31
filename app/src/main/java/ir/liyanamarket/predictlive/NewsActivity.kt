package ir.liyanamarket.predictlive

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ir.liyanamarket.predictlive.`interface`.SendSelectNews
import ir.liyanamarket.predictlive.adapter.NewsAdapter
import ir.liyanamarket.predictlive.dataclass.News
import ir.liyanamarket.predictlive.presenter.news.PresenterApiConnectSelectNews
import kotlinx.android.synthetic.main.activity_news.*
import org.koin.android.ext.android.inject
import java.lang.Exception

class NewsActivity : AppCompatActivity(),SendSelectNews {
    private val presenterApiConnectSelectNews:PresenterApiConnectSelectNews by inject()
    private val newsAdapter:NewsAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        newsAdapter.activity=this
        presenterApiConnectSelectNews.sendSelectNews=this

        presenterApiConnectSelectNews.selectnews()
    }

    override fun onsuccessSelectNews(list: List<News>) {
        try {
            newsAdapter.list=list
            recycler_view_newsactivity.apply {
                layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                adapter=newsAdapter

            }
        }catch (ex:Exception){}

    }

    override fun onerrorSelectNews(t: Throwable) {
   showInternetConnectionDialog()

    }


    private fun showInternetConnectionDialog(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("خطا در اتصال به اینترنت")
        builder.setMessage("لطفا وضعیت اینترنت را بررسی نمایید")
            .setCancelable(false)
        builder.setPositiveButton("تلاش دوباره"){_,_ ->
            presenterApiConnectSelectNews.selectnews()

        }
        builder.setNegativeButton("خروج از برنامه"){_,_->
            this.finishAffinity()
        }
        builder.show()
    }
}