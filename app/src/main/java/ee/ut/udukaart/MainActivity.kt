package ee.ut.udukaart

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

// Üks logisilt kogu rakendusele: Logcatis saab siis filtreerida tag:Udukaart
private const val TAG = "Udukaart"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Elutsükkel on nähtav Logcatis — see on loengu 7 teema, aga vaata juba praegu
        Log.d(TAG, "onCreate: rakendus käivitub")

        setContent {                      // siit algab Compose
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(discovered = 0.0)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ekraan muutub nähtavaks")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ekraan läks tausta")
    }

    // Pööramine HÄVITAB tegevuse ja loob ta uuesti — seda on Logcatis näha
    // ainult siis, kui onDestroy ka logib.
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: tegevus hävitatakse")
    }
}

@Composable
fun HomeScreen(discovered: Double) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(text = stringResource(R.string.app_name),
             style = MaterialTheme.typography.headlineMedium)
        Text(text = stringResource(R.string.discovered, discovered))
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme { HomeScreen(discovered = 12.4) }
}

// Sama ekraan tumeda teemaga, kõrvuti esimesega.
// uiMode seab SÜSTEEMI lipu; Compose'i värvid tulevad SKEEMIST, seega
// paljas MaterialTheme jääks ka siin heledaks.
@Preview(name = "Tume", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenDarkPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        HomeScreen(discovered = 12.4)
    }
}
