package com.example.fitnessapp.db

import org.jetbrains.exposed.sql.Database

object Db{
    val connenct = Database.connect("jdbc:h2:./myh2file", "org.h2.Driver")
}