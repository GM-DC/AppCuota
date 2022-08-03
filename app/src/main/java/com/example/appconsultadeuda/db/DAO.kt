package com.unosoft.ecomercialapp.db

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {

    //*************   QUERY DE TABLAS    *********************
    @Query("SELECT * FROM EntityDataLogin")
    fun getAllDataLogin(): List<EntityDataLogin>

    //*************   INSERT DE TABLAS    *********************
    @Insert
    fun insertDataLogin(insertDataLogin: EntityDataLogin)

    //*********  ELIMINAR DATOS DE LAS TABLAS  **************
    @Query("DELETE FROM EntityDataLogin")
    fun deleteTableDataLogin()

    //*********  REINICIAR LOS ID AUTOGENERADOS   **************
    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'EntityDataLogin'")
    fun clearPrimaryKeyDataLogin()

}
