package non.shahad.twilioconversation.service.repository

import non.shahad.twilioconversation.service.TwilioService
import non.shahad.twilioconversation.service.request.SendMessageRequest
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val service: TwilioService
) {
    suspend fun fetchMessages(conversationId: String) = service.fetchMessages(conversationId)

    suspend fun sendMessage(message: String,sId: String) = service.sendMessage(SendMessageRequest(sId,message))
}