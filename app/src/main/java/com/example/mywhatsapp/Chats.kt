package com.example.mywhatsapp

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Chats(){
    val grupo = getContacto().groupBy { it.grupo }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        grupo.forEach { (publisher, myContacto) ->
            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray), fontSize = 16.sp)
            }
            items(myContacto) { lazy ->
                ItemsContacto(contacto = lazy)
            }


        }
    }
}

data class Contacto(var grupo:String, var nombre :String, @DrawableRes var image :Int)

fun getContacto(): List<Contacto> {
    return listOf(
        Contacto(
            "Departamento Informatica",
            "Jefe de departamento",
            R.drawable.photo
        ),
        Contacto(
            "Departamento Informatica",
            "Tutora DAM",
            R.drawable.photo2
        ),
        Contacto(
            "Departamento Informatica",
            "Tutor DAW",
            R.drawable.photo3
        ),
        Contacto(
            "Departamento Informatica",
            "Tutor ASIX",
            R.drawable.photo4
        ),
        Contacto(
            "Comunidad Propietarios",
            "Presidenta",
            R.drawable.photo5
        ),
        Contacto(
            "Comunidad Propietarios",
            "Secretaria",
            R.drawable.photo6
        ),
        Contacto(
            "Comunidad Propietarios",
            "Administrador",
            R.drawable.photo7
        ),
        Contacto(
            "Gimnasio",
            "Entrenador",
            R.drawable.photo8
        ),
        Contacto(
            "Gimnasio",
            "Nutricionistas",
            R.drawable.photo9
        ),
        Contacto(
            "Gimnasio",
            "Fisioterapeuta",
            R.drawable.photo9
        ),

        )
}
@Composable
fun ItemsContacto(contacto: Contacto) {

        Row (modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 10.dp)
        ){
            Image(
                painter = painterResource(id = contacto.image),
                contentDescription = "Coffee",
                modifier= Modifier
                    .size(65.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Row( modifier= Modifier
                .fillMaxWidth().padding(top = 20.dp, start = 10.dp),
                ) {
                Text(text = contacto.nombre,  fontSize = 15.sp)
            }

        }


}



