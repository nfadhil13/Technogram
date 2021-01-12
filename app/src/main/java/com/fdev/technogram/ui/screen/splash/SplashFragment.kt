package com.fdev.technogram.ui.screen.splash

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.imageResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fdev.technogram.R
import com.fdev.technogram.ui.ActivityViewModel
import com.fdev.technogram.ui.theme.TechnogramTheme
import kotlinx.coroutines.CoroutineScope

class SplashFragment : Fragment() {


    private val splashViewModel: SplashViewModel by viewModels()
    private val activityViewModel : ActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TechnogramTheme(
                    darkTheme = activityViewModel.darkTheme
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Box(

                        ) {
                            Image(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxSize(0.45f),
                                bitmap = imageResource(if(!activityViewModel.darkTheme) R.drawable.technogram_logo else R.drawable.technogram_logo_dark)
                            )
                        }
                    }
                }

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashViewModel.isSplashDone.observe(viewLifecycleOwner, { isSplashDone ->
            if (isSplashDone) {
                navToMain()
            }
        })
    }

    private fun navToMain() {
        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
    }

}