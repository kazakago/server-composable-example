import io.ktor.client.HttpClient
import io.ktor.http.encodedPath
import kotlinx.rpc.krpc.ktor.client.installKrpc
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json

expect val DEV_SERVER_HOST: String

val client by lazy {
    HttpClient {
        installKrpc()
    }.rpc {
        url {
            host = DEV_SERVER_HOST
            port = 8080
            encodedPath = "/api"
        }
        rpcConfig {
            serialization {
                json()
            }
        }
    }
}
