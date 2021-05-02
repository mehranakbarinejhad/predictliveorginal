package ir.liyanamarket.predictlive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import ir.liyanamarket.predictlive.R
import kotlinx.android.synthetic.main.activity_success_register_activity.*


class SuccessRegisterActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_register_activity)

        img_success_successactivity.animation=AnimationUtils.loadAnimation(this, R.anim.zoomout)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent=Intent(applicationContext,LoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}