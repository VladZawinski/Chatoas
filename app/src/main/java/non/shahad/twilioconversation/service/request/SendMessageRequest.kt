package non.shahad.twilioconversation.service.request

data class SendMessageRequest(
    val sid: String,
    val body: String
)