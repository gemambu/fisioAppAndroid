package com.gmb.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.gmb.madridshops.repository.db.DBConstants
import com.gmb.madridshops.repository.db.DBHelper
import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogData
import com.projectx.fisioapp.repository.entitymodel.catalog.CatalogType


class CatalogDAO(dbHelper: DBHelper) : DAOPersistable<CatalogData> {


    private val dbReadOnlyConn: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteOnlyConn: SQLiteDatabase = dbHelper.writableDatabase

    private fun contentValues(entityData: CatalogData, type: String): ContentValues {
        val content = ContentValues()

        content.put(DBConstants.KEY_ENTITY_DATABASE_ID, entityData.databaseId)
        content.put(DBConstants.KEY_ENTITY_NAME, entityData.name)
        content.put(DBConstants.KEY_ENTITY_DESCRIPTION, entityData.description)
        content.put(DBConstants.KEY_ENTITY_PRICE, entityData.price)
        content.put(DBConstants.KEY_ENTITY_IS_ACTIVE, entityData.isActive)
        content.put(DBConstants.KEY_ENTITY_PROFESSIONAL_ID, entityData.professionalId)
        content.put(DBConstants.KEY_ENTITY_IMAGE_URL, entityData.image)
        content.put(DBConstants.KEY_ENTITY_TYPE, type)
        return content
    }

    override fun query(id: Long): CatalogData {
        val cursor = queryCursor(id)
        cursor.moveToFirst()
        return entityFromCursor(cursor)!!
    }

    override fun count(): Int {
        val cursor = dbReadOnlyConn.rawQuery("SELECT COUNT(*) FROM CATALOG", null)
        cursor.moveToFirst()
        return cursor.getInt(0)
    }

    override fun query(): List<CatalogData> {
        val result = ArrayList<CatalogData>()

        val cursor = dbReadOnlyConn.query(DBConstants.TABLE_CATALOG,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_ENTITY_NAME + " ASC")

        while (cursor.moveToNext()) {
            val entity = entityFromCursor(cursor)!!
            result.add(entity)
        }

        return result
    }

    private fun entityFromCursor(cursor: Cursor): CatalogData? {

        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }

        val isActive = cursor.getInt(cursor.getColumnIndex(DBConstants.KEY_ENTITY_IS_ACTIVE)) == 1;
        val type : CatalogType = if (cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_TYPE)) === "SERVICE")  CatalogType.SERVICE else CatalogType.PRODUCT

        return CatalogData(cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DESCRIPTION)),
                cursor.getFloat(cursor.getColumnIndex(DBConstants.KEY_ENTITY_PRICE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_PROFESSIONAL_ID)),
                isActive,
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_IMAGE_URL)),
                type
        )
    }

    override fun queryCursor(id: Long): Cursor = dbReadOnlyConn.query(DBConstants.TABLE_CATALOG,
            DBConstants.ALL_COLUMNS,
            DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
            arrayOf(id.toString()),
            "",
            "",
            DBConstants.KEY_ENTITY_NAME + " ASC")

    override fun query(type: String): List<CatalogData> {
        val result = ArrayList<CatalogData>()

        val cursor = dbReadOnlyConn.query(DBConstants.TABLE_CATALOG,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_ENTITY_TYPE + " = ?",
                arrayOf(type),
                "",
                "",
                DBConstants.KEY_ENTITY_NAME + " ASC")

        while (cursor.moveToNext()) {
            val entity = entityFromCursor(cursor)!!
            result.add(entity)

        }


        return result
    }

    override fun insert(element: CatalogData, type: String): Long = dbReadWriteOnlyConn.insert(DBConstants.TABLE_CATALOG, null, contentValues(element, type))

    override fun update(id: Long, element: CatalogData): Long =
            dbReadWriteOnlyConn.update(
                    DBConstants.TABLE_CATALOG,
                    contentValues(element, element.type.toString().toLowerCase()),
                    DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
                    arrayOf(id.toString())).toLong()


    override fun delete(element: CatalogData): String = if (element.databaseId == "") "" else delete(element.databaseId)


    override fun delete(id: String): String = dbReadWriteOnlyConn.delete(DBConstants.TABLE_CATALOG,
            DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
            arrayOf(id)).toString()

    override fun delete(id: Long): Long = dbReadWriteOnlyConn.delete(DBConstants.TABLE_CATALOG,
            DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
            arrayOf(id.toString())).toLong()

    override fun deleteAll(): Boolean = dbReadWriteOnlyConn.delete(DBConstants.TABLE_CATALOG,
            null,
            null).toLong() >= 0


}