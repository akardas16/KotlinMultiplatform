import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import networking.Network
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import utility.bounceClick

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        var greetingText by remember { mutableStateOf("Hello World!") }
        var showImage by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        var url by remember { mutableStateOf("") }
        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            KamelImage(resource = asyncPainterResource(data = url),contentDescription = null,
                contentScale = ContentScale.Crop,modifier = Modifier.size(180.dp).clip(
                    RoundedCornerShape(16.dp)
                ))
            Card(modifier = Modifier.bounceClick(0.96f) {
                scope.launch {
                   Network.shared.fetchData { response, error ->
                       response?.let {data->
                           url = data[0].image
                           Log.i("sddsfsdfsdf",data[0].image)
                       }
                   }

                }


            },shape = CircleShape, elevation = 8.dp,
                backgroundColor = Color.Blue){
                Text(text = "Click Me", color = Color(0xffffa601),
                    modifier = Modifier.padding(horizontal = 45.dp, vertical = 8.dp), fontWeight = FontWeight.SemiBold)
            }

            Text(text = platformName().name.name)

            Button(onClick = {
                greetingText = "Compose: ${Greeting().greet()}"
                showImage = !showImage
            }) {
                Text(greetingText)
            }
            AnimatedVisibility(showImage) {
                Image(
                    painterResource("setting.xml"),
                    null, modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}

class HomeScreen:Screen{
    @Composable
    override fun Content() {
        App()
    }

}