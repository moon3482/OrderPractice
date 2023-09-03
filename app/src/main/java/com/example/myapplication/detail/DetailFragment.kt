package com.example.myapplication.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.ListMenu
import com.example.myapplication.model.MenuType
import com.example.myapplication.model.OrderMenu
import com.example.myapplication.order.OrderFragment

class DetailFragment : Fragment(), DetailUiEvent {
    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "FragmentDetailBinding is Null"
        }
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = this@DetailFragment
            vm = viewModel
            uiEvent = this@DetailFragment
            detailToolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        val listMenu = arguments?.getListMenu()
        if (listMenu != null)
            viewModel.setSelectedListMenu(listMenu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onChangeTemp(isHot: Boolean) {
        viewModel.setTemp(isHot)
    }

    override fun onChangeCaffeine(isCaffeine: Boolean) {
        viewModel.setCaffeine(isCaffeine)
    }

    override fun onChangeIcePortion(icePortion: IcePortion?) {
        viewModel.setIcePortion(icePortion)
    }

    override fun moveToOrder(orderMenu: OrderMenu) {
        parentFragmentManager.commit {
            replace<OrderFragment>(
                containerViewId = R.id.fragmentContainerView,
                args = OrderFragment.arguments(orderMenu)
            ).addToBackStack(null)
        }
    }

    companion object {
        fun arguments(listMenu: ListMenu): Bundle = Bundle().apply {
            putString("menuName", listMenu.name)
            putInt("menuPrice", listMenu.price)
            putString("menuType", listMenu.menuType.name)
        }

        private fun Bundle.getListMenu(): ListMenu? {
            val name = getString("menuName")
            val price = getInt("menuPrice")
            val menuType = getString("menuType")?.let { menuTypeName ->
                MenuType.entries.find { it.name == menuTypeName }
            }
            return if (name != null && menuType != null)
                ListMenu(name, price, menuType)
            else
                null
        }
    }
}