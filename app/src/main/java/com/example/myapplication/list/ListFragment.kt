package com.example.myapplication.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.intro.IntroFragment
import com.example.myapplication.list.adapter.ListAdapter
import com.example.myapplication.model.ListMenu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentListBinding is Null"
        }
    private lateinit var viewModel: ListViewModel
    private lateinit var backPressedCallback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                goToIntro()
            }
        }
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(
                this,
                backPressedCallback,
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        with(binding) {
            lifecycleOwner = this@ListFragment
            val menuList = getMenuList()
            val listAdapter = ListAdapter(menuList)
            listRecyclerView.adapter = listAdapter
            toolbar.setNavigationOnClickListener {
                goToIntro()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        backPressedCallback.remove()
    }

    private fun getMenuList(): List<ListMenu?> {
        return mutableListOf(
            ListMenu.Coffee("아메리카노", 1000),
            ListMenu.Coffee("카페라떼", 1500),
            ListMenu.Coffee("카푸치노", 2000),
            null,
            ListMenu.Ade("오렌지에이드", 2500),
            ListMenu.Ade("망고에이드", 2500),
            null,
            ListMenu.Tea("얼그레이티", 1000),
            ListMenu.Tea("페퍼민트티", 1000),
            null,
            ListMenu.Desert("치즈케이크", 3000),
            ListMenu.Desert("초코케이크", 3000),
            ListMenu.Desert("마들렌", 1000),
            ListMenu.Desert("휘낭시에", 1500),
        )
    }

    private fun goToIntro() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                IntroFragment.newInstance(),
            ).commit()
    }

    companion object {
        fun newInstance() = ListFragment()
    }


}