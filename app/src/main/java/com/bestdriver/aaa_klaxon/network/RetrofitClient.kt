package com.bestdriver.aaa_klaxon.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.bestdriver.aaa_klaxon.network.auth.AuthApiService
import com.bestdriver.aaa_klaxon.network.mypage.MypageApiService
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitClient {
    private const val BASE_URL = "http://43.202.104.135:3000/"

    private var retrofit: Retrofit? = null

    private fun getRetrofitInstance(context: Context): Retrofit {
        if (retrofit == null) {
            val tokenManager = TokenManager(context)

            // 로깅 인터셉터 추가
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor) // 로깅 인터셉터 추가
                .addInterceptor(AuthInterceptor(tokenManager))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!!
    }

    fun getAuthApiService(context: Context): AuthApiService {
        return getRetrofitInstance(context).create(AuthApiService::class.java)
    }

    fun getMypageApiService(context: Context): MypageApiService {
        return getRetrofitInstance(context).create(MypageApiService::class.java)
    }
}
