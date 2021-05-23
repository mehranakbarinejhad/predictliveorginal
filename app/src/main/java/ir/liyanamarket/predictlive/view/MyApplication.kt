package ir.liyanamarket.predictlive.view

import android.app.Application
import android.widget.Toast
import ir.liyanamarket.predictlive.appmadules
import ir.liyanamarket.predictlive.utils.CheckNetworkConnection
import org.koin.android.ext.android.inject

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@MyApplication)
           modules(listOf(appmadules))
        }

    }

}