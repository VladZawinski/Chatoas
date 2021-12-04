package non.shahad.twilioconversation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import non.shahad.twilioconversation.service.TwilioService
import non.shahad.twilioconversation.service.interceptors.AuthInterceptor
import non.shahad.twilioconversation.service.repository.ConversationRepository
import non.shahad.twilioconversation.service.repository.UserRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideTwilioService(client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://twiliochat11.herokuapp.com/api/")
        .client(client)
        .build()
        .create(TwilioService::class.java)

//    @Provides
//    @Singleton
//    fun provideConversationRepository(service: TwilioService) = ConversationRepository(service)

    @Provides
    @Singleton
    fun provideUserRepository(service: TwilioService) = UserRepository(service)
}