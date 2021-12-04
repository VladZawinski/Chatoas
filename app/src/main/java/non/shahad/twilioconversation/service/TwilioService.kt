package non.shahad.twilioconversation.service

import non.shahad.twilioconversation.service.model.*
import non.shahad.twilioconversation.service.request.LoginRequest
import non.shahad.twilioconversation.service.request.RegisterRequest
import non.shahad.twilioconversation.service.request.SendMessageRequest
import retrofit2.http.*

interface TwilioService {

    @GET("chat/conversations")
    suspend fun fetchConversations(): List<Conversation>

    /**
     * Default limit is 20 already
     */
    @GET("chat/messages")
    suspend fun fetchMessages(
        @Query("sid") sid: String
    ): List<Message>

    @POST("chat/connect")
    suspend fun createChatRoom(
        @Query("receiverId") with: String
    ): ChatRoomResponse

    /**
     * Currently only text messages are supported
     */
    @POST("chat/send")
    suspend fun sendMessage(
        @Body body: SendMessageRequest
    )

    @POST("user/login")
    suspend fun login(
        @Body body: LoginRequest
    ): AuthResponse

    @POST("user/register")
    suspend fun register(
        @Body body: RegisterRequest
    ): AuthResponse

    @GET("list/users")
    suspend fun getAvailableUser() : List<User>
}