package ir.liyanamarket.predictlive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.dataclass.GroupKala
import org.koin.core.KoinComponent
import org.koin.core.inject

class GroupKalaAdapter:KoinComponent,RecyclerView.Adapter<GroupKalaAdapter.CustomViewHolder>() {
    private val picasso:Picasso by inject()
    lateinit var list:List<GroupKala>
    private var index:Int=0
    inner class CustomViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
       val txtnamegroup:TextView=itemview.findViewById(R.id.txt_namegroup)
        val viewimageshape:View=itemview.findViewById(R.id.view_imageshape)
        val imagegroup:ImageView=itemview.findViewById(R.id.img_group)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
val view=LayoutInflater.from(parent.context).inflate(R.layout.groupkalarecycleritem,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.txtnamegroup.text=list[position].namegroup
        picasso.load(list[position].imagegroup).fit().into(holder.imagegroup)
        holder.viewimageshape.setOnClickListener {
          index=position
            notifyDataSetChanged()
        }
        if(index==position)
        {
            holder.viewimageshape.setBackgroundResource(R.drawable.viewimageshapegroupbackground)
        }
        else
        {
            holder.viewimageshape.setBackgroundResource(R.drawable.viewimageshapegroupnotselected)

        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}