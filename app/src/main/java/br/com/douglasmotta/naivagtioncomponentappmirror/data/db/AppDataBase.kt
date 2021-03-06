package br.com.douglasmotta.naivagtioncomponentappmirror.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.dao.UserDao

//Criamos a classe como abstract, pois quem fará implementação da mesma será o room

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE:AppDataBase? = null

        fun getDatabase(context: Context):AppDataBase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}