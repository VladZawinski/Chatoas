package non.shahad.twilioconversation.service.repository

import non.shahad.twilioconversation.service.TwilioService
import javax.inject.Inject

/**
 * Just a bridge, no more..
 */
class UserRepository @Inject constructor(
    private val service: TwilioService
){
    suspend fun fetchAvailableUser() = service.getAvailableUser()

    suspend fun connect(userId: String) = service.createChatRoom(userId)
}