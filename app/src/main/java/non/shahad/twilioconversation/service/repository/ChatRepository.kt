package non.shahad.twilioconversation.service.repository

import non.shahad.twilioconversation.service.TwilioService
import non.shahad.twilioconversation.service.request.SendMessageRequest
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val service: TwilioService
) {
    suspend fun fetchMessages(conversationSid: String,chatServiceSid: String) = service.fetchMessages(conversationSid,chatServiceSid).map { it.toDomainModel() }

    suspend fun sendMessage(message: String,sId: String) = service.sendMessage(SendMessageRequest(sId,message))
}