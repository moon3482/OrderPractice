package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.example.myapplication.databinding.WidgetDetailBinding
import com.example.myapplication.detail.DetailUiEvent
import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.OrderMenu

@BindingMethods(
    value = [
        BindingMethod(
            type = DetailWidget::class,
            attribute = "bind:option",
            method = "setOption",
        ),
        BindingMethod(
            type = DetailWidget::class,
            attribute = "bind:uiEvent",
            method = "setDetailUiEvent",
        ),
    ]
)

class DetailWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding = WidgetDetailBinding.inflate(LayoutInflater.from(context), this, true)
    private var detailUiEvent: DetailUiEvent? = null

    init {
        with(binding) {
            tempGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    tempHot.id -> {
                        detailUiEvent?.onChangeTemp(true)
                        detailUiEvent?.onChangeIcePortion(null)
                    }

                    tempIce.id -> detailUiEvent?.onChangeTemp(false)
                }
            }
            caffeineGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    caffeine.id -> detailUiEvent?.onChangeCaffeine(true)
                    deCaffeine.id -> detailUiEvent?.onChangeCaffeine(false)
                }
            }
            iceGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    iceSmall.id -> detailUiEvent?.onChangeIcePortion(IcePortion.SMALL)
                    iceMedium.id -> detailUiEvent?.onChangeIcePortion(IcePortion.MEDIUM)
                    iceLarge.id -> detailUiEvent?.onChangeIcePortion(IcePortion.LARGE)
                }
            }
        }
    }

    fun setOption(orderMenu: OrderMenu) {
        with(binding) {
            isShowTemp = when (orderMenu) {
                is OrderMenu.Tea,
                is OrderMenu.Ade,
                is OrderMenu.Desert -> false

                else -> true
            }
            isShowCaffeine = orderMenu.isCaffeine != null
            isShowIce = when {
                orderMenu.isHot == true -> false
                orderMenu is OrderMenu.Tea -> false
                orderMenu is OrderMenu.Desert-> false
                else -> true
            }
            isHot = orderMenu.isHot == true
            isCaffeine = orderMenu.isCaffeine
            icePortion = orderMenu.icePortion
        }
    }

    fun setDetailUiEvent(detailUiEvent: DetailUiEvent) {
        this.detailUiEvent = detailUiEvent
    }
}