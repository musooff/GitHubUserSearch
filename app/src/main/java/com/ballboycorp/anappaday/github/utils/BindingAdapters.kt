package com.ballboycorp.anappaday.github.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ballboycorp.anappaday.github.R
import com.ballboycorp.anappaday.github.common.HorizontalSpaceDecoration
import com.ballboycorp.anappaday.github.common.VerticalSpaceDecoration
import com.ballboycorp.anappaday.github.utils.extensions.loadUrl

/**
 * Created by musooff on 2019-07-05.
 */

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:emptyItemDecorator")
    fun setEmptyItemDecorator(recyclerView: RecyclerView, isHorizontal: Boolean = false) {
        if (isHorizontal){
            recyclerView.addItemDecoration(HorizontalSpaceDecoration(recyclerView.context, R.dimen.default_item_margin))
        }
        else {
            recyclerView.addItemDecoration(VerticalSpaceDecoration(recyclerView.context, R.dimen.default_item_margin))
        }
    }

    @JvmStatic
    @BindingAdapter("app:url")
    fun setImageUrl(imageView: ImageView, url: String?) {
        imageView.loadUrl(url, R.color.colorPlaceHolder)
    }

    @JvmStatic
    @BindingAdapter("app:uri")
    fun setImageUrl(imageView: ImageView, drawableId: Int) {
        imageView.setImageResource(drawableId)
    }
}