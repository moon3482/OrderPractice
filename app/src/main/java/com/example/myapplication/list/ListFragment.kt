package com.example.myapplication.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.detail.DetailFragment
import com.example.myapplication.intro.IntroFragment
import com.example.myapplication.list.adapter.ListAdapter
import com.example.myapplication.model.ListMenu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), ListUiEvent {
    private var _binding: FragmentListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentListBinding is Null"
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
        with(binding) {
            lifecycleOwner = this@ListFragment
            val menuList = getMenuList()
            val listAdapter = ListAdapter(menuList).apply {
                setOnClickMenu(this@ListFragment::onClickMenu)
            }
            listRecyclerView.adapter = listAdapter
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        parentFragmentManager.commit {
            replace<IntroFragment>(
                containerViewId = R.id.fragmentContainerView,
            )
        }
    }

    override fun onClickMenu(listMenu: ListMenu?) {
        parentFragmentManager.commit {
            replace<DetailFragment>(
                containerViewId = R.id.fragmentContainerView,
                args = DetailFragment.arguments(listMenu)
            )
        }
    }
}