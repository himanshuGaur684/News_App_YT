package com.example.newsapp

import com.example.newsapp.navigation.DefaultNavigatorProvider
import com.universal.utils.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BaseAppModule {


    @Singleton
    @Provides
    fun provideDefaultNavigator():Navigator.Provider{
        return DefaultNavigatorProvider()
    }

}