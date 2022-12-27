package ru.msu.cmc.weatherapp.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.media.audiofx.Equalizer.Settings
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SettingsSlicesContract
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.activityViewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import ru.msu.cmc.weatherapp.R
import ru.msu.cmc.weatherapp.isPermissionGranted
import ru.msu.cmc.weatherapp.permitions.DialogManager
import ru.msu.cmc.weatherapp.presentation.mainlist.FragmentGeneral
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var fLocationClient: FusedLocationProviderClient

    private lateinit var geocoder: Geocoder

    private lateinit var pLauncher: ActivityResultLauncher<String>

    private var onSaveInstanceState: Bundle? = null

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()

        if (!isLocationEnabled())
            Toast.makeText(this, "Location disabled!", Toast.LENGTH_SHORT).show()

        onSaveInstanceState = savedInstanceState

        if (checkLocation())
            getLocation()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onResume() {
        super.onResume()
        if (checkLocation())
            getLocation()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun isLocationEnabled(): Boolean{
        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.FUSED_PROVIDER)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun checkLocation(): Boolean {
        if (!isLocationEnabled()) {
            Log.d("MyLog", "Im in dialog")
            DialogManager.locationSettingsDialog(this, object : DialogManager.Listener{
                override fun onOkClick() {
                    startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }

                override fun onCancelClick() {
                    finish()
                }
            })
            return false
        }
        return true
    }

    private fun getLocation() {
        fLocationClient = LocationServices.getFusedLocationProviderClient(this)

        geocoder = Geocoder(this, Locale.getDefault())

        val ct = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, ct.token)
            .addOnSuccessListener {
                val address = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                val town = address!![0].locality.toString()
                Log.d("MyLog", town)
//                if (onSaveInstanceState == null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fmt_container, FragmentGeneral().createFragment(town))
                        .commit()
//                }

                onBackPressedDispatcher.addCallback(
                    this,
                    object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() {
                            if (supportFragmentManager.fragments.size < 3)
                                supportFragmentManager.popBackStack(
                                    "general",
                                    POP_BACK_STACK_INCLUSIVE)
                            else
                                finish()
                        }
                    }
                )
            }

    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) {
            Toast.makeText(this, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!isPermissionGranted(this, Manifest.permission.INTERNET)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.INTERNET)
        }
    }
}