package non.shahad.twilioconversation.service.model


data class MessageUiModel(
    val sId: String,
    val type: String,
    val inOut: String,
    val image: String?,
    val body: String
)

data class Message(
    val sid: String,
    val type: String,
    val inOut: String,
    val image: String?,
    val body: String
) {

    fun toDomainModel() = MessageUiModel(
        sid,
        type = createType(),
        inOut, image, body
    )

    private fun createType(): String {
        return if (type == "image" && inOut == "incoming"){
            "incoming-image"
        }else if (type == "image" && inOut == "outgoing"){
            "outgoing-image"
        }else if (type == "text" && inOut == "incoming"){
            "incoming-text"
        }else if (type == "text" && inOut == "outgoing"){
            "outgoing-text"
        }else {
            ""
        }
    }
}

data class Sender(
    val id: String,
    val name: String
)