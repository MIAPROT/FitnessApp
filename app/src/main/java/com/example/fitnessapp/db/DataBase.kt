package com.example.fitnessapp.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


object ReadyMadeWorkouts : IntIdTable() {
    val name: Column<String> = varchar("name", length = 300)
    val description: Column<String> = varchar("description", length = 300)
    val image: Column<String> = varchar("image",10000)
}

object Muscular_Types : IntIdTable(){
    val name: Column<String> = varchar("name", length = 300)
}

object Persons: IntIdTable(){
    val age: Column<Int> = integer("age")
    val weight: Column<Int> = integer("weight")
    val height: Column<Int> = integer("height")
    val activity: Column<Int> = integer("activity")
}

class Person(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<Person>(Persons)
    var age by Persons.age
    var weight by Persons.weight
    var height by Persons.height
    var activity by Persons.activity
}

object IndividualExcercises: IntIdTable(){
    val name: Column<String> = varchar("name", length = 600)
    val muscular_id: Column<Int> = integer("muscular_id").references(Muscular_Types.id)
    val link: Column<String> = varchar("link", length = 400)
    var timer: Column<Int> = integer("timer")
    val description: Column<String> = varchar("description", length = 1000)
    val image: Column<String> = varchar("image",10000)
}

class IndividualExcercise(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<IndividualExcercise>(IndividualExcercises)
    var name by IndividualExcercises.name
    var muscular_id by IndividualExcercises.muscular_id
    var link by IndividualExcercises.link
    var timer by IndividualExcercises.timer
    var description by IndividualExcercises.description
    var image by IndividualExcercises.image
}

object DoneExcercises: IntIdTable(){
    val readyMadeWorkouts = reference("ReadyMadeWorkouts", ReadyMadeWorkouts).nullable()
    val individualExcercises = reference("IndividualExcercises", IndividualExcercises).nullable()
}

class DoneExcercise(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<DoneExcercise>(DoneExcercises)
    var readyMadeWorkouts by DoneExcercises.readyMadeWorkouts
    var individualExcercises by DoneExcercises.individualExcercises
}

object ReadyMadeWorkouts_Idividual_Exercises: Table(){
    val readyMadeWorkouts = reference("ReadyMadeWorkouts", ReadyMadeWorkouts)
    val individualExcercises = reference("IndividualExcercises", IndividualExcercises)
    override val primaryKey = PrimaryKey(readyMadeWorkouts, individualExcercises)
}


class ReadyMadeWorkout(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<ReadyMadeWorkout>(ReadyMadeWorkouts)
    var name by ReadyMadeWorkouts.name
    var description by ReadyMadeWorkouts.description
    var image by ReadyMadeWorkouts.image
    var readyMadeWorkouts by IndividualExcercise via ReadyMadeWorkouts_Idividual_Exercises
}

class Muscular_Type(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<Muscular_Type>(Muscular_Types)
    var name by Muscular_Types.name
}

/*class ReadyMadeWorkout_Idividual_Exercise(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<ReadyMadeWorkout_Idividual_Exercise>(ReadyMadeWorkouts_Idividual_Exercises)
    var readyMadeWorkouts by ReadyMadeWorkouts_Idividual_Exercises.readyMadeWorkouts
    var individualExcercises by ReadyMadeWorkouts_Idividual_Exercises.individualExcercises
}*/