package com.example.fitnessapp.components
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*

object ReadyMadeWorkouts : IntIdTable() {
    val name: Column<String> = varchar("name", length = 30)
    val description: Column<String> = varchar("description", length = 60)
}

object Muscular_Types : IntIdTable(){
    val name: Column<String> = varchar("name", length = 30)
}

object Idividual_Excercises: IntIdTable(){
    val name: Column<String> = varchar("name", length = 30)
    val muscular_id: Column<Int?> = (integer("muscular_id").references(Muscular_Types.id)).nullable()
}



