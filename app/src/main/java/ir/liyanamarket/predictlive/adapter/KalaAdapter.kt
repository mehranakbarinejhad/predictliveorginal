package ir.liyanamarket.predictlive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.dataclass.Kala
import org.koin.core.KoinComponent
import org.koin.core.inject

class KalaAdapter(private var context:Context) : RecyclerView.Adapter<KalaAdapter.Customviewholder>(),KoinComponent {
    private val picasso:Picasso by inject()
    lateinit var list: List<Kala>
    //val backgroundparen= listOf(R.drawable.shaperecyclerkalaitemgreen,R.drawable.shaperecyclerkalaitemblue)
    inner class Customviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txttitrkala: TextView = itemview.findViewById(R.id.txt_titrkala)
        val txtnamekala: TextView = itemview.findViewById(R.id.txt_namekala)
        val imageflagh: ImageView = itemview.findViewById(R.id.img_flagkala)
        val imgkala: ImageView = itemview.findViewById(R.id.img_kala)
        val price: TextView = itemview.findViewById(R.id.txt_price)
        val recyclerkalaparent:RelativeLayout=itemview.findViewById(R.id.recyclerkalaparent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Customviewholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.kalaitemrecycler, parent, false)
        return Customviewholder(view)
    }

    override fun onBindViewHolder(holder: Customviewholder, position: Int) {
        holder.recyclerkalaparent.animation=AnimationUtils.loadAnimation(context,R.anim.fade_transtion_animation)
        holder.txttitrkala.text = list[position].titr
        holder.txtnamekala.text = list[position].name
        picasso.load(list[position].Countryimage).fit().centerCrop().into(holder.imageflagh)
        picasso.load(list[position].image).fit().into(holder.imgkala)
        holder.price.text=list[position].price
        when((1..4).random()) {
            1 ->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitemgreen)
            2->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitemblue)
            3->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitemorange)
            4->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitemred)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }




}