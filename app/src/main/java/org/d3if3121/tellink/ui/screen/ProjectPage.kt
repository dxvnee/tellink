package org.d3if3121.tellink.ui.screen

import android.annotation.SuppressLint
import org.d3if3121.tellink.R
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3121.tellink.navigation.Screen
import org.d3if3121.tellink.ui.theme.TellinkTheme
import org.d3if3121.tellink.ui.theme.Warna
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ModifierInfo
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.d3if3121.tellink.navigation.BottomBarScreen
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardElevation



@Preview(showBackground = true)
@Composable
fun ProjectPagePreview() {
    ProjectPage(navController = rememberNavController())
}




@Composable
fun MainContentProject(
    lazyListState: LazyListState,
    paddingValues: PaddingValues
) {
    val numbers = remember { List(size = 200){ it } }
    val padding by animateDpAsState(
        targetValue = if (cekScroll(lazyListState)) 0.dp else TOP_BAR_HEIGHT,
        animationSpec = tween(
            durationMillis = 500,


        )
    )
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(start = 17.dp, end = 17.dp).background(color = Warna.PutihNormal)
    ) {

        LazyColumn(
            modifier = Modifier.padding(top = padding).fillMaxWidth().fillMaxHeight(),
            state = lazyListState
        ){
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "My Project",
                    color = Warna.MerahNormal,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.ExtraBold,

                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 17.dp)

                ){
                    InputPutihSearch(
                        input = search,
                        placeholder = stringResource(id = R.string.search),
                        onInputChange = { input ->
                            search = input
                        },
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier.width(297.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Button(
                            modifier = Modifier.size(49.dp).fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonColors(
                                containerColor =  Warna.MerahNormal,
                                contentColor = Warna.PutihNormal,
                                disabledContentColor = Warna.MerahNormal,
                                disabledContainerColor = Warna.PutihNormal
                            ),
                            onClick = {},
                        ) {
                            Text(
                                modifier = Modifier.offset(-5.dp),
                                text = "+",
                                color = Warna.PutihNormal,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }


                }

            }
            items(100){
                KartuKonten(
                    fotoprofil = R.drawable.photo,
                    nama = "Eigiya Daramuli Kale",
                    jurusan = "D3 Rekayasa Perangkat Lunak Aplikasi",
                    hari = "5 Days Ago",

                    judul = "MAU NANYA DONGG",
                    gambar = R.drawable.post1,
                    konten = "Ini Kenapa kodingan Java aku error " +
                            "ya guys, tolong bantuannya dong"
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }

}


@Composable
fun PilihanPutih(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 17.dp)
            .height(50.dp),

        colors = CardDefaults.cardColors(containerColor = Warna.PutihNormal),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Row (
            modifier = Modifier.padding(8.dp)
        ){
            Button(
                modifier = Modifier.size(50.dp).fillMaxWidth().weight(1f),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonColors(
                    containerColor =  Warna.MerahNormal,
                    contentColor = Warna.PutihNormal,
                    disabledContentColor = Warna.MerahNormal,
                    disabledContainerColor = Warna.PutihNormal
                ),
                onClick = {},
            ) {
                Text(
                    text = "Your Project",
                    color = Warna.PutihNormal,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Button(
                modifier = Modifier.size(50.dp).fillMaxWidth().weight(1f),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonColors(
                    containerColor =  Warna.PutihNormal,
                    contentColor = Warna.MerahNormal,
                    disabledContentColor = Warna.PutihNormal,
                    disabledContainerColor = Warna.MerahNormal
                ),
                onClick = {},
            ) {
                Text(
                    text = "Recommended",
                    color = Warna.MerahNormal,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                )
            }

        }
    }
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProjectPage(
    navController: NavHostController
) {
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopBar(lazyListState = lazyListState, helloActive = false, TOP_BAR_ZERO = 100)
        },
        content = { paddingValues ->
            MainContentProject(lazyListState = lazyListState, paddingValues = paddingValues)
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    )


}

