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

object MuscularTypes : IntIdTable(){
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

object IndividualExercises: IntIdTable(){
    val name: Column<String> = varchar("name", length = 600)
    val muscular_id: Column<Int> = integer("muscular_id").references(MuscularTypes.id)
    val link: Column<String> = varchar("link", length = 400)
    var timer: Column<Int> = integer("timer")
    val description: Column<String> = varchar("description", length = 1000)
    val image: Column<String> = varchar("image",10000)
}

class IndividualExercise(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<IndividualExercise>(IndividualExercises)
    var name by IndividualExercises.name
    var muscular_id by IndividualExercises.muscular_id
    var link by IndividualExercises.link
    var timer by IndividualExercises.timer
    var description by IndividualExercises.description
    var image by IndividualExercises.image
}

object DoneExercises: IntIdTable(){
    val readyMadeWorkouts = reference("ReadyMadeWorkouts", ReadyMadeWorkouts).nullable()
    val individualExcercises = reference("IndividualExcercises", IndividualExercises).nullable()
}

class DoneExercise(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<DoneExercise>(DoneExercises)
    var readyMadeWorkouts by DoneExercises.readyMadeWorkouts
    var individualExcercises by DoneExercises.individualExcercises
}

object ReadyMadeWorkoutsIndividualExercises: Table(){
    val readyMadeWorkouts = reference("ReadyMadeWorkouts", ReadyMadeWorkouts)
    val individualExcercises = reference("IndividualExcercises", IndividualExercises)
    override val primaryKey = PrimaryKey(readyMadeWorkouts, individualExcercises)
}


class ReadyMadeWorkout(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<ReadyMadeWorkout>(ReadyMadeWorkouts)
    var name by ReadyMadeWorkouts.name
    var description by ReadyMadeWorkouts.description
    var image by ReadyMadeWorkouts.image
    var readyMadeWorkouts by IndividualExercise via ReadyMadeWorkoutsIndividualExercises
}

class MuscularType(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<MuscularType>(MuscularTypes)
    var name by MuscularTypes.name
}

/*class ReadyMadeWorkout_Idividual_Exercise(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<ReadyMadeWorkout_Idividual_Exercise>(ReadyMadeWorkouts_Idividual_Exercises)
    var readyMadeWorkouts by ReadyMadeWorkouts_Idividual_Exercises.readyMadeWorkouts
    var individualExcercises by ReadyMadeWorkouts_Idividual_Exercises.individualExcercises
}*/