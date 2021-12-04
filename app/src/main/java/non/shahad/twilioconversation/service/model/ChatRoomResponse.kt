package non.shahad.twilioconversation.service.model

import com.squareup.moshi.Json

data class ChatRoomResponse(
    val message: String,
    @field:Json(name = "conversationId")
    val conversationId: String
)