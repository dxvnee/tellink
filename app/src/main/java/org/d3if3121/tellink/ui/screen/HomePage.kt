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
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardElevation
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ObserveScrollState(lazyListState: LazyListState) : Boolean{
    var isScrolledFar by remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemScrollOffset > 28 }
            .distinctUntilChanged()
            .debounce(160)
            .collect { newIsScrolledFar ->
                isScrolledFar = newIsScrolledFar
            }
    }

   return isScrolledFar
}

@Composable
fun cekScroll(lazyListState: LazyListState) : Boolean{
    var isScrolled by remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex > 0 }
            .distinctUntilChanged()
            .debounce(80)
            .collect {
                isScrolled = it
            }
    }
    return isScrolled
}

@Composable
fun ScrollDirectionDetector(lazyListState: LazyListState): String {
    var previousIndex by remember { mutableStateOf(lazyListState.firstVisibleItemIndex) }
    var scrollDirection by remember { mutableStateOf("Idle") }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .collect { currentIndex ->
                scrollDirection = when {
                    currentIndex > previousIndex -> "Down"
                    currentIndex < previousIndex -> "Up"
                    else -> "Idle"
                }
                previousIndex = currentIndex
            }
    }

    return scrollDirection
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(navController = rememberNavController())
}


val TOP_BAR_HEIGHT = 100.dp

@Composable
fun TombolGambar(
    painter: Painter,
    size: Int,
    onClick: () -> Unit,
){
    IconButton(onClick = onClick) {
        Image(
            painter = painter,
            contentDescription = "Chat logo",
            modifier = Modifier.size(size.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    lazyListState: LazyListState,
    helloActive: Boolean,
    profileActive: Boolean = false,
    TOP_BAR_ZERO: Int = 0,
    search: String = "",
    onSearchChange: (String) -> Unit = {},
    navController: NavHostController = rememberNavController()
){
    if (profileActive == true){

        TopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Column (
                        horizontalAlignment = Alignment.Start,
                    ){
                        IconButton(
                            onClick = {},
                            modifier = Modifier.offset(x = -12.dp)
                        ){
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "eheh",
                                tint = Warna.AbuTua
                            )
                        }

                    }
                    Column (

                    ){
                        InputPutihSearchProfile(
                            input = search,
                            placeholder = stringResource(id = R.string.search),
                            onInputChange = onSearchChange,
                            keyboardType = KeyboardType.Text,
                            modifier = Modifier.fillMaxWidth()
                                .padding( end = 17.dp).height(40.dp),
                            fontSize = 15

                        )
                    }


                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Warna.PutihNormal,
                titleContentColor = Warna.PutihNormal
            ),
            modifier = Modifier
                .background(color = Warna.PutihNormal).padding(top = 0.dp)
                .height(height = 100.dp)
        )
    } else {
        TopAppBar(
            title = {
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column (
                            modifier = Modifier
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.photo),
                                contentDescription = "App logo",
                                modifier = Modifier
                                    .size(45.dp)
                            )
                        }

                        Column (

                        ){
                            if (helloActive == true) {
                                Text(
                                    text = "Hello,",
                                    color = Warna.MerahNormal,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.offset(y = 5.dp)
                                )
                                Text(
                                    text = "Eigiya Daramuli Kale",
                                    color = Warna.HitamNormal,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier.offset(y = -4.dp)
                                )
                            } else {
                                Text(
                                    text = "Eigiya Daramuli Kale",
                                    color = Warna.HitamNormal,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier.offset(y = 5.dp)
                                )
                                Text(
                                    text = "6706223121",
                                    color = Warna.AbuTua,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.offset(y = -4.dp)
                                )
                            }




                        }
                        Row (
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            TombolGambar(
                                painterResource(id = R.drawable.notifications),
                                26
                            ){

                            }
                            TombolGambar(
                                painterResource(id = R.drawable.chat),
                                26
                            ){
                                navController.navigate(Screen.Login.route)
                            }
                        }

                    }

                    Box(modifier = Modifier.fillMaxWidth().height(30.dp).background(Warna.MerahNormal).padding(10.dp))
                }

            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Warna.PutihNormal,
                titleContentColor = Warna.PutihNormal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Warna.PutihNormal)
                .animateContentSize(animationSpec =  tween(
                    durationMillis = 500,
                ))
                .height(height =
                if (cekScroll(lazyListState)){
                    TOP_BAR_ZERO.dp
                    if (ScrollDirectionDetector(lazyListState) == "Up" && ObserveScrollState(lazyListState)){
                        Log.d("cekFlow", ObserveScrollState(lazyListState).toString())
                        TOP_BAR_HEIGHT
                    } else {
                        TOP_BAR_ZERO.dp
                    }
                } else {
                    TOP_BAR_HEIGHT
                }
                ),
        )
    }


}

@Composable
fun MainContent(
    navController: NavHostController,
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
                    text = "Welcome to Tellink",
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
                        modifier = Modifier.fillMaxWidth()
                    )
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
fun KartuKonten(
    fotoprofil: Int,
    nama: String,
    jurusan: String,
    hari: String,

    judul: String,
    gambar: Int,
    konten: String,
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),

        colors = CardDefaults.cardColors(containerColor = Warna.PutihNormal),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(17.dp).fillMaxWidth().fillMaxHeight()
        ){
            Row (modifier = Modifier.padding(bottom = 17.dp).fillMaxWidth()){
                Column (
                    modifier = Modifier.padding(end = 10.dp),
                    verticalArrangement = Arrangement.Center,
                ){
                    Image(
                        painter = painterResource(id = fotoprofil),
                        contentDescription = "App logo",
                        modifier = Modifier
                            .size(52.dp)
                    )
                }

                Column {
                    Text(
                        text = nama,
                        color = Warna.MerahNormal,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Text(
                        text = jurusan,
                        color = Warna.AbuTua,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.offset(y = -3.dp)
                    )
                    Text(
                        text = hari,
                        color = Warna.AbuTua,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.offset(y = -8.dp)
                    )

                }

            }
            Text(
                text = judul,
                color = Warna.HitamNormal,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 9.dp, bottom = 5.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 17.dp)
            ){
                for (i in 1..3){
                    Card(
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .width(80.dp)
                            .height(30.dp),

                        colors = CardDefaults.cardColors(containerColor = Warna.PutihNormal),
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(1.dp, Warna.MerahNormal)
                    ){
                        Column (
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ){
                            Text(
                                text = "Android",
                                color = Warna.MerahNormal,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }
                }

            }



            Image(
                painter = painterResource(gambar),
                contentDescription = "Chat logo",
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )

            Text(
                text = konten,
                color = Warna.HitamNormal,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                textAlign = TextAlign.Justify
            )
            Column (
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxHeight()
            ) {
                ButtonMerah(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(46.dp),
                    content = {
                        Text(
                            text = stringResource(id = R.string.request),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = Warna.PutihNormal
                        )
                    }
                )
            }



        }





    }
}

@Composable
fun NumberHolder(number: Int){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = number.toString()
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    navController: NavHostController
) {
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopBar(lazyListState = lazyListState, helloActive = true, navController = navController)
        },
        content = { paddingValues ->
            MainContent(navController = navController, lazyListState = lazyListState, paddingValues = paddingValues)
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    )


}


@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.BottomMenuPage,
        BottomBarScreen.BottomSkillPage,
        BottomBarScreen.BottomProfilePage,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Warna.MerahTua,
        modifier = Modifier.height(70.dp)
    ){
        screens.forEach{ screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem (
        modifier = Modifier.padding(top = 10.dp, bottom = 36.dp),
        label = {
            Text(
                text = screen.title, color = Warna.PutihNormal,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        icon = {
            Icon(
                modifier = Modifier.size(65.dp)
                    .padding(bottom =1.dp),
                imageVector = screen.icon,
                contentDescription = "eheh",
                tint = Warna.PutihNormal
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }

    )


}