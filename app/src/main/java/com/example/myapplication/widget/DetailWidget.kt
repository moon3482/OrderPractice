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
            tempHot.setOnClickListener {
                detailUiEvent?.onChangeTemp(true)
            }
            tempIce.setOnClickListener {
                detailUiEvent?.onChangeTemp(false)
            }
            caffeine.setOnClickListener {
                detailUiEvent?.onChangeCaffeine(true)
            }
            deCaffeine.setOnClickListener {
                detailUiEvent?.onChangeCaffeine(false)
            }
            iceSmall.setOnClickListener {
                detailUiEvent?.onChangeIcePortion(IcePortion.SMALL)
            }
            iceMedium.setOnClickListener {
                detailUiEvent?.onChangeIcePortion(IcePortion.MEDIUM)
            }
            iceLarge.setOnClickListener {
                detailUiEvent?.onChangeIcePortion(IcePortion.LARGE)
            }
        }
    }

    fun setOption(orderMenu: OrderMenu?) {
        orderMenu?.let { orderMenu ->
            with(binding) {
                this.orderMenu = orderMenu
                when (orderMenu) {
                    is OrderMenu.Coffee -> {
                        isShowTemp = true
                        isShowCaffeine = true
                        isShowIce = !orderMenu.isHot
                    }

                    is OrderMenu.Ade -> {
                        isShowTemp = false
                        isShowCaffeine = false
                        isShowIce = true
                    }

                    is OrderMenu.Tea -> {
                        isShowTemp = false
                        isShowCaffeine = true
                        isShowIce = false
                    }

                    is OrderMenu.Desert -> {
                        isShowTemp = false
                        isShowCaffeine = false
                        isShowIce = false
                    }
                }
                isSmallPortion = IcePortion.SMALL == orderMenu.icePortion
                isMediumPortion = IcePortion.MEDIUM == orderMenu.icePortion
                isLargePortion = IcePortion.LARGE == orderMenu.icePortion
            }
        }
    }

    fun setDetailUiEvent(detailUiEvent: DetailUiEvent?) {
        this.detailUiEvent = detailUiEvent
    }
}
