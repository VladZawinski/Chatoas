package non.shahad.twilioconversation.service.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor constructor(
//    private val prefHelper: PrefHelper
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()
            .addHeader("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxYTljM2M3YjVjZDY1MDI1ZmExZjRhYyIsImlhdCI6MTYzODU5MzQwMCwiZXhwIjoxNjM4NTk3MDAwfQ.dgH1YLmiWQ64KQ_HxC8_mq0JK0fykp2liQE5Aq4YJ9o")
            .addHeader("Accept","application/json")
            .addHeader("Content-Type","application/json")

        return chain.proceed(requestBuilder.build())
    }
}