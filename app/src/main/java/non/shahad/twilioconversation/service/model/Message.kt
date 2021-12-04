package non.shahad.twilioconversation.service.model

data class Message(
    val sId: String,
    val sender: Sender,
    val body: String
)

data class Sender(
    val id: String,
    val name: String
)