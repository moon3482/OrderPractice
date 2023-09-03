package com.example.myapplication.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrderBinding
import com.example.myapplication.intro.IntroFragment
import com.example.myapplication.model.OrderMenu
import com.example.myapplication.util.toKRWString
import com.example.myapplication.util.toOptionString

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentOrderBinding is Null"
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentOrderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val name = arguments?.getString("menuName")
            val price = arguments?.getInt("menuPrice")
            val option = arguments?.getString("menuOption")
            menuName.text = name
            manuPrice.text = price?.toKRWString()
            menuOption.text = option
            orderToolbar.setNavigationOnClickListener {
                moveToIntro()
            }
            close.setOnClickListener {
                moveToIntro()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun moveToIntro() {
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