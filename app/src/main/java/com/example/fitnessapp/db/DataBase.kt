package com.example.fitnessapp.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column


object ReadyMadeWorkouts : IntIdTable() {
    val name: Column<String> = varchar("name", length = 30)
    val description: Column<String> = varchar("description", length = 60)
}

object Muscular_Types : IntIdTable(){
    val name: Column<String> = varchar("name", length = 30)
}

object IdividualExcercises: IntIdTable(){
    val name: Column<String> = varchar("name", length = 30)
    //val muscular_id: Column<Int?> = (integer("muscular_id").references(Muscular_Types.id)).nullable()
}

object ReadyMadeWorkouts_Idividual_Exercises: IntIdTable(){
    val readyMadeWorkouts = reference("ReadyMadeWorkouts", ReadyMadeWorkouts)
    val individualExcercises = reference("IdividualExcercises", IdividualExcercises)
}

class IdividualExcercise(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<IdividualExcercise>(IdividualExcercises)

}