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
        with(binding) {
            orderMenu?.let { orderMenu ->
                this.orderMenu = orderMenu
                isShowTemp = when (orderMenu) {
                    is OrderMenu.Coffee -> true
                    is OrderMenu.Ade,
                    is OrderMenu.Tea,
                    is OrderMenu.Desert -> false
                }
                isShowCaffeine = when (orderMenu) {
                    is OrderMenu.Coffee,
                    is OrderMenu.Tea -> true

                    is OrderMenu.Ade,
                    is OrderMenu.Desert -> false
                }
                isShowIce = when (orderMenu) {
                    is OrderMenu.Coffee -> {
                        !orderMenu.isHot
                    }

                    is OrderMenu.Ade -> true
                    is OrderMenu.Tea,
                    is OrderMenu.Desert -> false
                }
            }
        }
    }

    fun setDetailUiEvent(detailUiEvent: DetailUiEvent?) {
        this.detailUiEvent = detailUiEvent
    }
}
