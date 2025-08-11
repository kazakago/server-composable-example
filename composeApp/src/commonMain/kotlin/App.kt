import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.rpc.withService
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    val service = remember { client.withService<CounterService>() }
    val events = remember { MutableSharedFlow<Unit>(extraBufferCapacity = 5) }
    val counter by service.state(events).collectAsStateWithLifecycle(Counter.Empty)
    MaterialTheme {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("count = ${counter.count}")
                Button(onClick = {
                    events.tryEmit(Unit)
                }) {
                    Text("Click me!")
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}
