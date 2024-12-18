package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.R
import org.d3if3121.tellink.ui.theme.Warna


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
                        text = nama!!,
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
