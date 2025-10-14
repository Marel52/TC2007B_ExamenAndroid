package com.app.tc2007b_examenandroid.presentation.navigation

sealed class Screen(

){
    object Home : Screen ("home")

    object Detail : Screen
}