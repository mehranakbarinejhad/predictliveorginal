package ir.liyanamarket.predictlive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_complete_news.*
import org.koin.android.ext.android.inject

class CompleteNewsActivity : AppCompatActivity() {
    private val picasso:Picasso by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_news)
        val newstitr = intent.getSerializableExtra("newstitr").toString()
        val newstext = intent.getStringExtra("newstext").toString()
        val newstitrimage = intent.getStringExtra("newstitrimage").toString()

picasso.load(newstitrimage).fit().into(img_titr_completenewsactivity)
        txt_titr_completenews.text=newstitr
        txt_textnews.text=newstext

    }
}