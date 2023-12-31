package com.example.myapplication.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentIntroBinding
import com.example.myapplication.list.ListFragment


class IntroFragment : Fragment(), IntroUiEvent {
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
            lifecycleOwner = this@IntroFragment
            uiEvent = this@IntroFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickNext() {
        parentFragmentManager.commit {
            replace<ListFragment>(
                containerViewId = R.id.fragmentContainerView,
            ).addToBackStack(null)
        }
    }
}
