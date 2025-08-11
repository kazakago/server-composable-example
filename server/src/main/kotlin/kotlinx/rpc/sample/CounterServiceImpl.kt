package kotlinx.rpc.sample

import Counter
import CounterService
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import kotlinx.coroutines.flow.Flow

class CounterServiceImpl : CounterService {
    override fun state(events: Flow<Unit>): Flow<Counter> = moleculeFlow(RecompositionMode.Immediate) { CounterPresenter(events) }
}

@Composable
fun CounterPresenter(events: Flow<Unit>): Counter {
    var counter by remember { mutableStateOf(Counter.Empty) }

    LaunchedEffect(Unit) {
        events.collect {
            counter = Counter(counter.count + 1)
        }
    }

    return counter
}
