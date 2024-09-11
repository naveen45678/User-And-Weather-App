package com.test.android.technicalinterviewapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.test.android.technicalinterviewapp.data.local.entity.User

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier, // Use passed modifier
    value: String,
    buttonColor: Color,
    textColor: Color,
    onClick:  () -> Unit,
    textSize: TextUnit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(50.dp), // Apply passed modifier without overriding it
        colors = ButtonDefaults.buttonColors(buttonColor)
    ) {
        Text(
            text = value,
            color = textColor,
            fontSize = textSize
        )
    }
}


@Preview
@Composable
fun ButtonComponentPreview() {
    ButtonComponent(

        textSize = 20.sp,
        value = "Login",
        buttonColor = Color.Blue,
        textColor = Color.White,
        onClick = {

        }
    )

}

@Composable
fun TextComponent(
    value: String,
    textSize: TextUnit,
    textColor: Color,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = value,
        fontSize = textSize,
        color = textColor,
        fontWeight = fontWeight

    )
}

@Preview
@Composable
fun TextComponentPreview() {
    TextComponent(
        value = "Naveen",
        textSize = 14.sp,
        textColor = Color.White
    )
}


@Composable
fun OutlinedEditTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    Column {
        OutlinedTextField(
            maxLines = 1,
            value = value,
            onValueChange = onValueChange,
            label = { Text(labelText) },
            modifier = modifier.fillMaxWidth(),
            isError = !errorMessage.isNullOrEmpty()
        )

        // Show error if available
        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewOutlinedEditTextField() {
    OutlinedTextField(value = "enter userName", onValueChange = {

    })
}

@Composable
fun UserListItem(user: User, onItemClick : ()->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).clickable {
                onItemClick()
            }, // Add some padding around the card
        elevation = CardDefaults.cardElevation(4.dp), // Optional: Elevation to give it a raised look
        shape = RoundedCornerShape(8.dp) // Optional: Rounded corners
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth() // Fill the card width
                .padding(16.dp), // Padding inside the card
            horizontalAlignment = Alignment.Start // Align content to start
        ) {
            Text(
                text = "First Name: ${user.firstName}",
                fontSize = 16.sp,
                color = Color.Blue,
                modifier = Modifier.padding(bottom = 8.dp) // Optional: Add space between texts
            )
            Text(
                text = "Last Name: ${user.lastName}",
                fontSize = 16.sp,
                color = Color.Blue,
                modifier = Modifier.padding(bottom = 8.dp) // Optional: Add space between texts

            )
            Text(
                text = "Email Id: ${user.email}",
                fontSize = 16.sp,
                color = Color.Blue
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUserListItem() {
    UserListItem(
        onItemClick = {

        },
        user = User(
            firstName = "Naveen",
            lastName = "kumar",
            email = "naveen@gmail.com",
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarApplication(
    title: String,
    onActionClick: () -> Unit,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    imageVectorForAction : ImageVector
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    onBackClick?.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Blue
                    )
                }
            } else {
                // Return null when no navigation icon is needed
                null
            }
        },
        actions = {
            IconButton(onClick = {
                onActionClick()
            }) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = imageVectorForAction,
                    contentDescription = "More options",
                    tint = Color.Blue
                )
            }
        }
    )
}

//@Preview
//@Composable
//fun PreviewTopAppBar() {
//    TopAppBarUserList(
//        rememberNavController(),
//        onClick = {
//
//        }
//    )
//}



