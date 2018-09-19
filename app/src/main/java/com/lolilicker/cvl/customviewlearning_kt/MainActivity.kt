package com.lolilicker.cvl.customviewlearning_kt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lolilicker.cvl.customviewlearning_kt.domain.Forecast
import com.lolilicker.cvl.customviewlearning_kt.domain.ForecastDataMapper
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    private val API = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7&APPID=15646a06818f61f7b8d7823ca833e1ce&q=BeiJing";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        doAsync {
           val result =  Request("BeiJing").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(ForecastDataMapper().convertFromDataModel(result),
                       object :ForecastListAdapter.OnItemClickLisener{
                           override fun invoke(forecast: Forecast) {
                               toast(forecast.date)
                           }
                       })
            }
        }
    }
}
