package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.example.myapplication.databinding.WidgetDetailBinding
import com.example.myapplication.model.IcePortion

@BindingMethods(
    value = [
        BindingMethod(
            type = DetailWidget::class,
            attribute = "bind:isHot",
            method = "setIsHot",
        ),
        BindingMethod(
            type = DetailWidget::class,
            attribute = "bind:isCaffeine",
            method = "setIsCaffeine",
        ),
        BindingMethod(
            type = DetailWidget::class,
            attribute = "bind:icePortion",
            method = "setIcePortion",
        )
    ]
)

class DetailWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding = WidgetDetailBinding.inflate(LayoutInflater.from(context), this, true)

    var isHot: Boolean? = null
        get() = binding.isHot
        set(value) {
            binding.isHot = value
            field = value
        }

    var isCaffeine: Boolean? = null
        get() = binding.isCaffeine
        set(value) {
            binding.isCaffeine = value
            field = value
        }

    var icePortion: IcePortion? = null
        get() = binding.icePortion
        set(value) {
            binding.icePortion = value
            field = value
        }
}