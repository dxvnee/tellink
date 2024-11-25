package org.d3if3121.tellink.ui.screen

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3121.tellink.navigation.Screen
import org.d3if3121.tellink.ui.theme.TellinkTheme
import org.d3if3121.tellink.ui.theme.Warna
import org.d3if3121.tellink.viewmodel.UserViewModel


@Preview(showBackground = true)
@Composable
fun RegisterPagePreview() {
    RegisterPage(
        navController = rememberNavController(),
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(
    navController: NavHostController,
){
    var nim by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible = remember { mutableStateOf(false) }

    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordVisible = remember { mutableStateOf(false) }

    var usernameError by remember { mutableStateOf(false) }

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
                            text = stringResource(id = R.string.register_title),
                            color = Warna.MerahNormal,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(end = 7.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.tellink),
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
                .height(450.dp),

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
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .height(100.dp)
                ){

                    Column {
                        Row (
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = stringResource(id = R.string.password),
                                color = Warna.MerahNormal,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp).weight(1f)
                            )
                            Text(
                                text = stringResource(id = R.string.confirm_password),
                                color = Warna.MerahNormal,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                                    .weight(1f)
                                    .padding(start = 7.dp)
                            )
                        }

                        Row (
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            InputPassword(
                                input = password,
                                placeholder = stringResource(id = R.string.enter_password),
                                onInputChange = { input ->
                                    password = input
                                },
                                keyboardType = KeyboardType.Password,
                                passwordVisible = passwordVisible,
                                modifiers = Modifier.weight(1f).padding(end = 7.dp)
                            )
                            InputPassword(
                                input = confirmPassword,
                                placeholder = stringResource(id = R.string.enter_password),
                                onInputChange = { input ->
                                    confirmPassword = input
                                },
                                keyboardType = KeyboardType.Password,
                                passwordVisible = confirmPasswordVisible,
                                modifiers = Modifier.weight(1f).padding(start = 7.dp)
                            )
                        }

                    }
                }
                Row(
                    modifier = Modifier.padding(top = 6.dp)
                ){
                    Column {
                        Text(
                            text = stringResource(id = R.string.email),
                            color = Warna.MerahNormal,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                        InputPutih(
                            input = email,
                            placeholder = stringResource(id = R.string.enter_email),
                            onInputChange = { input ->
                                email = input
                            },
                            keyboardType = KeyboardType.Text,
                            modifier = Modifier.fillMaxWidth()
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
                                    text = stringResource(id = R.string.register_bold),
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
                                text = stringResource(id = R.string.have_account),
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                modifier = Modifier.padding(end = 5.dp)
                            )
                            ClickableText(
                                text = AnnotatedString(stringResource(id = R.string.login)),
                                onClick = {
                                    navController.navigate(Screen.Login.route)
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