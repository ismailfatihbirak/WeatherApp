package com.example.weather.ui.fragment

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.data.model.Konum
import com.example.weather.data.model.Root

import com.example.weather.databinding.FragmentDetayBinding
import com.example.weather.ui.adapter.GunlukAdapter
import com.example.weather.ui.adapter.SaatlikAdapter
import com.example.weather.ui.viewmodel.AnasayfaViewModel
import com.example.weather.ui.viewmodel.DetayViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private lateinit var viewModel : DetayViewModel
    private var izinKontrol = 0
    private lateinit var flpc: FusedLocationProviderClient
    private lateinit var locationTask: Task<Location>
    var a = Konum(40.616667,43.1)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)


        binding.backBtn.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.back)
        }

        binding.rvDetay.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        viewModel.gunListesi.observe(viewLifecycleOwner){
            val dailyData = mutableListOf<Root>()
            dailyData.add(it[14])
            dailyData.add(it[21])
            dailyData.add(it[28])
            dailyData.add(it[35])
            val gunlukAdapter = GunlukAdapter(requireContext(),dailyData)
            binding.rvDetay.adapter = gunlukAdapter

            when (it[7].weather[0].main) {
                "Rain" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.rainy)
                "Clear" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.sunny)
                "Clouds" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy_sunny)
                "Snow" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.snowy)
                "Drizzle" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.rainy)
                "Thunderstorm" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.rainy)
                "Mist" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                "Smoke" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                "Haze" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                "Dust" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                "Fog" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                "Sand" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                "Dust" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                "Ash" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                "Squall" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                "Tornado" -> binding.imageViewBuyukDurum.setImageResource(R.drawable.cloudy)
                else -> {
                    Log.e("hata","bilinmeyen hava durumu switch")
                }
            }


            binding.textViewDerece.text = "${it[7].main.temp}°"

            binding.textViewDurumText.text = it[7].weather[0].description.toUpperCase()

            binding.textViewSemsiyeYagmur.text = "%22"

            binding.textViewRuzgarHiziDeger.text = it[7].wind?.speed.toString()

            binding.textViewNemDeger.text = "%${it[7].main?.humidity}"

        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetayViewModel by viewModels()
        viewModel = tempViewModel

        flpc = LocationServices.getFusedLocationProviderClient(requireActivity())


        izinKontrol = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)

        if (izinKontrol != PackageManager.PERMISSION_GRANTED){//izin onaylanmamışsa
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
        }else{//İzin onaylanmışsa
            locationTask = flpc.lastLocation
            konumBilgisiAl()
        }


    }

    fun konumBilgisiAl(){
        locationTask.addOnSuccessListener { location ->
            if (location != null) {
                CoroutineScope(Dispatchers.Main).launch {
                    a.lat = location.latitude
                    a.lon = location.longitude
                    viewModel.detayRecyclerview(a)
                }
            } else {
                Log.e("hata","enlem alınamadı")
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 100){

            izinKontrol = ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)

            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(requireContext(),"İzin kabul edildi", Toast.LENGTH_SHORT).show()
                locationTask = flpc.lastLocation
                konumBilgisiAl()
            }else{
                Toast.makeText(requireContext(),"İzin reddedildi", Toast.LENGTH_SHORT).show()
            }
        }
    }


}