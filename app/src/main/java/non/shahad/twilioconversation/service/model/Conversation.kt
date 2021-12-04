package non.shahad.twilioconversation.service.model

import com.squareup.moshi.Json

data class Conversation(
    val sid: String,
    val peer: Participant
)

data class Participant(
    @field:Json(name = "_id")
    val id: String,
    @field:Json(name = "name")
    val name: String
)