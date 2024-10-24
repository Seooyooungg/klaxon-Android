package com.bestdriver.aaa_klaxon.network.home

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestdriver.aaa_klaxon.network.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// MapViewModel 수정 (TrafficError 데이터 사용)
class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val _trafficData = MutableStateFlow<List<TrafficError>>(emptyList())
    val trafficData: StateFlow<List<TrafficError>> get() = _trafficData

    private val apiService: MapApiService
    private val tokenManager: TokenManager

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://43.202.104.135:3000/") // 실제 API의 베이스 URL로 대체
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(MapApiService::class.java)
        tokenManager = TokenManager(application)

        fetchTrafficErrors()
    }

    fun fetchTrafficErrors() {
        viewModelScope.launch {
            val token = tokenManager.getToken()
            if (token == null) {
                Log.e("MapViewModel", "Token is null")
                return@launch
            }
            try {
                val response = apiService.getTrafficErrors(token)
                if (response.isSuccess) {
                    val trafficErrors = response.result ?: emptyList()
                    Log.d("MapViewModel", "Fetched traffic errors: $trafficErrors")
                    _trafficData.value = trafficErrors
                } else {
                    Log.e("MapViewModel", "Traffic errors fetching failed: ${response.code} - ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("MapViewModel", "Exception fetching traffic errors: ${e.localizedMessage}")
            }
        }
    }
}

