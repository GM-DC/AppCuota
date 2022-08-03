package com.unosoft.ecomercialapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EntityDataLogin::class],
    version = 2)
abstract class TablaBasica : RoomDatabase() {
    abstract fun daoTblBasica(): DAO
}