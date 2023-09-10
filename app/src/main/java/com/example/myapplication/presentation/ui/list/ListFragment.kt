package com.example.myapplication.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.domain.GetMenuListUsecase
import com.example.myapplication.presentation.model.ListMenu
import com.example.myapplication.presentation.ui.FragmentName
import com.example.myapplication.presentation.ui.detail.DetailFragment
import com.example.myapplication.presentation.ui.list.adapter.ListMenuAdapter


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentListBinding is Null"
        }
    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory(GetMenuListUsecase())
    }
    private val itemClick: (ListMenu) -> Unit = { listMenu ->
        parentFragmentManager.commit {
            add<DetailFragment>(
                containerViewId = R.id.fragmentContainerView,
                args = DetailFragment.arguments(listMenu)
            ).addToBackStack(FragmentName.LIST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
            vm = viewModel
            menuGroupListRecyclerView.adapter = ListMenuAdapter().apply {
                setOnClickMenu(itemClick)
            }
            listToolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
