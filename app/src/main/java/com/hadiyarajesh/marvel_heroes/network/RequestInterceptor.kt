package com.hadiyarajesh.marvel_heroes.network

import com.hadiyarajesh.marvel_heroes.utility.HashGenerator
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.random.Random

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val timestamp = Random.nextInt(10000, 99999).toString()
        val apiPublicKey = "9b4a79ad4a27def8ec8b5f4ff3edd92d"
        val apiPrivateKey = "d5476af6b7fcb17b078d170bf6f81f0d55b1e37e"
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
