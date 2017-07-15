package at.com.dbupgrade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Creted by User on 14-Jul-17
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String name = "dbSample";
    private static int version = 2;//CHANGE VERSION TO RUN onUpgrade METHOD
    private String tbName = "tbName";
    private SQLiteDatabase sqLiteDatabase;

    public DataBaseHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + tbName + "(" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            sqLiteDatabase.execSQL("ALTER TABLE " + tbName + " ADD  COLUMN roll INTEGER DEFAULT 0");
        }
    }

    public boolean insert(String name) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        long id = sqLiteDatabase.insert(tbName, null, cv);
        sqLiteDatabase.close();
        return id != -1;
    }

    public String[] getColumnName() {
        sqLiteDatabase = this.getReadableDatabase();
        String selectRow = "SELECT * FROM " + tbName;
        Cursor cursor = sqLiteDatabase.rawQuery(selectRow, null);
        String[] columName = new String[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            columName[i] = cursor.getColumnName(i);
        }
        cursor.close();
        sqLiteDatabase.close();
        return columName;
    }

    public Student[] getStudentName() {
        sqLiteDatabase = this.getReadableDatabase();
        String selectRow = "SELECT * FROM " + tbName;
        Cursor cursor = sqLiteDatabase.rawQuery(selectRow, null);
        Student[] students = new Student[cursor.getCount()];
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                int i = 0;
                do {
                    Student student = new Student();
                    student.setnId(cursor.getInt(0));
                    student.setsName(cursor.getString(1));
                    if (cursor.getColumnCount() > 2) {
                        student.setnRollNo(cursor.getInt(2));
                    }
                    students[i] = student;
                    i++;

                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return students;
    }
}
