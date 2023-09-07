package com.example.myapplication.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.intro.IntroFragment
import com.example.myapplication.model.Event
import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.ListMenu
import com.example.myapplication.model.MenuType
import com.example.myapplication.model.OrderMenu
import com.example.myapplication.order.OrderFragment

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentDetailBinding is Null"
        }
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = this@DetailFragment
            vm = viewModel
            tempGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.tempHot -> {
                        viewModel.setTemp(true)
                        viewModel.setIcePortion(null)
                        viewModel.selectedListMenu.value?.let {
                            viewModel.setIsShowIce(false)
                        }
                    }

                    R.id.tempIce -> {
                        viewModel.setTemp(false)
                        viewModel.selectedListMenu.value?.let { listMenu ->
                            val isShowIce = listMenu.menuType == MenuType.COFFEE
                            viewModel.setIsShowIce(isShowIce)
                        }
                    }
                }
            }
            caffeineGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.caffeine -> viewModel.setCaffeine(true)
                    R.id.deCaffeine -> viewModel.setCaffeine(false)
                }
            }
            iceGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.iceSmall -> viewModel.setIcePortion(IcePortion.SMALL)
                    R.id.iceMedium -> viewModel.setIcePortion(IcePortion.MEDIUM)
                    R.id.iceLarge -> viewModel.setIcePortion(IcePortion.LARGE)
                }
            }
            sendOrder.setOnClickListener {
                viewModel.selectedListMenu.value?.let { listMenu ->
                    val validateList = mutableListOf<String>()
                    when (listMenu.menuType) {
                        MenuType.COFFEE -> {
                            if (viewModel.isHot.value == null)
                                validateList.add("온도")
                            if (viewModel.isCaffeine.value == null)
                                validateList.add("카페인")
                            if (viewModel.isHot.value == false && viewModel.icePortion.value == null)
                                validateList.add("얼음양")
                        }

                        MenuType.ADE -> {
                            if (viewModel.icePortion.value == null)
                                validateList.add("얼음양")
                        }

                        MenuType.TEA -> {
                            if (viewModel.isCaffeine.value == null)
                                validateList.add("카페인")
                        }

                        MenuType.DESERT -> Unit
                    }
                    if (validateList.isNotEmpty()) {
                        Toast.makeText(
                            requireContext(),
                            "${validateList.joinToString(",")}을(를) 선택해 주세요",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@let
                    }
                    when (listMenu.menuType) {
                        MenuType.TEA -> viewModel.setTemp(true)
                        MenuType.ADE -> viewModel.setTemp(false)
                        else -> Unit
                    }
                    val orderMenu = OrderMenu(
                        name = listMenu.name,
                        price = listMenu.price,
                        isHot = viewModel.isHot.value,
                        isCaffeine = viewModel.isCaffeine.value,
                        icePortion = viewModel.icePortion.value,
                    )
                    parentFragmentManager.commit {
                        replace<OrderFragment>(
                            containerViewId = R.id.fragmentContainerView,
                            args = OrderFragment.arguments(orderMenu)
                        ).addToBackStack(null)
                    }
                }
            }
            detailToolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        viewModel.event.observe(viewLifecycleOwner) { event ->
            when (event) {
                Event.ERROR -> {
                    Toast.makeText(requireContext(), "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack(
                        null,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                    parentFragmentManager.commit {
                        replace<IntroFragment>(R.id.fragmentContainerView)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun arguments(listMenu: ListMenu): Bundle = Bundle().apply {
            putSerializable("menu", listMenu)
        }
    }
}
