package non.shahad.twilioconversation.service.model

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "_id")
    val id: String,
    val name: String
)