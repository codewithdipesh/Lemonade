package com.example.lemonade

import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}



@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var page by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }


    when(page) {
     1-> {
         LemonTextAndImage(
             text = R.string.Lemon_tree,
             image = R.drawable.lemon_tree,
             imageDescription = "Lemon Tree",
             modifier = Modifier,
             onImageClick = {
                 page = 2
                 squeezeCount = (2..4).random()
             }
         )
     }
        2-> {
            LemonTextAndImage(
                text = R.string.Lemon,
                image = R.drawable.lemon_squeeze,
                imageDescription = "Lemon squeeze",
                modifier = Modifier,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        page = 3
                    }

                }
            )
        }
        3->{
            LemonTextAndImage(
                text = R.string.Glass_Of_Lemon,
                image = R.drawable.lemon_drink,
                imageDescription = "Lemon juice",
                modifier = Modifier,
                onImageClick = {
                    page = 4
                }
            )
        }
        4->{
            LemonTextAndImage(
                text = R.string.Empty_Glass,
                image = R.drawable.lemon_restart,
                imageDescription = "Once more",
                modifier = Modifier,
                onImageClick = {
                    page = 1
                }
            )
        }
    }


}


@Composable
fun LemonTextAndImage(text:Int , image: Int,imageDescription :String,modifier:Modifier,onImageClick : ()->Unit){

    Surface (
        modifier = Modifier
            .fillMaxSize()
        ,
        color = MaterialTheme.colorScheme.background
    ){
               Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                Button(
                    onClick = onImageClick,
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(Color(186, 255, 237))

                   ) {
               Image(
                painter = painterResource(image),
                contentDescription = imageDescription,
                modifier = Modifier
                    .size(200.dp) // Adjust size as needed
                    .padding(4.dp) // Add padding to prevent clipping by the border
            )
          }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(text), fontSize = 18.sp)

                }

        }

    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonApp()
    }
}