package ru.msu.cmc.weatherapp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

fun isPermissionGranted(activity: AppCompatActivity, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(activity, permission) ==
            PackageManager.PERMISSION_GRANTED
}