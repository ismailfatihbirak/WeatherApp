package com.example.weather.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.Root

import com.example.weather.databinding.ViewholderHourlyBinding
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SaatlikAdapter(var mContext:Context,var saatlikListe:List<Root>) :RecyclerView.Adapter<SaatlikAdapter.SaatlikTasarimTutucu>(){


    inner class SaatlikTasarimTutucu(var binding: ViewholderHourlyBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaatlikTasarimTutucu {
        val binding = ViewholderHourlyBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return SaatlikTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: SaatlikTasarimTutucu, position: Int) {
        val saat = saatlikListe.get(position)
        val t = holder.binding

        val veri = saat.dt_txt
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val tarihVeSaat: Date = dateFormat.parse(veri)

        // Sadece saat kısmını alıp yazdırma
        val saatFormat = SimpleDateFormat("HH:mm")
        val sadeceSaat: String = saatFormat.format(tarihVeSaat)

        t.tempTxt.text = saat.main.temp.toString()
        t.hourTxt.text = sadeceSaat

        for (i in saat.weather){
            when (i.main) {
                "Rain" -> t.pic.setImageResource(mContext.resources.getIdentifier("rainy", "drawable", mContext.packageName))
                "Clear" -> t.pic.setImageResource(mContext.resources.getIdentifier("sunny", "drawable", mContext.packageName))
                "Clouds" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy_sunny", "drawable", mContext.packageName))
                "Snow" -> t.pic.setImageResource(mContext.resources.getIdentifier("snowy", "drawable", mContext.packageName))
                "Drizzle" -> t.pic.setImageResource(mContext.resources.getIdentifier("rainy", "drawable", mContext.packageName))
                "Thunderstorm" -> t.pic.setImageResource(mContext.resources.getIdentifier("rainy", "drawable", mContext.packageName))
                "Mist" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))
                "Smoke" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))
                "Haze" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))
                "Dust" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))
                "Fog" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))
                "Sand" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))
                "Dust" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))
                "Ash" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))
                "Squall" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))
                "Tornado" -> t.pic.setImageResource(mContext.resources.getIdentifier("cloudy", "drawable", mContext.packageName))

                else -> {
                    Log.e("hata","bilinmeyen hava durumu switch")
                }
            }

        }
    }


}


