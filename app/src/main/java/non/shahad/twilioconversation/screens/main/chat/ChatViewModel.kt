package non.shahad.twilioconversation.screens.main.chat

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import non.shahad.twilioconversation.base.OrbitMVIViewModel
import non.shahad.twilioconversation.service.model.Message
import non.shahad.twilioconversation.service.repository.ChatRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repo: ChatRepository
): OrbitMVIViewModel<ChatState,ChatSideEffect>(){

    override val container: Container<ChatState, ChatSideEffect>
        = container(ChatState())


    fun fetchMessages(conversationId: String) = intent {
        postSideEffect(ChatSideEffect.Pooling)
        try {
            while (true){
                if (!state.isSendingMessage){
                    val result = repo.fetchMessages(conversationId)
                    reduce {
                        state.copy(messages = result, conversationId = conversationId)
                    }
                }
                delay(2000)
            }
        }catch (e: Throwable){

        }
    }

    private fun fakeAddBubble(message: String) = intent {
        val currentList = mutableListOf<Message>().also {
            it.addAll(state.messages)
            it.add(0,Message("","outgoing",message))
        }

        reduce {
            state.copy(messages = currentList)
        }
    }

    fun sendMessage(message: String) = intent {
        try {
            reduce {
                state.copy(isSendingMessage = true)
            }
            fakeAddBubble(message)
            repo.sendMessage(message,state.conversationId)
            reduce {
                state.copy(isSendingMessage = false)
            }
//            fetchMessages(state.conversationId)
        }catch (e: Throwable){
            Timber.d(e)
        }
    }

}

data class ChatState(
    val conversationId: String = "",
    val messages: List<Message> = emptyList(),
    val isSendingMessage: Boolean = false
)

sealed class ChatSideEffect {
    object Pooling: ChatSideEffect()
    object MessageSent: ChatSideEffect()
}