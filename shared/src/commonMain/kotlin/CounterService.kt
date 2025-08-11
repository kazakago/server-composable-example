import kotlinx.coroutines.flow.Flow
import kotlinx.rpc.annotations.Rpc
import kotlinx.serialization.Serializable

@Serializable
data class Counter(
    val count: Int,
) {
    companion object {
        val Empty = Counter(0)
    }
}

@Rpc
interface CounterService {
    fun state(events: Flow<Unit>): Flow<Counter>
}
