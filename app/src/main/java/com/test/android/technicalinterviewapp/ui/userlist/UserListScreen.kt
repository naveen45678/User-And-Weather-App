package com.test.android.technicalinterviewapp.ui.userlist

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.test.android.technicalinterviewapp.navigation.Routes
import com.test.android.technicalinterviewapp.ui.TopAppBarApplication
import com.test.android.technicalinterviewapp.ui.UserListItem
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.unit.dp
import com.test.android.technicalinterviewapp.util.showToast


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    userListviewModel: UserListviewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val context = LocalContext.current
    val userList by userListviewModel.allUsers.observeAsState(emptyList())

    BackHandler(enabled = true) {
        (context as? Activity)?.finish()
    }

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBarApplication(
            title = "Users List",
            onActionClick = {
                navController.navigate(Routes.USER_FORM_SCREEN)
            },
            imageVectorForAction = Icons.Filled.AddCircle
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(userList, key = { it.id }) { user ->
                val dismissState = rememberSwipeToDismissBoxState()

                if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart ||
                    dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) {
                    userListviewModel.deleteUser(user)
                    context.showToast("User deleted successfully")
                }

                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {
                        val color by animateColorAsState(
                            when (dismissState.targetValue) {
                                SwipeToDismissBoxValue.Settled -> Color.White
                                SwipeToDismissBoxValue.StartToEnd,
                                SwipeToDismissBoxValue.EndToStart -> Color.Red
                            }
                        )
                        Box(modifier = Modifier.fillMaxSize().background(color))
                    }
                ) {
                    OutlinedCard(
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(Color.Transparent),
                        border = BorderStroke(0.dp, Color.Transparent)
                    ) {
                        UserListItem(user, onItemClick = {
                            navController.navigate(Routes.WEATHER_SCREEN)
                        })
                    }
                }
            }
        }
    }
}






@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewUserListScreen() {
    UserListScreen(modifier = Modifier, navController = rememberNavController())
}



