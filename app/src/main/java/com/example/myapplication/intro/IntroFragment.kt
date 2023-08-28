package com.example.myapplication.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentIntroBinding
import com.example.myapplication.list.ListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroFragment : Fragment() {
    private var _binding: FragmentIntroBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentIntroBinding is Null"
        }
    private lateinit var viewModel: IntroViewModel

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
        viewModel = ViewModelProvider(this)[IntroViewModel::class.java]
        with(binding) {
            lifecycleOwner = this@IntroFragment
            toListPage.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragmentContainerView,
                        ListFragment.newInstance(),
                    ).commit()
            }
        }
    }

    companion object {
        fun newInstance() = IntroFragment()
    }

}