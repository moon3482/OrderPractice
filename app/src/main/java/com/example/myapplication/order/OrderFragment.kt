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
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrderBinding
import com.example.myapplication.intro.IntroFragment
import com.example.myapplication.model.Event
import com.example.myapplication.model.OrderMenu
import com.example.myapplication.util.toOptionString

class OrderFragment : Fragment(){
    private var _binding: FragmentOrderBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentOrderBinding is Null"
        }
    private val viewModel: OrderViewModel by viewModels()
    private lateinit var backPressedCallback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToIntro()
            }
        }
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(backPressedCallback)
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
        with(binding) {
            lifecycleOwner = this@OrderFragment
            vm = viewModel
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
        backPressedCallback.remove()
    }

    private fun navigateToIntro() {
        parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        parentFragmentManager.commit {
            replace<IntroFragment>(
                containerViewId = R.id.fragmentContainerView,
            )
        }
    }

    companion object {
        fun arguments(orderMenu: OrderMenu): Bundle = Bundle().apply {
            putString("menuName", orderMenu.name)
            putInt("menuPrice", orderMenu.price)
            putString("menuOption", orderMenu.toOptionString())
        }
    }
}
