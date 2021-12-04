package non.shahad.twilioconversation.screens.main.conversations

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import non.shahad.twilioconversation.base.OrbitMVIViewModel
import non.shahad.twilioconversation.service.model.Conversation
import non.shahad.twilioconversation.service.repository.ConversationRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val repository: ConversationRepository
): OrbitMVIViewModel<ConversationState,ConversationSideEffect>() {

    override val container: Container<ConversationState, ConversationSideEffect>
        = container(ConversationState()) {
            fetch()
    }

    private fun fetch() = intent {
        postSideEffect(ConversationSideEffect.Loading)
        try {
            val result = repository.fetch()
            reduce {
                state.copy(conversations = result)
            }
        } catch (e: Throwable){
            postSideEffect(ConversationSideEffect.ShowError(e.message!!))
        }
    }

}

data class ConversationState(
    val conversations: List<Conversation> = emptyList()
)

sealed class ConversationSideEffect {
    object Loading: ConversationSideEffect()
    data class ShowError(val message: String): ConversationSideEffect()
}