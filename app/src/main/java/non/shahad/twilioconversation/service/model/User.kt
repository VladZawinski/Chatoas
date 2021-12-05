package non.shahad.twilioconversation.service.model

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "_id")
    val id: String,
    val name: String,
    @field:Json(name = "isAlreadyConnected")
    val isAlreadyConnected: Boolean,
    @field:Json(name = "conversationId")
    val conversationId: String?,
    @field:Json(name = "chatServiceSid")
    val chatServiceSid: String?
)