package app.math.com.mathanador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

/**
 * Created by Mike on 11/23/2014.
 */
public class GameData {
    private static final int    DATABASE_VERSION =  5;
    private static final String DATABASE_NAME    = "GameData_db";

    private static final String TABLE_USER                          = "userTable";
    private static final String TABLE_GAME_TYPE                     = "gameTable";
    private static final String TABLE_SCORES                        = "scoresTable";
    private static final String TABLE_ACHIEVEMENTS_TYPE             = "achievementsTypeTable";
    private static final String TABLE_USER_ACHIEVEMENTS             = "userAchievementsTable";
    private static final String TABLE_MOST_MISSED                   = "mostMissedTable";


    // Common                  Columns names
    public static final String KEY_ROW_ID                            = "_id";
    // USER           Table    Columns names
    public static final String KEY_USERS_NAME                        = "user";
     // GAME TYPE     Table    Columns Names
    public static final String KEY_GAMES_TYPE_ID                     = "games_Id";
    public static final String KEY_GAMES_TYPE_NAME                   = "games";
    // SCORES         Table    Columns Names
    public static final String KEY_SCORES_USER_ID_FK                 = "scores_user_ID_FK";
    public static final String KEY_SCORES_GAME_TYPE_ID_FK            = "scores_game_type_FK";
    public static final String KEY_SCORES_GAME_LEVEL                 = "scores_game_level";
    public static final String KEY_SCORES_SCORE                      = "scores_score";
    // ACHIEVEMENT    Table    Columns Names
    public static final String KEY_ACHIEVEMENTS_TYPE_ID              = "achievements_type_ID";
    public static final String KEY_ACHIEVEMENTS_TYPE                 = "achievements_type";
    // USER ACHIEVEMENT    Table    Columns Names
    public static final String KEY_USER_ACHIEVEMENTS_USER_ID_FK      = "user_achievements_user_ID-FK";
    public static final String KEY_USER_ACHIEVEMENTS_TYPE_ID_FK      = "user_achievements_type_FK";
    // MOST_MISSED   Table    Columns Names
    public static final String KEY_MOST_MISSED_USER_ID_FK            = "most_missed-user_ID_FK";
    public static final String KEY_MOST_MISSED_GAME_ID_FK            = "most_missed_game_ID_FK";
    public static final String KEY_MOST_MISSED_USER_GAME_LEVEL       = "most_missed_user_game_level";
    public static final String KEY_MOST_MISSED_USER_MISSED_QUESTION  = "most_missed_question";




    //Table create statements
    //Create name Table
    public static final String CREATE_TABLE_USERS = "CREATE TABLE "
            +TABLE_USER + "(" +
            KEY_ROW_ID                            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_USERS_NAME                        + " TEXT NOT NULL "+")";

    public static final String CREATE_TABLE_GAME_TYPE = "CREATE TABLE "
            +TABLE_GAME_TYPE + "(" +
            KEY_ROW_ID                            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_GAMES_TYPE_ID                     + " INTEGER NOT NULL ,"+
            KEY_GAMES_TYPE_NAME                   + " TEXT NOT NULL" + ")";

    public static final String CREATE_TABLE_SCORES = "CREATE TABLE "
            +TABLE_SCORES + "(" +
            KEY_ROW_ID                            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_SCORES_USER_ID_FK                 + " INTEGER NOT NULL ,"+
            KEY_SCORES_GAME_TYPE_ID_FK            + " INTEGER NOT NULL ,"+
            KEY_SCORES_GAME_LEVEL                 + " INTEGER NOT NULL ,"+
            KEY_SCORES_SCORE                      + " INTEGER NOT NULL  "+")";

    public static final String CREATE_TABLE_ACHIEVEMENTS_TYPE = "CREATE TABLE "
            +TABLE_ACHIEVEMENTS_TYPE + "(" +
            KEY_ROW_ID                            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_ACHIEVEMENTS_TYPE_ID              + " INTEGER NOT NULL ,"+
            KEY_ACHIEVEMENTS_TYPE                 + " STRING  NOT NULL ,"+")";

    public static final String CREATE_TABLE_USER_ACHIEVEMENTS  = "CREATE TABLE "
            +TABLE_USER_ACHIEVEMENTS  + "(" +
            KEY_ROW_ID                            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_USER_ACHIEVEMENTS_USER_ID_FK      + " INTEGER NOT NULL ,"+
            KEY_USER_ACHIEVEMENTS_TYPE_ID_FK      + " INTEGER NOT NULL ,"+")";

    public static final String CREATE_TABLE_MOST_MISSED = "CREATE TABLE "
            +TABLE_MOST_MISSED + "(" +
            KEY_MOST_MISSED_USER_ID_FK            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_MOST_MISSED_GAME_ID_FK            + " INTEGER NOT NULL ,"+
            KEY_MOST_MISSED_USER_GAME_LEVEL       + " INTEGER NOT NULL ,"+
            KEY_MOST_MISSED_USER_MISSED_QUESTION  + " INTEGER NOT NULL ,"+")";

     private DBHelper dbHelper;
     private final Context appContext;
     private SQLiteDatabase appDatabase;

    public GameData(Context c){
        appContext = c;
    }

    public int getGameDataHighestCompleted(long key_id_user) {
        String sql = "Select "+ KEY_SCORES_GAME_LEVEL + " From "+TABLE_SCORES+
                " Where  "+ KEY_SCORES_USER_ID_FK + " = " + key_id_user +
                " ORDER BY "+ KEY_SCORES_GAME_LEVEL + " DESC ";

        Log.i("sql = ",sql);


        Cursor c =  appDatabase.rawQuery(sql,null);

        Log.i("c.getCount"," = " + c.getCount());


        if(c.getCount() > 0) {
            c.moveToFirst();
            Log.i("return c.getInt(c.getColumnIndex(KEY_SCORES_GAME_LEVEL)", " = " +c.getInt(c.getColumnIndex(KEY_SCORES_GAME_LEVEL)));
            return c.getInt(c.getColumnIndex(KEY_SCORES_GAME_LEVEL));
        }else{

            return 0;
        }
    }







































    private static class DBHelper extends SQLiteOpenHelper{


        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_TABLE_USERS );
            db.execSQL(CREATE_TABLE_GAME_TYPE );
            db.execSQL(CREATE_TABLE_SCORES );

            // TABLE_ACHIEVEMENTS
            // TABLE_USER_ACHIEVEMENTS
            // TABLE_MOST_MISSED

            // initialValues
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_GAMES_TYPE_ID,1);
            initialValues.put(KEY_GAMES_TYPE_NAME,"Multiplication");
            db.insert(TABLE_GAME_TYPE,null,initialValues);
            initialValues.clear();
            initialValues.put(KEY_GAMES_TYPE_ID,2);
            initialValues.put(KEY_GAMES_TYPE_NAME,"Division");
            db.insert(TABLE_GAME_TYPE,null,initialValues);
            initialValues.clear();
            initialValues.put(KEY_GAMES_TYPE_ID,3);
            initialValues.put(KEY_GAMES_TYPE_NAME,"Comprehension");
            db.insert(TABLE_GAME_TYPE,null,initialValues);
            initialValues.clear();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_GAME_TYPE);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_SCORES);

               // TABLE_ACHIEVEMENTS
               // TABLE_USER_ACHIEVEMENTS
               // TABLE_MOST_MISSED

            onCreate(db);


        }
    }


    public GameData open(){

        dbHelper = new DBHelper(appContext);
        appDatabase = dbHelper.getWritableDatabase() ;
        return this;

    }
    public void close(){
        dbHelper.close();

    }





    public long createEntry(String name) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_USERS_NAME, name);
        return appDatabase.insert(TABLE_USER,null,cv);
    }





    public String[][] getUserArray() {

        String[] columns = new String[]{KEY_ROW_ID,KEY_USERS_NAME};
        Cursor c = appDatabase.query(TABLE_USER,columns,null,null,null,null,null);

        String[][] result   = new String[c.getCount()][2];
        int iRow  = c.getColumnIndex(KEY_ROW_ID);
        int iName = c.getColumnIndex(KEY_USERS_NAME );
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result[c.getPosition()] [0]= c.getString(iRow);
            result[c.getPosition()] [1]= c.getString(iName);
            //result = result +c.getString(iRow) + " "+ c.getString(iName)+"\n";
        }
        return result;
    }

    public String getName(long l) {
        String[] columns = new String[]{KEY_ROW_ID,KEY_USERS_NAME};
        Cursor c = appDatabase.query(TABLE_USER,columns,KEY_ROW_ID +" = " + l, null,null,null,null);
        if(c != null){
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        }
        return null;
    }

    public void updateUserName(long key_user_id, String sName) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_USERS_NAME,sName);
        appDatabase.update(TABLE_USER, cvUpdate, KEY_ROW_ID + " = " + key_user_id, null);
    }

    public void deleteUser(long key_user_id) {
        appDatabase.delete(TABLE_USER, KEY_ROW_ID + " = " + key_user_id, null);
        appDatabase.delete(TABLE_SCORES, KEY_SCORES_USER_ID_FK + " = " + key_user_id, null);


    }


    public void saveGameDataScore(long userId, int gameType, int gameLevel,  long score) {

        String sql = "Select "+ KEY_ROW_ID +" , " + KEY_SCORES_SCORE + " From "+TABLE_SCORES+
                    " Where  "+ KEY_SCORES_GAME_TYPE_ID_FK + " = " + gameType  +
                       " AND "+ KEY_SCORES_GAME_LEVEL      + " = " + gameLevel +
                       " AND "+ KEY_SCORES_USER_ID_FK      + " = " + userId;

        Log.i("sql = ",sql);

        Cursor c =  appDatabase.rawQuery(sql,null);
        Log.i("sql = ",sql);
        if(c.moveToFirst() && score < c.getInt(c.getColumnIndex(KEY_SCORES_SCORE) ) ){
            Log.i("moveToFirst = ","true");
            Log.i("Score ","is less and should be updated @ ID = " +KEY_ROW_ID );

            ContentValues cvUpdate = new ContentValues();
            cvUpdate.put(KEY_SCORES_SCORE            ,score     );
            appDatabase.update(TABLE_SCORES, cvUpdate, KEY_ROW_ID + " = " + c.getInt(c.getColumnIndex(KEY_ROW_ID)), null);

        }
        else if (!c.moveToFirst()){
            Log.i("moveToFirst = ","False");
            Log.i("Score ","is new Score and should be insert " );

            ContentValues cv  = new ContentValues();
            cv.put(KEY_SCORES_USER_ID_FK,userId);
            cv.put(KEY_SCORES_GAME_TYPE_ID_FK, gameType);
            cv.put(KEY_SCORES_GAME_LEVEL, gameLevel);
            cv.put(KEY_SCORES_SCORE,score);
            appDatabase.insert(TABLE_SCORES, null, cv);
         }





    }

    public String[][] getScoreArray(Long user_id) {



        Log.i("getScoreArray User ID ",""+user_id);

        String sql = " SELECT GT."+ KEY_GAMES_TYPE_NAME +" , S." + KEY_SCORES_GAME_LEVEL +" , S." + KEY_SCORES_SCORE +
                     " FROM "     + TABLE_SCORES  +" S INNER JOIN "+TABLE_GAME_TYPE +" GT"+
                     " WHERE "    + "S."+KEY_SCORES_GAME_TYPE_ID_FK + " = GT." + KEY_GAMES_TYPE_ID  +
                     " AND "      + "S."+KEY_SCORES_USER_ID_FK      + " = " + user_id  +
                     " ORDER BY " + KEY_GAMES_TYPE_NAME +","+ KEY_SCORES_GAME_LEVEL +" ASC";


        //sql ="Select * From "+TABLE_GAME_TYPE;
        Log.i("sql = ",sql);

        Cursor c =  appDatabase.rawQuery(sql,null);

        Log.i("getScoreArray get count",""+c.getCount());


        String[][] result   = new String[c.getCount()][3];

        int iGame   = c.getColumnIndex(KEY_GAMES_TYPE_NAME);
        int iLevel  = c.getColumnIndex(KEY_SCORES_GAME_LEVEL);
        int iScore  = c.getColumnIndex(KEY_SCORES_SCORE);


        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){

            result[c.getPosition()] [0]= c.getString(iGame);
            result[c.getPosition()] [1]= c.getString(iLevel);
            result[c.getPosition()] [2]= c.getString(iScore);

                    }
        return result;
        //KEY_SCORES_USER_ID_FK
        //KEY_SCORES_GAME_TYPE_ID_FK
        //KEY_SCORES_GAME_LEVEL
        //KEY_SCORES_SCORE

    }
    public String[][] getLeadersArray() {


        Log.i("getLeadersArray "," Started");

        String sql = " SELECT GT."+ KEY_GAMES_TYPE_NAME +" , S." + KEY_SCORES_GAME_LEVEL +" , S." + KEY_SCORES_SCORE +" , U." + KEY_USERS_NAME +
                " FROM "     + TABLE_SCORES  +" S INNER JOIN "+TABLE_GAME_TYPE +" GT INNER JOIN " + TABLE_USER +" U "+
                " WHERE "    + "GT."+KEY_GAMES_TYPE_ID + " = S." + KEY_SCORES_GAME_TYPE_ID_FK  +
                " AND "      + "S."+KEY_SCORES_USER_ID_FK      + " = U."   +KEY_ROW_ID +
                " ORDER BY GT." + KEY_GAMES_TYPE_NAME +" , S."+ KEY_SCORES_GAME_LEVEL +" ,S." +KEY_SCORES_SCORE +" ASC";


        //sql ="Select * From "+TABLE_GAME_TYPE;
        Log.i("sql = ",sql);

        Cursor c =  appDatabase.rawQuery(sql,null);

        Log.i("getScoreArray get count",""+c.getCount());


        String[][] result   = new String[c.getCount()][4];

        int iGame   = c.getColumnIndex(KEY_GAMES_TYPE_NAME);
        int iLevel  = c.getColumnIndex(KEY_SCORES_GAME_LEVEL);
        int iScore  = c.getColumnIndex(KEY_SCORES_SCORE);
        int iUser   = c.getColumnIndex(KEY_USERS_NAME);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){

            result[c.getPosition()] [0]= c.getString(iGame);
            result[c.getPosition()] [1]= c.getString(iLevel);
            result[c.getPosition()] [2]= c.getString(iScore);
            result[c.getPosition()] [3]= c.getString(iUser);

        }
        return result;
        //KEY_SCORES_USER_ID_FK
        //KEY_SCORES_GAME_TYPE_ID_FK
        //KEY_SCORES_GAME_LEVEL
        //KEY_SCORES_SCORE




    }

}
