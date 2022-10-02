package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.app.utils.navigation.Activities
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.navigation.DefaultNavigatorProvider
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
        _binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        setContentView(binding.root)

        Thread.sleep(2500)

        provider.getActivity(Activities.NewsActivity).navigate(this)


    }
}