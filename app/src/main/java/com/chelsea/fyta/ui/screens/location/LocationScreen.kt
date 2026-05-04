package com.chelsea.fyta.ui.screens.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chelsea.fyta.R
import com.chelsea.fyta.ui.theme.Purple40
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun LocationScreen(navController: NavController) {

    Scaffold(

        bottomBar = {
            BottomNavigationBar(navController)

        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF8F9FE))
        ) {

            // Header

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")

                Text(
                    text = "Gyms Near You",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    Icons.Default.Tune,
                    contentDescription = "Filter",
                    tint = Purple40
                )
            }

            // Location Row

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFF3E8FF), CircleShape),
                    contentAlignment = Alignment.Center

                ) {

                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Purple40,
                        modifier = Modifier.size(24.dp)
                    )

                }
                Spacer(modifier = Modifier.width(12.dp))


                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        text = "Current Location",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "Kileleshwa. Nairobi",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    text = "Change",
                    color = Purple40,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Map Section

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {

                val ny = LatLng(40.7831, -73.9712)
                val cameraPositionState = rememberCameraPositionState {


                    position = CameraPosition.fromLatLngZoom(ny, 14f)

                }
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = MapUiSettings(zoomControlsEnabled = false)
                ) {

                    Marker(
                        state = rememberMarkerState(position = ny),

                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                    )
                    // Sample markers to match the image

                    val purpleHue = 270f

                    Marker(state = rememberMarkerState(position = LatLng(40.785, -73.975)), icon = BitmapDescriptorFactory.defaultMarker(purpleHue))
                    Marker(state = rememberMarkerState(position = LatLng(40.782, -73.965)), icon = BitmapDescriptorFactory.defaultMarker(purpleHue))
                    Marker(state = rememberMarkerState(position = LatLng(40.788, -73.968)), icon = BitmapDescriptorFactory.defaultMarker(purpleHue))
                    Marker(state = rememberMarkerState(position = LatLng(40.780, -73.972)), icon = BitmapDescriptorFactory.defaultMarker(purpleHue))
                    Marker(state = rememberMarkerState(position = LatLng(40.775, -73.968)), icon = BitmapDescriptorFactory.defaultMarker(purpleHue))
                    Marker(state = rememberMarkerState(position = LatLng(40.790, -73.962)), icon = BitmapDescriptorFactory.defaultMarker(purpleHue))
                }
                
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    MapOverlayButton(icon = Icons.Default.MyLocation)
                    MapOverlayButton(icon = Icons.Default.NearMe)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Search Bar

            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search gyms or areas", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(52.dp)
                    .clip(CircleShape),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF3F3F3),
                    unfocusedContainerColor = Color(0xFFF3F3F3),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(8.dp))


            // Gym List

            LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp)

            ) {
                items(sampleGyms) { gym ->
                    GymItem(gym)
                }
                item {
                    AddGymCard()
                }
            }
        }
    }
}

@Composable
fun MapOverlayButton(icon: androidx.compose.ui.graphics.vector.ImageVector) {

    Surface(
        onClick = {},
        shape = CircleShape,
        color = Color.White,
        tonalElevation = 4.dp,
        modifier = Modifier.size(40.dp)

    ) {

        Box(contentAlignment = Alignment.Center) {

            Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
fun GymItem(gym: Gym) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

    ) {

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()

        ) {

            Image(
                painter = painterResource(id = gym.image),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))


            Column(modifier = Modifier.weight(1f)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top

                ) {

                    Text(
                        text = gym.name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold

                    )

                    Icon(
                        imageVector = if (gym.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        tint = if (gym.isFavorite) Purple40 else Color.LightGray,
                        modifier = Modifier.size(22.dp)
                    )
                }


                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(Icons.Default.Star, contentDescription = null, tint = Purple40, modifier = Modifier.size(14.dp))

                    Spacer(modifier = Modifier.width(4.dp))


                    Text(text = gym.rating, fontSize = 13.sp, color = Color.Gray)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.dp))

                    Spacer(modifier = Modifier.width(4.dp))


                    Text(text = "${gym.distance} away", fontSize = 13.sp, color = Color.Gray)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(Icons.Default.AccessTime, contentDescription = null, tint = Color.Green, modifier = Modifier.size(14.dp))

                    Spacer(modifier = Modifier.width(4.dp))


                    Text(
                        text = "Open",
                        fontSize = 13.sp,
                        color = Color.Green
                    )
                    Text(
                        text = " • Closes ${gym.closesAt}",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }
            
            Column(
                modifier = Modifier.height(90.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End

            ) {

                Box(
                    modifier = Modifier
                        .background(Color(0xFFF3E8FF), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center

                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Icon(Icons.Default.NearMe, contentDescription = null, tint = Purple40, modifier = Modifier.size(14.dp))

                        Spacer(modifier = Modifier.width(4.dp))


                        Text(text = gym.time, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Purple40)
                    }
                }
            }
        }
    }
}



@Composable
fun AddGymCard() {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E8FF))

    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Purple40, CircleShape),
                contentAlignment = Alignment.Center

            ) {

                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = "Can't find the right gym?",
                    fontWeight = FontWeight.Bold,
                    color = Purple40,
                    fontSize = 16.sp

                )
                Text(
                    text = "Add a gym to help others find it too!",
                    color = Purple40,
                    fontSize = 13.sp
                )
            }
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Purple40)

        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp

    ) {

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Workouts") },
            label = { Text("Workouts") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.LocationOn, contentDescription = "Location") },
            label = { Text("Location") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Purple40,
                selectedTextColor = Purple40,
                indicatorColor = Color(0xFFF3E8FF)
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.BarChart, contentDescription = "Progress") },
            label = { Text("Progress") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}

data class Gym(
    val name: String,
    val rating: String,
    val distance: String,
    val status: String,
    val closesAt: String,
    val time: String,
    val image: Int,
    val isFavorite: Boolean = false
)



val sampleGyms = listOf(

    Gym("FitZone Gym", "4.6 (128 reviews)", "0.4 km", "Open", "11:00 PM", "5 min", R.drawable.gymp1, true),
    Gym("Powerhouse Fitness", "4.4 (96 reviews)", "0.8 km", "Open", "10:00 PM", "8 min", R.drawable.gymp2),
    Gym("ProFit Gym", "4.3 (74 reviews)", "1.2 km", "Open", "9:00 PM", "12 min", R.drawable.gymp3),
    Gym("The Strength Lab", "4.7 (153 reviews)", "1.5 km", "Open", "10:30 PM", "14 min", R.drawable.gymp4)
)

@Preview(showBackground = true)
@Composable
fun LocationScreenPreview() {
    LocationScreen(rememberNavController())
}
