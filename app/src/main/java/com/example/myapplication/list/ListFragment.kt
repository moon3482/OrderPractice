package com.example.myapplication.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.detail.DetailFragment
import com.example.myapplication.list.adapter.MenuGroupAdapter
import com.example.myapplication.model.ListMenu


class ListFragment : Fragment(), ListUiEvent {
    private var _binding: FragmentListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentListBinding is Null"
        }
    private val viewModel: ListViewModel by viewModels()

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
            uiEvent = this@ListFragment
            menuGroupListRecyclerView.adapter = MenuGroupAdapter()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickMenu(listMenu: ListMenu) {
        parentFragmentManager.commit {
            replace<DetailFragment>(
                containerViewId = R.id.fragmentContainerView,
                args = DetailFragment.arguments(listMenu)
            ).addToBackStack(null)
        }
    }

    override fun onClickBack() {
        requireActivity().onBackPressed()
    }
}
