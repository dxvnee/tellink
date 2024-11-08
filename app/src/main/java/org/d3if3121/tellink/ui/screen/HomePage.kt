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
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

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
        snapshotFlow { lazyListState.firstVisibleItemIndex > 5 }
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
val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 5

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
fun TopBar(lazyListState: LazyListState){
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            ) {
                Column (
                    modifier = Modifier.padding(end = 10.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.photo),
                        contentDescription = "App logo",
                        modifier = Modifier
                            .size(45.dp)
                    )
                }

                Column (
                    modifier = Modifier.offset(y = -6.dp)
                ){
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
                }
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth().padding(end = 5.dp)
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

                    }
                }

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
                durationMillis = 100,
                delayMillis = 200,
                easing = LinearOutSlowInEasing
            ))
            .height(height =
                if (cekScroll(lazyListState)){
                    0.dp
                    if (ScrollDirectionDetector(lazyListState) == "Up" && ObserveScrollState(lazyListState)){
                        Log.d("cekFlow", ObserveScrollState(lazyListState).toString())
                        TOP_BAR_HEIGHT
                    } else {
                        0.dp
                    }
                } else {
                    TOP_BAR_HEIGHT
                }
            ),
    )

}

@Composable
fun MainContent(
    lazyListState: LazyListState,
    paddingValues: PaddingValues
) {
    val numbers = remember { List(size = 200){ it } }
    val padding by animateDpAsState(
        targetValue = if (lazyListState.isScrolled) 0.dp else TOP_BAR_HEIGHT,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )

    LazyColumn(
        modifier = Modifier.padding(top = padding),
        state = lazyListState
    ){
        items(
            items = numbers,
            key = { it }
        ){
            NumberHolder(number = it)
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
            TopBar(lazyListState = lazyListState)
        },
        content = { paddingValues ->
            MainContent(lazyListState = lazyListState, paddingValues = paddingValues)
        }
    )


}