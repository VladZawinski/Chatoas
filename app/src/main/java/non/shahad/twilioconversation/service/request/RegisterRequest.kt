package non.shahad.twilioconversation.service.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)