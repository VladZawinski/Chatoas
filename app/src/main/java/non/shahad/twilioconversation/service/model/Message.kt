package non.shahad.twilioconversation.service.model

data class Message(
    val sId: String,
    val type: String,
    val body: String
)

data class Sender(
    val id: String,
    val name: String
)