package com.example.myapplication.order

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.myapplication.FragmentName
import com.example.myapplication.databinding.FragmentOrderBinding
import com.example.myapplication.model.Event
import com.example.myapplication.model.OrderMenu
import com.example.myapplication.util.toKRWString
import com.example.myapplication.util.toOptionString

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentOrderBinding is Null"
        }
    private val viewModel: OrderViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToIntro()
            }
        }
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(
                owner = this,
                onBackPressedCallback = backPressedCallback,
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentOrderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.clearEvent()
        viewModel.setOrderedMenu()
        with(binding) {
            menuName.text = viewModel.orderedMenuName
            manuPrice.text = viewModel.orderedMenuPrice?.toKRWString()
            menuOption.text = viewModel.orderedMenuOption
            orderToolbar.setNavigationOnClickListener {
                navigateToIntro()
            }
            close.setOnClickListener {
                navigateToIntro()
            }
        }
        viewModel.event.observe(viewLifecycleOwner) { event ->
            when (event) {
                Event.ERROR -> {
                    Toast.makeText(requireContext(), "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                    navigateToIntro()
                }

                else -> Unit
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToIntro() {
        parentFragmentManager.popBackStack(
            FragmentName.INTRO,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    companion object {
        fun arguments(orderMenu: OrderMenu): Bundle = Bundle().apply {
            putString("menuName", orderMenu.name)
            putInt("menuPrice", orderMenu.price)
            putString("menuOption", orderMenu.toOptionString())
        }
    }
}
