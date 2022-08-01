package com.example.movplayv3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.movplayv3.data.model.SnackBarEvent
import com.example.movplayv3.data.paging.ConfigDataSource
import com.example.movplayv3.ui.components.others.MovplayBottomBar
import com.example.movplayv3.ui.screens.NavGraphs
import com.example.movplayv3.ui.screens.destinations.FavoriteScreenDestination
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import com.example.movplayv3.ui.screens.destinations.SearchScreenDestination
import com.example.movplayv3.ui.screens.destinations.TvShowScreenDestination
import com.example.movplayv3.ui.theme.MovplayV3Theme
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.ImageUrlParser
import com.example.movplayv3.utils.safeNavigate
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

val LocalImageUrlParser = staticCompositionLocalOf<ImageUrlParser?> { null }

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var configDataSource: ConfigDataSource

    @SuppressLint("UnrememberedMutableState")
    @OptIn(
        ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class,
        ExperimentalMaterialNavigationApi::class, ExperimentalMaterial3Api::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val mainViewModel: MainViewModel = hiltViewModel(this)
            val lifeCycleOwner = LocalLifecycleOwner.current
            val keyboardController = LocalSoftwareKeyboardController.current
            val imageUrlParser by mainViewModel.imageUrlParser.collectAsState()
            val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
            val snackBarEvent: SnackBarEvent? by mainViewModel.networkSnackBarEvent.collectAsState()

            //val useDarkIcons = MaterialTheme.colors.isLight
            val navController = rememberAnimatedNavController()
            val navHostEngine = rememberAnimatedNavHostEngine(
                navHostContentAlignment = Alignment.TopCenter,
                rootDefaultAnimations = RootNavGraphDefaultAnimations(
                    enterTransition = { fadeIn(animationSpec = tween(500)) },
                    exitTransition = { fadeOut(animationSpec = tween(500)) }
                )
            )
            val systemUiController = rememberSystemUiController()
            var currentRoute: String? by rememberSaveable {
                mutableStateOf(null)
            }

            var backQueueRoutes: List<String?> by rememberSaveable {
                mutableStateOf(emptyList())
            }

            navController.apply {
                addOnDestinationChangedListener { controller, _, _ ->
                    currentRoute = controller.currentBackStackEntry?.destination?.route
                    backQueueRoutes = controller.backQueue.map { entry -> entry.destination.route }
                }
                addOnDestinationChangedListener { _, _, _ ->
                    keyboardController?.hide()
                }
            }

            val showBottomBar by derivedStateOf {
                currentRoute in setOf(
                    null,
                    MovieScreenDestination.route,
                    TvShowScreenDestination.route,
                    FavoriteScreenDestination.route,
                    SearchScreenDestination.route
                )
            }

            LaunchedEffect(snackBarEvent) {
                snackBarEvent?.let { event ->
                    snackBarHostState.showSnackbar(
                        message = getString(event.messageStringRes)
                    )
                }
            }
            LaunchedEffect(lifeCycleOwner) {
                lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    Timber.d("Update locale")

                    mainViewModel.updateLocale()
                }
            }

            CompositionLocalProvider(LocalImageUrlParser provides imageUrlParser) {
                MovplayV3Theme {
                    val navigationBarColor = MaterialTheme.colorScheme.surface
                    val experiment = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)

                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = experiment,
                            darkIcons = false
                        )

                        systemUiController.setNavigationBarColor(
                            color = navigationBarColor,
                            darkIcons = false
                        )
                    }
                    val snackbarHostState = remember { SnackbarHostState() }
                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        bottomBar = {
                            MovplayBottomBar(
                                currentRoute = currentRoute,
                                backQueueRoutes = backQueueRoutes,
                                visible = showBottomBar
                            ) { route ->
                                navController.safeNavigate(
                                    route = route,
                                    onSameRouteSelected = { sameRoute ->
                                        mainViewModel.onSameRouteSelected(sameRoute)
                                    }
                                )

                            }
                        }
                    ) { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    bottom = if (showBottomBar) {
                                        innerPadding.calculateBottomPadding()
                                    } else MaterialTheme.spacing.default
                                ),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            DestinationsNavHost(
                                navGraph = NavGraphs.root,
                                engine = navHostEngine,
                                navController = navController,
                                dependenciesContainerBuilder = {
                                    dependency(mainViewModel)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}