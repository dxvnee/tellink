package org.d3if3121.tellink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.d3if3121.tellink.navigation.SetupNavGraph
import org.d3if3121.tellink.ui.theme.TellinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TellinkTheme {
                SetupNavGraph()
            }
        }
    }
}
