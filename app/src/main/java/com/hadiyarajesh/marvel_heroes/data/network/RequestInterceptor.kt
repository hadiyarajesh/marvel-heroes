package com.hadiyarajesh.marvel_heroes.data.network

import com.hadiyarajesh.marvel_heroes.BuildConfig
import com.hadiyarajesh.marvel_heroes.utility.HashGenerator
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.random.Random

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val timestamp = Random.nextInt(10000, 99999).toString()
        val apiPublicKey = BuildConfig.apiPublicKey
        val apiPrivateKey = BuildConfig.apiPrivateKey
        val hashInputString = timestamp + apiPrivateKey + apiPublicKey
        val generatedHash = HashGenerator.generateMD5(hashInputString)

        val url = request.url.newBuilder()
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("apikey", apiPublicKey)
            .addQueryParameter("hash", generatedHash)
            .build()

        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}
