package com.universal.utils.navigation

import android.app.Activity
import com.app.utils.navigation.Activities

interface Navigator {

    fun navigate(activity:Activity)

    interface Provider{
        fun getActivity(activities: Activities): Navigator
    }

}