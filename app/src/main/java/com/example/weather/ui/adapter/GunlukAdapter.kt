package com.example.weather.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.Root

import com.example.weather.databinding.ViewholderFutureBinding
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class GunlukAdapter (var mContext: Context, var gunlukListe:List<Root>) :
    RecyclerView.Adapter<GunlukAdapter.GunlukTasarimTutucu>(){

    inner class GunlukTasarimTutucu(var binding: ViewholderFutureBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunlukTasarimTutucu {
        val binding = ViewholderFutureBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return GunlukTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return gunlukListe.size
    }

    override fun onBindViewHolder(holder: GunlukTasarimTutucu, position: Int) {
        val gun = gunlukListe.get(position)
        val t = holder.binding

        for (i in gun.weather){
            t.statusTxt.text = i.description
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

        val veri = gun.dt_txt
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val tarihObjesi: Date = dateFormat.parse(veri)
        val gunFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val gunb: String = gunFormat.format(tarihObjesi)

        t.dayTxt.text = gunb
        t.highTxt.text = gun.main.temp_max.toString()
        t.lowTxt.text = gun.main.temp_min.toString()
    }


}
