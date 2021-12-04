package non.shahad.twilioconversation.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import non.shahad.twilioconversation.persistence.SharedPreferenceHelper
import non.shahad.twilioconversation.service.TwilioService
import non.shahad.twilioconversation.service.interceptors.AuthInterceptor
import non.shahad.twilioconversation.service.repository.ChatRepository
import non.shahad.twilioconversation.service.repository.ConversationRepository
import non.shahad.twilioconversation.service.repository.LoginRepository
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
    fun provideOkHttpClient(@ApplicationContext context: Context,preferenceHelper: SharedPreferenceHelper) = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(preferenceHelper))
        .addInterceptor(ChuckInterceptor(context))
        .build()

    @Provides
    @Singleton
    fun provideTwilioService(client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://twiliochat11.herokuapp.com/api/")
        .client(client)
        .build()
        .create(TwilioService::class.java)

    @Provides
    @Singleton
    fun provideSharedPrefHelper(
        @ApplicationContext context: Context
    ) = SharedPreferenceHelper(context,"things-we-were-promised.pref")

    @Provides
    @Singleton
    fun provideConversationRepository(service: TwilioService) = ConversationRepository(service)

    @Provides
    @Singleton
    fun provideUserRepository(service: TwilioService) = UserRepository(service)

    @Provides
    @Singleton
    fun provideChatRepository(service: TwilioService) = ChatRepository(service)

    @Provides
    @Singleton
    fun provideLoginRepository(service: TwilioService) = LoginRepository(service)
}