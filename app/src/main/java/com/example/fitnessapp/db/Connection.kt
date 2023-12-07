package com.example.fitnessapp.db

import org.jetbrains.exposed.sql.Database

object Db{
    val connect = Database.connect("jdbc:h2:/data/data/com.example.fitnessapp/databases/fitnessapp.db", "org.h2.Driver")
}