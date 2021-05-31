package ir.liyanamarket.predictlive.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.text.Html

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.CompleteNewsActivity
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.dataclass.News
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

import kotlin.collections.ArrayList

@Suppress("CAST_NEVER_SUCCEEDS")
class NewsAdapter(var context:Context):RecyclerView.Adapter<NewsAdapter.CustomHolder>(),KoinComponent {
    private val picasso:Picasso by inject()
lateinit var activity:AppCompatActivity
    lateinit var list:List<News>
    inner class CustomHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val txttitrnews:TextView=itemview.findViewById(R.id.txt_newstitr)
        val txtdescriptionnews:TextView=itemview.findViewById(R.id.txt_newsdescription)
        val txtdatetimenews:TextView=itemview.findViewById(R.id.txt_newsdate)
        val imagetitr:ImageView=itemview.findViewById(R.id.img_newstitr)
        val parentnews:LinearLayout=itemview.findViewById(R.id.parentnewsrecycleritem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.newrecycleritem,parent,false)
                return CustomHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

        holder.txttitrnews.text=list[position].newstitr
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.txtdescriptionnews.text=Html.fromHtml(list[position].newsdescription,Html.FROM_HTML_MODE_COMPACT)
        }
        holder.txtdatetimenews.text= list[position].newsdate +"   " + list[position].newstime
        picasso.load(list[position].newstitrimage).fit().into(holder.imagetitr)
        if(position%2!=0)
        {
            holder.parentnews.setBackgroundResource(R.drawable.shapenewsrecycleritemswhite)
            holder.txtdatetimenews.setTextColor(Color.DKGRAY)
            holder.txtdescriptionnews.setTextColor(Color.DKGRAY)
            holder.txttitrnews.setTextColor(Color.BLACK)
        }

        holder.parentnews.setOnClickListener {

            val intent=Intent(activity,CompleteNewsActivity::class.java)
            intent.putExtra("newstitr",list[position].newstitr)
            intent.putExtra("newstext",list[position].newstext)
            intent.putExtra("newstitrimage",list[position].newstitrimage)

            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return list.size
    }



}