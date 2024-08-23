package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier)
{
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Yellow)
                .padding(vertical = 16.dp),
            text = stringResource(R.string.title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var step by remember { mutableStateOf(1) }
            var squeezeCounter = 0
            var squeezeGoal = 0
            var imageResource = 0
            var contentDescriptionResource = 0
            var stepDescriptionResource = 0
            when(step) {
                1 -> {
                    squeezeCounter = 0
                    imageResource = R.drawable.lemon_tree
                    contentDescriptionResource = R.string.tree_content_description
                    stepDescriptionResource = R.string.tree_step_description
                }
                2 -> {
                    squeezeGoal = (2..4).random()
                    imageResource = R.drawable.lemon_squeeze
                    contentDescriptionResource = R.string.lemon_content_description
                    stepDescriptionResource = R.string.squeeze_step_description
                }
                3 -> {
                    imageResource = R.drawable.lemon_drink
                    contentDescriptionResource = R.string.glass_of_lemonade_content_description
                    stepDescriptionResource = R.string.drink_step_description
                }
                else -> {
                    imageResource = R.drawable.lemon_restart
                    contentDescriptionResource = R.string.empty_glass_content_description
                    stepDescriptionResource = R.string.empty_glass_step_description
                }
            }
            Button(
                onClick =
                {
                    when (step)
                    {
                        2 -> {
                            squeezeCounter++
                            if (squeezeCounter == squeezeGoal) step++
                        }
                        in 1..3 -> step++
                        else -> step = 1
                    }
                },
                colors = ButtonColors(
                    containerColor = Color(red = 105, green = 205, blue = 216, alpha = 150),
                    contentColor = Color.Yellow,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Blue
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = stringResource(id = contentDescriptionResource),
                )
            }
            Spacer(
                Modifier.height(24.dp)
            )
            Text(
                text = stringResource(stepDescriptionResource),
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun LemonadeAppPreview() {
    LemonadeApp()
}