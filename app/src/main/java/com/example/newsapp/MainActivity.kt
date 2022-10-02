package com.example.newsapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.app.utils.navigation.Activities
import com.example.newsapp.databinding.ActivityMainBinding
import com.universal.utils.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding?=null
    private val binding:ActivityMainBinding
    get() = _binding!!

    @Inject
    lateinit var provider: Navigator.Provider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor  = ContextCompat.getColor(this,R.color.white)
        _binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({
            provider.getActivity(Activities.NewsActivity).navigate(this)
            finish()
        }, 2700)

    }
}