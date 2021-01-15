package com.tatak.footballapp.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.tatak.footballapp.BuildConfig.BASE_URL
import com.tatak.footballapp.BuildConfig.TSDB_API_KEY

class FootballApiService {

    companion object {
        fun getClient() : Retrofit{
            return Retrofit.Builder()
                    .baseUrl("$BASE_URL$TSDB_API_KEY/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}