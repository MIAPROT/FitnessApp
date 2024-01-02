package com.example.fitnessapp.db

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.date
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import org.jetbrains.exposed.sql.kotlin.datetime.time
import java.sql.Time
import java.util.Date


object ReadyMadeWorkouts : IntIdTable() {
    val name: Column<String> = varchar("name", length = 30)
    val description: Column<String> = varchar("description", length = 60)
}

object Muscular_Types : IntIdTable(){
    val name: Column<String> = varchar("name", length = 30)
}

object Persons: IntIdTable(){
    val date: Column<String> = varchar("date", length = 300)
    val weight: Column<Int> = integer("weight")
    val height: Column<Int> = integer("height")
    val activity: Column<Int> = integer("activity")
}

class Person(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<Person>(Persons)
    var date by Persons.date
    var weight by Persons.weight
    var height by Persons.height
    var activity by Persons.activity
}

object IdividualExcercises: IntIdTable(){
    val name: Column<String> = varchar("name", length = 30)
    val muscular_id: Column<Int?> = (integer("muscular_id").references(Muscular_Types.id)).nullable()
    val link: Column<String> = varchar("link", length = 400)
    var timer: Column<String> = varchar("timer", length = 300)
}

class IdividualExcercise(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<IdividualExcercise>(IdividualExcercises)
    var name by IdividualExcercises.name
    var muscular_id by IdividualExcercises.muscular_id
    var link by IdividualExcercises.link
    var timer by IdividualExcercises.timer

}

object DoneExcercises: IntIdTable(){
    val readyMadeWorkouts = reference("ReadyMadeWorkouts", ReadyMadeWorkouts)
    val individualExcercises = reference("IdividualExcercises", IdividualExcercises)
}

object ReadyMadeWorkouts_Idividual_Exercises: IntIdTable(){
    val readyMadeWorkouts = reference("ReadyMadeWorkouts", ReadyMadeWorkouts)
    val individualExcercises = reference("IdividualExcercises", IdividualExcercises)
}


class ReadyMadeWorkout_Idividual_Exercise(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<ReadyMadeWorkout_Idividual_Exercise>(ReadyMadeWorkouts_Idividual_Exercises)
    val name by ReadyMadeWorkouts_Idividual_Exercises.readyMadeWorkouts
    val individualExcercises by ReadyMadeWorkouts_Idividual_Exercises.individualExcercises
}

class ReadyMadeWorkout(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<ReadyMadeWorkout>(ReadyMadeWorkouts)
    var name by ReadyMadeWorkouts.name
    var description by ReadyMadeWorkouts.description
}

class Muscular_Type(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<Muscular_Type>(Muscular_Types)
    var name by Muscular_Types.name
}