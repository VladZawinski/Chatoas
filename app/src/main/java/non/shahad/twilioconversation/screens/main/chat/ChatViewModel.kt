package non.shahad.twilioconversation.screens.main.chat

import dagger.hilt.android.lifecycle.HiltViewModel
import non.shahad.twilioconversation.base.OrbitMVIViewModel
import non.shahad.twilioconversation.service.model.Message
import non.shahad.twilioconversation.service.repository.ChatRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repo: ChatRepository
): OrbitMVIViewModel<ChatState,ChatSideEffect>(){

    override val container: Container<ChatState, ChatSideEffect>
        = container(ChatState())

    fun fetchMessages(conversationId: String) = intent {
        postSideEffect(ChatSideEffect.Preloading)
        try {
            val result = repo.fetchMessages(conversationId)
            reduce {
                state.copy(messages = result)
            }
        }catch (e: Throwable){

        }
    }

}

data class ChatState(
    val messages: List<Message> = emptyList()
)

sealed class ChatSideEffect {
    object Preloading: ChatSideEffect()
}