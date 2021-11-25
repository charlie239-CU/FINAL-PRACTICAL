package example.javatpoint.com.kotlinsqlitecrud

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.mysqliteexperiment.Student

//creating the database logic, extending the SQLiteOpenHelper base class  
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "sDatabase"
        private val TABLE_CONTACTS = "student"
        private val KEY_NAME = "name"
        private val KEY_SCORE = "score"
    }
    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                 + KEY_NAME + " TEXT PRIMARY KEY,"
                + KEY_SCORE + " Number" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
           db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data  
    fun addstu(stu: Student):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, stu.name) // Student Name  
        contentValues.put(KEY_SCORE,stu.score!!.toInt() ) // Student Phone
          val success = db.insert(TABLE_CONTACTS, null, contentValues)
         db.close() // Closing database connection
        return success
    }
    //method to read data  
    @SuppressLint("Range")
    fun viewstu():MutableList<Student>{
        val stuList:MutableList<Student> = mutableListOf()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userName: String
        var userscore: String
        if (cursor.moveToFirst()) {
            do {
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userscore = cursor.getString(cursor.getColumnIndex("score")).toString()
                val stu= Student( name = userName, score = userscore)
                stuList.add(stu)
            } while (cursor.moveToNext())
        }
        return stuList
    }

    @SuppressLint("Range")


    //method to update data  
    fun updatestu(stu: Student):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, stu.name) // Student Name  
        contentValues.put(KEY_SCORE,stu.score!!.toInt() ) // Student score

        // Updating Row
        val score=stu.score
        val name=stu.name


        val success = db.update(TABLE_CONTACTS, contentValues, "$KEY_NAME=$name",null)

        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        Log.d("data",score.toString())
        return success
    }




}  