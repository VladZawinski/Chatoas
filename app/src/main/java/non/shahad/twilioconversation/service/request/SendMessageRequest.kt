package non.shahad.twilioconversation.service.request

data class SendMessageRequest(
    val conversationSid: String,
    val body: String
)