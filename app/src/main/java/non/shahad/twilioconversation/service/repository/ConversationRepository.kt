package non.shahad.twilioconversation.service.repository

import non.shahad.twilioconversation.service.TwilioService
import javax.inject.Inject

class ConversationRepository @Inject constructor(
    private val service: TwilioService
) {
    suspend fun fetch() = service.fetchConversations()
}