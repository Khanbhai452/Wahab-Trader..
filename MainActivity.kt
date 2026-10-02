package com.wahabtrader.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val bg = Color(0xFF070A0D)
private val panel = Color(0xFF111820)
private val green = Color(0xFF00D887)
private val red = Color(0xFFFF465C)

class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)
  setContent { MaterialTheme(colorScheme = darkColorScheme(background=bg, surface=panel, primary=green)) { Home() } }
 }
}

@Composable fun Home() {
 var asset by remember { mutableStateOf("EUR/USD") }
 var signal by remember { mutableStateOf("WAITING FOR DATA") }
 var trend by remember { mutableStateOf("Not connected") }
 Surface(Modifier.fillMaxSize(), color=bg) {
  Column(Modifier.fillMaxSize().padding(18.dp), verticalArrangement=Arrangement.spacedBy(14.dp)) {
   Text("WAHAB TRADER", color=Color.White, fontSize=26.sp, fontWeight=FontWeight.Black)
   Text("QUOTEX SIGNAL BOT  •  SIGNAL ONLY", color=green, fontSize=11.sp, letterSpacing=1.5.sp)
   Card(colors=CardDefaults.cardColors(containerColor=panel), shape=RoundedCornerShape(18.dp)) {
    Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(10.dp)) {
     Text("MARKET", color=Color.Gray, fontSize=12.sp)
     Row(verticalAlignment=Alignment.CenterVertically, horizontalArrangement=Arrangement.SpaceBetween, modifier=Modifier.fillMaxWidth()) {
      Text(asset, color=Color.White, fontSize=22.sp, fontWeight=FontWeight.Bold)
      Text("DEMO / PREVIEW", color=green, fontSize=11.sp)
     }
     Row(horizontalArrangement=Arrangement.spacedBy(8.dp)) {
      listOf("EUR/USD","GBP/USD","BTC/USD").forEach { a -> FilterChip(selected=asset==a,onClick={asset=a},label={Text(a)}, colors=FilterChipDefaults.filterChipColors(selectedContainerColor=green, selectedLabelColor=Color.Black)) }
     }
    }
   }
   Card(colors=CardDefaults.cardColors(containerColor=panel), shape=RoundedCornerShape(18.dp)) {
    Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(12.dp)) {
     Text("15M TREND", color=Color.Gray, fontSize=12.sp)
     Text(trend, color=Color.White, fontSize=20.sp, fontWeight=FontWeight.Bold)
     Divider(color=Color.DarkGray)
     Text("1M ENTRY SIGNAL", color=Color.Gray, fontSize=12.sp)
     Text(signal, color=Color.White, fontSize=24.sp, fontWeight=FontWeight.Black)
     Text("No live market feed connected. Signals are disabled until a verified data source is added.", color=Color.LightGray, fontSize=12.sp)
    }
   }
   Button(onClick={signal="NO SIGNAL — DATA REQUIRED"; trend="Waiting for verified candles"}, modifier=Modifier.fillMaxWidth(), colors=ButtonDefaults.buttonColors(containerColor=green, contentColor=Color.Black)) { Text("CHECK SIGNAL", fontWeight=FontWeight.Bold) }
   Card(colors=CardDefaults.cardColors(containerColor=panel), shape=RoundedCornerShape(18.dp)) {
    Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(6.dp)) {
     Text("STRATEGY", color=green, fontWeight=FontWeight.Bold)
     Text("15-minute trend + 1-minute confirmation", color=Color.White)
     Text("CALL / PUT alerts only • No auto trading • No martingale", color=Color.LightGray, fontSize=12.sp)
    }
   }
   Spacer(Modifier.weight(1f))
   Text("Wahab Trader • Prototype build • Not financial advice", color=Color.Gray, fontSize=10.sp, modifier=Modifier.align(Alignment.CenterHorizontally))
  }
 }
}
