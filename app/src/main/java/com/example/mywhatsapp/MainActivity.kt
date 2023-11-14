package com.example.mywhatsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mywhatsapp.ui.theme.MyWhatsAppTheme
import com.example.mywhatsapp.ui.theme.Purple40
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWhatsAppTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    rememberTopAppBarState()
                )
                // A surface container using the 'background' color from the theme
                Scaffold (
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = { MyTopAppBar(scrollBehavior)},
                    floatingActionButton = { floatingButtom() }
                )

                {

                    Box (modifier = Modifier
                        .fillMaxSize()
                        .padding(top = it.calculateTopPadding())){
                        MyTabs()
                    }


                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(scrollBehavior: TopAppBarScrollBehavior){
    var isMenuVisible by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(text = "MyWhatsApp", color = Color.White, fontSize = 20.sp)
        },
        actions =  {
            Row (){
                IconButton(
                    onClick = {
                        isMenuVisible = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Row (){
                IconButton(
                    onClick = {
                        isMenuVisible = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Purple40),
        scrollBehavior = scrollBehavior
    )
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabs(){
    var pagerState = rememberPagerState(initialPage = 0,
        initialPageOffsetFraction = 0f){3} //variable a la que iniciamos
    var scope = rememberCoroutineScope() //necesario tmb para tab a tab
    val titles = listOf("Chats", "Novedades", "Llamadas")
    Column {
        TabRow(selectedTabIndex = pagerState.currentPage /*para pasar de tab a tab*/) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(page =index) } },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }

        //necesario para navegar de tab a tab
        HorizontalPager( state = pagerState)
        { page ->
                    when(page){
                        0 -> Chats()
                        1 -> Novedades()
                        2 -> Llamadas()
                    }
        }

    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun floatingButtom(){
    val image =
        AnimatedImageVector.animatedVectorResource(R.drawable.add_rotation
        )
    var atEnd by remember { mutableStateOf(false) }
    FloatingActionButton(onClick = {},
        containerColor = Color(0xFF027A58)
    ) {

        Image(
            painter = rememberAnimatedVectorPainter(image, atEnd),
            contentDescription = "VectorDrawable",
            modifier = Modifier.clickable {
                atEnd = !atEnd
            },
            contentScale = ContentScale.Crop,
        )
    }
}