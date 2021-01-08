package com.fdev.technogram.ui.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.fdev.technogram.R
import com.fdev.technogram.databinding.FragmentMainBinding
import com.fdev.technogram.ui.TechnogramTheme
import com.fdev.technogram.ui.components.TechnogramDrawer
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import com.fdev.technogram.util.produceFakeNewsData
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Error



@AndroidEntryPoint
class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null

    private val binding
        get() = _binding!!


    private val mainViewModel : MainViewModel by viewModels()

    lateinit var childNavController : NavController

    lateinit var childFragment : Fragment

    private val navigations = listOf(
            MainNavigation.Home,
            MainNavigation.Search("App", R.drawable.ic_app),
            MainNavigation.Search("Software", R.drawable.ic_software),
            MainNavigation.Search("Hardware", R.drawable.ic_hardware),
            MainNavigation.More
    )



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        settinUpComposeView()
        val view = binding.root
        return view
    }


    private fun settinUpComposeView() {
        binding.apply {
            this.mainToolbarContent.setContent {
                TechnogramTheme{
                    TechnogramTopAppBar(
                            onBurgerClicked = { binding.root.open() },
                            onSearchClicked = { binding.root.open() }
                    )
                }
            }
            this.navigationDrawerContent.setContent {
                TechnogramTheme{
                    TechnogramDrawer(
                            modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                            isLoggedIn = false,
                            onSignInClicked = { /*TODO*/ },
                            onSearch = { /*TODO*/ },
                            searchKey = "",
                            onValueChange = { /*TODO*/ },
                            navigations = navigations,
                            onNavigationItemClicked = { selectedMenu ->
                                mainViewModel.currentSelected = selectedMenu
                                onDrawerNavigate(navigations[selectedMenu])
                            },
                            selectedItem = mainViewModel.currentSelected

                    )
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.findFragmentById(R.id.main_nav_host_fragment)?.let{ childFragment ->
            childNavController = childFragment.findNavController()
            this.childFragment = childFragment
        }?:throw Error("No child fragment found")
    }


    private fun onDrawerNavigate(navigation: MainNavigation) {
        when(navigation) {
            is MainNavigation.Home -> {
                childNavController.navigate(R.id.homeFragment)
            }
            is MainNavigation.Search -> {
                childNavController.navigate(R.id.newsDetailFragment , bundleOf(MainBundleConst.HOME_TO_NEWSDETAIL_NEWS_BUNDLE to produceFakeNewsData()))
            }
            is MainNavigation.More -> {
                println("go to more")
            }
        }
        binding.root.closeDrawers()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}