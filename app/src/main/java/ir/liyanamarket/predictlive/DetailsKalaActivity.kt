package ir.liyanamarket.predictlive

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.core.graphics.green
import androidx.core.view.marginStart
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_kala.*
import org.koin.android.ext.android.inject


class DetailsKalaActivity : AppCompatActivity() {
private val picasso:Picasso by inject()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_kala)
        try {
            val descriptionkala=splitDescriptionWithKama(intent.getStringExtra("description").toString())
            val detailskala=splitDescriptionWithKama(intent.getStringExtra("details").toString())
            picasso.load(intent.getStringExtra("image").toString()).fit().into(img_kala_details_activity)
            txt_price_details_activity.text=intent.getStringExtra("price").toString()
            txt_titr_details_activity.text=intent.getStringExtra("titr").toString()
            txt_details_detailsactivity.movementMethod=ScrollingMovementMethod()
            txt_details_detailsactivity.text=descriptionkala


            //region create Tab Layout And Click Command
            val detailstab=tablayout_detailsactivity.newTab()
            detailstab.text="مشخصات"
            tablayout_detailsactivity.addTab(detailstab)
            val descriptiontab=tablayout_detailsactivity.newTab()
                descriptiontab.text="توضیحات"
            descriptiontab.view.setBackgroundResource(R.drawable.shapetabitemseleected)
            tablayout_detailsactivity.addTab(descriptiontab)
            tablayout_detailsactivity.selectTab(descriptiontab)
            tablayout_detailsactivity.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if(tab!=null)
                    {
                        if(tab.position==0)
                        {
                            txt_details_detailsactivity.text=detailskala
                            detailstab.view.setBackgroundResource(R.drawable.shapetabitemseleected)
                            descriptiontab.view.setBackgroundResource(R.drawable.shapetabitemunselected)
                        }
                        else{
                            txt_details_detailsactivity.text=descriptionkala
                            descriptiontab.view.setBackgroundResource(R.drawable.shapetabitemseleected)
                            detailstab.view.setBackgroundResource(R.drawable.shapetabitemunselected)


                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
            //endregion


        }
            catch (ex:Exception){}





    }


    override fun onBackPressed() {
        finish()
    }

    fun splitDescriptionWithKama(text:String):String{
        var result=""
        val spltext=text.split(',')
        spltext.forEach {
            result+=it+"\n\n"
        }
        return result
    }
}