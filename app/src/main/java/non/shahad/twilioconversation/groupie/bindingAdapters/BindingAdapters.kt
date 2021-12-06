package non.shahad.twilioconversation.groupie.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("bindImageView")
fun bindImageView(imageView: ImageView, url: String?){
    if (url != null){
        imageView.load(url)
    }
}