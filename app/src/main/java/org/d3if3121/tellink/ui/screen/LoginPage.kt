package org.d3if3121.tellink.ui.screen

import android.media.tv.TvContract.WatchNextPrograms
import org.d3if3121.tellink.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3121.tellink.navigation.Screen
import org.d3if3121.tellink.ui.theme.TellinkTheme
import org.d3if3121.tellink.ui.theme.Warna



@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage(navController = rememberNavController())
}



@Composable
fun ButtonMerah(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier,
){
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        content = {
            content()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor =  Warna.MerahNormal,
            contentColor =  Warna.PutihNormal
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPutih(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier
){
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        placeholder = {
           
            Text(
                text = placeholder,
                color = Warna.AbuTua,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .height(50.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = Warna.HitamNormal,
            unfocusedTextColor = Warna.HitamNormal,
            focusedPlaceholderColor = Warna.MerahNormal,
            focusedBorderColor = Warna.MerahNormal,
            unfocusedBorderColor = Warna.AbuMuda,
            containerColor = if (isFocused) Warna.PutihNormal else Warna.AbuMuda
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),

    )
}

@Composable
fun InputPutihSearchProfile(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    fontSize: Int = 17
){
    var isFocused by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .border(1.dp, if (!isFocused) Warna.AbuMuda else Warna.MerahNormal, RoundedCornerShape(10.dp)).fillMaxHeight(),
        colors = if (!isFocused) CardDefaults.cardColors(Warna.AbuMuda) else CardDefaults.cardColors(Warna.PutihNormal)

    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight().padding(start = 10.dp)
        ){
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "eheh",
                tint = Warna.AbuTua
            )
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxHeight().padding(start = 7.dp)
            ){
                if (input.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Warna.AbuTua,
                        fontSize = fontSize.sp,
                        style = TextStyle(
                            fontSize = fontSize.sp,
                            color = Warna.HitamNormal,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.offset(y = 8.dp)

                    )
                }
                BasicTextField(
                    value =  input,
                    onValueChange = onInputChange,
                    textStyle = TextStyle(
                        fontSize = fontSize.sp,
                        color = Warna.HitamNormal,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = if (input.isEmpty()) -9.dp else 0.dp)
                        .onFocusChanged {
                            isFocused = it.isFocused
                        },
                )
            }
        }


    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPutihSearch(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    fontSize: Int = 17
){
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        leadingIcon = {
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "eheh",
                tint = Warna.AbuTua
            )
        },
        textStyle = TextStyle(
            color = Warna.HitamNormal,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Normal,
        ),
        value = input,
        onValueChange = onInputChange,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                color = Warna.AbuTua,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        },
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .height(48.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = Warna.HitamNormal,
            unfocusedTextColor = Warna.HitamNormal,
            focusedPlaceholderColor = Warna.MerahNormal,
            focusedBorderColor = Warna.MerahNormal,
            unfocusedBorderColor = Warna.AbuMuda,
            containerColor = if (isFocused) Warna.PutihNormal else Warna.AbuMuda
        ),

        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),

        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputPassword(
    input: String,
    placeholder: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType,
    passwordVisible: MutableState<Boolean>,
    modifiers: Modifier = Modifier
){
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        placeholder = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = placeholder,
                    color = Warna.AbuTua,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }

        },

        shape = RoundedCornerShape(10.dp),
        modifier = modifiers
            .height(50.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = Warna.HitamNormal,
            unfocusedTextColor = Warna.HitamNormal,
            focusedPlaceholderColor = Warna.MerahNormal,
            focusedBorderColor = Warna.MerahNormal,
            unfocusedBorderColor = Warna.AbuMuda,
            containerColor = if (isFocused) Warna.PutihNormal else Warna.AbuMuda
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image =
                if (passwordVisible.value) painterResource(id = R.drawable.baseline_visibility_24)
                else painterResource(id = R.drawable.baseline_visibility_off_24)

            IconButton(onClick = {
                passwordVisible.value = !passwordVisible.value
            }) {
                Icon(
                    painter = image,
                    contentDescription = if (passwordVisible.value) "Hide password" else "Show password",
                    tint = Warna.MerahNormal
                )
            }
        }

        )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    navController: NavHostController
) {
    var nim by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Warna.PutihNormal, RectangleShape)
    ) {


        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 95.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App logo",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .shadow(elevation = 25.dp, shape = CircleShape, ambientColor = Color.Red)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row {
                        Text(
                            text = "Log in to ",
                            color = Warna.MerahNormal,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Text(
                            text = "Tellink.",
                            color = Warna.MerahNormal,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        Canvas(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 525.dp)
                .size(400.dp)

        ) {

            drawCircle(
                color = Warna.MerahTua,
                radius = size.minDimension
            )


        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 280.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                .height(350.dp),

            colors = CardDefaults.cardColors(containerColor = Warna.PutihNormal),
            elevation = CardDefaults.cardElevation(20.dp),
            shape = RoundedCornerShape(15.dp)
        )
        {
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, top = 23.dp, end = 24.dp, bottom = 23.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .height(100.dp)
                ){
                    Column {
                        Text(
                            text = stringResource(id = R.string.nim),
                            color = Warna.MerahNormal,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                        InputPutih(
                            input = nim,
                            placeholder = stringResource(id = R.string.enter_nim),
                            onInputChange = { input ->
                                nim = input
                            },
                            keyboardType = KeyboardType.Number,
                        )

                    }
                }

                Row(
                    modifier = Modifier.padding(top = 6.dp)
                ){
                    Column {
                        Text(
                            text = stringResource(id = R.string.password),
                            color = Warna.MerahNormal,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                        InputPassword(
                            input = password,
                            placeholder = stringResource(id = R.string.enter_password),
                            onInputChange = { input ->
                                password = input
                            },
                            keyboardType = KeyboardType.Password,
                            passwordVisible = passwordVisible,
                            modifiers = Modifier.fillMaxWidth()
                        )
                    }
                }


                Row {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        ButtonMerah(
                            onClick = {

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                                .size(46.dp),
                            content = {
                                Text(
                                    text = "LOGIN",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 18.sp,
                                    color = Warna.PutihNormal
                                )
                            }
                        )

                        Row(

                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Don't have account? ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500)
                            )
                            ClickableText(
                                text = AnnotatedString("Register!"),
                                onClick = {
                                    navController.navigate(Screen.Register.route)
                                },
                                style = TextStyle.Default.copy(
                                    Warna.MerahNormal,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(500)
                                )
                            )
                        }

                    }
                }



            }
        }
    }

}