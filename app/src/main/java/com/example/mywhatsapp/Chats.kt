package com.example.mywhatsapp

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywhatsapp.ui.theme.Purple40
import com.example.mywhatsapp.ui.theme.PurpleGrey80

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Chats(){

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
        }

        else -> {
            Scaffold (topBar = { MyTopAppBar()}){

                Box (modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())){
                    MyTabs()
                }

            }

                /*Column (modifier= Modifier.padding(top = 60.dp)){
                    LazyColumn {
                        items(getCardCoffee()) { lazy ->
                            ItemsCoffe(cardCoffee = lazy)
                        }
                    }
                }*/



        }
    }

}
data class CardCoffee(var name:String, var subbit:String, @DrawableRes var image :Int)

//fun getCardCoffee(): List<CardCoffee> {
//    return listOf(
//        CardCoffee(
//            "Antico Caffè Greco",
//            "St.Italy,Rome",
//            R.drawable.images
//        ),
//        CardCoffee(
//            "Coffee Room",
//            "St. Germany, Berlin",
//            R.drawable.images1
//        ),
//        CardCoffee(
//            "Coffee Ibiza",
//            "St. Colon,Madrid",
//            R.drawable.images2
//        ),
//        CardCoffee(
//            "Pudding Coffee Shop",
//            "St. Diagonal, Barcelona",
//            R.drawable.images3
//        ),
//        CardCoffee(
//            "L'Express",
//            "St. Picadilly, London",
//            R.drawable.images4
//        ),
//        CardCoffee(
//            "Coffee Ibiza",
//            "St.Àngel Guimerá, Valencia",
//            R.drawable.images5
//        ),
//        CardCoffee(
//            "Sweet Cup",
//            "St. Kinkerstraat, Amsterdam",
//            R.drawable.images6
//        ),
//
//        )
//}
@Composable
fun ItemsCoffe(cardCoffee: CardCoffee) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = cardCoffee.image),
                contentDescription = "Coffee",
                modifier= Modifier
                    .fillMaxWidth()
                    .size(200.dp),
                contentScale = ContentScale.Crop
            )
            Row( modifier= Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(text = cardCoffee.name,  fontSize = 30.sp)
            }

            Spacer(modifier = Modifier.size(10.dp))

            RatingBar()
            Spacer(modifier = Modifier.size(10.dp))
            Row( modifier= Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)) {
                Text(text = cardCoffee.subbit)
            }
            Spacer(modifier = Modifier.size(10.dp))
            Divider()
            TextButton(onClick = { /*TODO*/ },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color.Transparent, contentColor = Purple40
                )) {
                Text(text = "RESERVE")

            }
        }
    }

}
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    stars: Int = 5,
    starsColor: Color = Purple40
) {
    var starStates by remember { mutableStateOf(List(stars) { false }) }


    val onStarClick: (Int) -> Unit = { starIndex ->
        starStates = starStates.mapIndexed { index, _ ->
            index <= starIndex
        }
    }


    Row(modifier = modifier) {
        starStates.forEachIndexed { index, isFilled ->
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = if (isFilled) starsColor else PurpleGrey80,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable { onStarClick(index) }
            )
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