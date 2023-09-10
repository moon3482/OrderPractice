package com.example.myapplication.presentation.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentIntroBinding
import com.example.myapplication.presentation.ui.FragmentName
import com.example.myapplication.presentation.ui.list.ListFragment


class IntroFragment : Fragment() {
    private var _binding: FragmentIntroBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentIntroBinding is Null"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentIntroBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toListPage.setOnClickListener {
                parentFragmentManager.commit {
                    add<ListFragment>(
                        containerViewId = R.id.fragmentContainerView,
                    ).addToBackStack(FragmentName.INTRO)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
