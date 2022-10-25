package com.greedy.practice

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SqliteHelper (context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version){

    override fun onCreate(db: SQLiteDatabase?) {

        /* 데이터베이스가 생성될 때 테이블을 생성한다. */
        val create = "create table memo (" +
                "no integer primary key, " +
                "content text, " +
                ")"

        db?.execSQL(create)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    /* insert */
    fun insertMemo(memo: Memo){
        val values = ContentValues()
        values.put("content", memo.content)

        val wd = writableDatabase
        wd.insert("memo", null, values)
        wd.close()
    }

    @SuppressLint("Range")
    fun selectMemo(): MutableList<Memo>{

        /* 데이터베이스가 존재하지 않는 다면 onCreate를 호출해서 테이블을 생성한다. */
        if(readableDatabase == null){
            onCreate(readableDatabase)
        }

        /* 조회 시에는 readableDatabase를 이용한다. */
        val rd = readableDatabase

        val select = "select * from memo"
        val list = mutableListOf<Memo>()

        /* 조회결과는 cursor 형태로 되돌아 오는데 조회 시 쿼리문과 쿼리문에 전달할 값을 인자로 전달한다. */
        var cursor = rd.rawQuery(select, null)

        while (cursor.moveToNext()){
            val no = cursor.getLong(cursor.getColumnIndex("no"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            list.add(Memo(no,content))
        }

        cursor.close()
        rd.close()
        return  list

    }

    /* delete */
    fun  deleteMemo(memo: Memo){
        val delete = "delete from memo where no = ${memo.no}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }

}