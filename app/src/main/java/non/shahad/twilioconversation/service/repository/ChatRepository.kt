package non.shahad.twilioconversation.service.repository

import non.shahad.twilioconversation.service.TwilioService
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val service: TwilioService
) {
    suspend fun fetchMessages(conversationId: String) = service.fetchMessages(conversationId)
}