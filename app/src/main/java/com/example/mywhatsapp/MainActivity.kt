package com.example.mywhatsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mywhatsapp.ui.theme.MyWhatsAppTheme
import com.example.mywhatsapp.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWhatsAppTheme {
                // A surface container using the 'background' color from the theme
                Scaffold (modifier = Modifier.fillMaxSize(), topBar = { MyTopAppBar()}){

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
fun MyTopAppBar(){
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
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Purple40)
    )
}
@Composable
fun MyTabs(){
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Chats", "Novedades", "Llamadas")
    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        when(state){
            0 -> Chats()
            1 -> Novedades()
            2 -> Llamadas()
        }
    }
}