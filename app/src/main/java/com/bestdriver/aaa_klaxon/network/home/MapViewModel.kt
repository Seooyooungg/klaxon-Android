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


class MapViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData 또는 StateFlow 관리 (Compose에서 State 사용 가능)
    private val _trafficData = MutableStateFlow<List<TrafficError>>(emptyList())
    val trafficData: StateFlow<List<TrafficError>> get() = _trafficData

    private val apiService: MapApiService
    private val tokenManager: TokenManager

    init {
        // Retrofit 인스턴스 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://43.202.104.135:3000/") // 실제 API의 베이스 URL로 대체
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(MapApiService::class.java)
        tokenManager = TokenManager(application) // Application context를 전달

        // 데이터를 가져오기 위한 첫 API 호출
        fetchTrafficErrors()
    }

    // API 호출
    fun fetchTrafficErrors() {
        viewModelScope.launch {
            val token = tokenManager.getToken() // 저장된 토큰을 가져옴
            if (token == null) {
                Log.e("MapViewModel", "Token is null")
                return@launch
            }
            try {
                // API 호출, 응답은 TrafficResponse 객체일 것으로 가정
                val response = apiService.getTrafficErrors(token)
                if (response.isSuccess) { // 여기서는 isSuccess를 사용
                    val trafficErrors = response.result ?: emptyList() // result가 null이면 빈 리스트로 처리
                    Log.d("MapViewModel", "Fetched traffic errors: $trafficErrors")
                    _trafficData.value = trafficErrors // StateFlow에 데이터 저장
                } else {
                    Log.e("MapViewModel", "Traffic errors fetching failed: ${response.code} - ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("MapViewModel", "Exception fetching traffic errors: ${e.localizedMessage}")
            }
        }
    }
}

