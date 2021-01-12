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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.fdev.technogram.R
import com.fdev.technogram.databinding.FragmentMainBinding
import com.fdev.technogram.ui.ActivityViewModel
import com.fdev.technogram.ui.theme.TechnogramTheme
import com.fdev.technogram.ui.components.TechnogramDrawer
import com.fdev.technogram.ui.components.TechnogramTopAppBar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Error



@AndroidEntryPoint
class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null

    private val binding
        get() = _binding!!


    private val mainViewModel : MainViewModel by viewModels()
    private val activityViewModel : ActivityViewModel by activityViewModels()

    lateinit var childNavController : NavController


    private val navigations = listOf(
            MainNavigation.Home,
            MainNavigation.Search,
            MainNavigation.SearchWithQuery("App", R.drawable.ic_app),
            MainNavigation.SearchWithQuery("Software", R.drawable.ic_software),
            MainNavigation.SearchWithQuery("Hardware", R.drawable.ic_hardware),
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
                TechnogramTheme(
                        darkTheme = activityViewModel.darkTheme
                ){
                    TechnogramTopAppBar(
                            onBurgerClicked = { binding.root.open() },
                            onSearchClicked = {
                                mainViewModel.currentSelected = 1
                                onDrawerNavigate(MainNavigation.Search)
                            },
                            darkTheme = activityViewModel.darkTheme
                    )
                }
            }
            this.navigationDrawerContent.setContent {
                TechnogramTheme(
                        darkTheme = activityViewModel.darkTheme
                ){
                    TechnogramDrawer(
                            modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                            isLoggedIn = false,
                            onSignInClicked = { /*TODO*/ },
                            navigations = navigations,
                            onNavigationItemClicked = { selectedMenu ->
                                mainViewModel.currentSelected = selectedMenu
                                onDrawerNavigate(navigations[selectedMenu])
                            },
                            selectedItem = mainViewModel.currentSelected,
                            darkTheme = activityViewModel.darkTheme,
                            onToogle = { activityViewModel.toogleTheme()}

                    )
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.findFragmentById(R.id.main_nav_host_fragment)?.let{ childFragment ->
            childNavController = childFragment.findNavController()
        }?:throw Error("No child fragment found")
    }


    private fun onDrawerNavigate(navigation: MainNavigation) {
        when(navigation) {
            is MainNavigation.Home -> {
                childNavController.navigate(R.id.homeFragment)
            }
            is MainNavigation.SearchWithQuery -> {
                childNavController.navigate(R.id.searchFragment , bundleOf(MainBundleConst.SEARCH_QUERY_BUNDLE to navigation.query))
            }
            is MainNavigation.More -> {
                childNavController.navigate(R.id.categoriesFragment)
            }
            is MainNavigation.Search -> {
                childNavController.navigate(R.id.searchFragment)
            }
        }
        binding.root.closeDrawers()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}