package non.shahad.twilioconversation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.orbitmvi.orbit.viewmodel.observe

abstract class OrbitMVIFragment<V: ViewDataBinding,VM: OrbitMVIViewModel<VS,SE>,VS: Any,SE: Any>: Fragment(){

    @get:LayoutRes
    abstract val layoutRes: Int
    lateinit var viewBinding: V
    abstract val viewModel: VM

    abstract fun handleSideEffect(sideEffect: SE)
    abstract fun render(state: VS)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater,layoutRes,container,false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            sideEffect = ::handleSideEffect,
            state = ::render
        )
    }
}