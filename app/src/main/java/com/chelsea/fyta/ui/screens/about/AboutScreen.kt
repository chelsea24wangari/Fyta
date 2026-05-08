import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.R
import com.chelsea.fyta.ui.theme.Purple40

@Composable
fun AboutScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())

    ) {

        // Header

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)

            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }

            Text(
                text = "About FYTA",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // App Info

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.Top

        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "FYTA Logo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop

            )

            Spacer(modifier = Modifier.width(16.dp))


            Column {

                Text(
                    text = "FYTA",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color.Black

                )

                Text(
                    text = "Your All-in-One Fitness Companion",
                    color = Purple40,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium

                )

                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = "FYTA is designed to help you live a healthier, stronger, and more balanced life. From personalized workouts to nutrition tracking and progress insights, we're with you every step of your fitness journey.",
                    fontSize = 13.sp,
                    color = Color.Black,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Features Row

        Card(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                FeatureItem(Icons.Default.FitnessCenter, "Workouts", Purple40)
                FeatureItem(Icons.Default.Restaurant, "Nutrition", Color.Green)
                FeatureItem(Icons.Default.ShowChart, "Progress", Color.Magenta)
                FeatureItem(Icons.Default.FavoriteBorder, "Wellness", Color.Blue)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Mission

        InfoCard(
            icon = Icons.Default.TrackChanges,
            title = "Our Mission",
            desc = "To empower people everywhere to achieve their fitness goals through smart tools, expert guidance, and a supportive community."
        )

        // Vision

        InfoCard(
            icon = Icons.Default.Visibility,
            title = "Our Vision",
            desc = "To become the world's most trusted fitness platform, inspiring millions to live healthier lives every day."
        )

        // Why FYTA

        WhyFytaCard()

        Spacer(modifier = Modifier.height(8.dp))


        // Settings Items

        AboutSettingsItem(Icons.Outlined.Shield, "Privacy Policy")
        AboutSettingsItem(Icons.Outlined.Description, "Terms of Use")
        AboutSettingsItem(Icons.Outlined.Info, "App Version", trailingText = "1.0.0")
        AboutSettingsItem(Icons.Outlined.Email, "Contact Us")

        Spacer(modifier = Modifier.height(24.dp))

        // Footer

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF6C4EF6))
                .padding(16.dp),
            contentAlignment = Alignment.Center

        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "Made with ",
                    color = Color.White,
                    fontSize = 14.sp

                )

                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)

                )

                Text(
                    text = " for a healthier you!",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))

    }
}

@Composable
fun FeatureItem(icon: ImageVector, title: String, color: Color) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Icon(
            icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(28.dp)

        )

        Spacer(modifier = Modifier.height(4.dp))


        Text(
            text = title,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun InfoCard(icon: ImageVector, title: String, desc: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)

    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Icon(
                icon,
                contentDescription = null,
                tint = Purple40,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp

                )

                Text(
                    text = desc,
                    fontSize = 13.sp,
                    color = Color.Black,
                    lineHeight = 18.sp
                )
            }

            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}

@Composable
fun WhyFytaCard() {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)

    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Icon(
                Icons.Default.Groups,
                contentDescription = null,
                tint = Purple40,
                modifier = Modifier.size(32.dp)

            )

            Spacer(modifier = Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = "Why FYTA?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))


                BulletPoint("Personalized workout & nutrition plans")
                BulletPoint("Track your progress in real-time")
                BulletPoint("Expert tips and guidance")
                BulletPoint("Stay motivated with goals & challenges")

            }

            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.LightGray

            )
        }
    }
}

@Composable
fun BulletPoint(text: String) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)

    ) {

        Icon(
            Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Purple40,
            modifier = Modifier.size(16.dp)

        )

        Spacer(modifier = Modifier.width(8.dp))


        Text(
            text = text,
            fontSize = 13.sp,
            color = Color.Black
        )
    }
}

@Composable
fun AboutSettingsItem(icon: ImageVector, title: String, trailingText: String? = null) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {

        Icon(
            icon,
            contentDescription = null,
            tint = Purple40,
            modifier = Modifier.size(24.dp)

        )

        Spacer(modifier = Modifier.width(16.dp))


        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = Color.DarkGray

        )
        if (trailingText != null) {


            Text(
                text = trailingText,
                fontSize = 14.sp,
                color = Color.Gray

            )
        } else {

            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}

























































































@Preview (showBackground =true)
@Composable
fun AboutScreenPreview(){
    AboutScreen(rememberNavController())
}