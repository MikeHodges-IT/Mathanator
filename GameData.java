package app.math.com.mathanador;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

/**
 * Handles the creation, upgrade, and management of the database for the MathANador app. This includes
 * defining the schema for tables related to users, game types, scores, achievements, and most missed questions.
 * It provides methods to insert, update, and query data, supporting the app's functionality in tracking
 * user progress, game statistics, and achievements.
 * Created by Mike on 11/23/2014.
 */
public class GameData {
    // Database version; update this number for schema changes
    private static final int DATABASE_VERSION = 5;

    // Database name
    private static final String DATABASE_NAME = "GameData_db";

    // Table names
    private static final String TABLE_USER = "userTable"; // Stores user information
    private static final String TABLE_GAME_TYPE = "gameTable"; // Stores different game types
    private static final String TABLE_SCORES = "scoresTable"; // Stores scores for games
    private static final String TABLE_ACHIEVEMENTS_TYPE = "achievementsTypeTable"; // Stores types of achievements
    private static final String TABLE_USER_ACHIEVEMENTS = "userAchievementsTable"; // Stores achievements earned by users
    private static final String TABLE_MOST_MISSED = "mostMissedTable"; // Stores questions most often missed by users

    // Common Column Names
    public static final String KEY_ROW_ID = "_id";
    // USER Table Column Names
    public static final String KEY_USERS_NAME = "user";
    // GAME TYPE Table Column Names
    public static final String KEY_GAMES_TYPE_ID = "games_Id";
    public static final String KEY_GAMES_TYPE_NAME = "games";
    // SCORES Table Column Names
    public static final String KEY_SCORES_USER_ID_FK = "scores_user_ID_FK";
    public static final String KEY_SCORES_GAME_TYPE_ID_FK = "scores_game_type_FK";
    public static final String KEY_SCORES_GAME_LEVEL = "scores_game_level";
    public static final String KEY_SCORES_SCORE = "scores_score";
    // ACHIEVEMENT Table Column Names
    public static final String KEY_ACHIEVEMENTS_TYPE_ID = "achievements_type_ID";
    public static final String KEY_ACHIEVEMENTS_TYPE = "achievements_type";
    // USER ACHIEVEMENT Table Column Names
    public static final String KEY_USER_ACHIEVEMENTS_USER_ID_FK = "user_achievements_user_ID-FK";
    public static final String KEY_USER_ACHIEVEMENTS_TYPE_ID_FK = "user_achievements_type_FK";
    // MOST MISSED Table Column Names
    public static final String KEY_MOST_MISSED_USER_ID_FK = "most_missed-user_ID_FK";
    public static final String KEY_MOST_MISSED_GAME_ID_FK = "most_missed_game_ID_FK";
    public static final String KEY_MOST_MISSED_USER_GAME_LEVEL = "most_missed_user_game_level";
    public static final String KEY_MOST_MISSED_USER_MISSED_QUESTION = "most_missed_question";

    // Table creation statements for the database

    // SQL statement to create the users table
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " +
        TABLE_USER + "(" +
        KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        KEY_USERS_NAME + " TEXT NOT NULL)";

    // SQL statement to create the game type table
    public static final String CREATE_TABLE_GAME_TYPE = "CREATE TABLE " +
        TABLE_GAME_TYPE + "(" +
        KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        KEY_GAMES_TYPE_ID + " INTEGER NOT NULL," +
        KEY_GAMES_TYPE_NAME + " TEXT NOT NULL)";

    // SQL statement to create the scores table
    public static final String CREATE_TABLE_SCORES = "CREATE TABLE " +
        TABLE_SCORES + "(" +
        KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        KEY_SCORES_USER_ID_FK + " INTEGER NOT NULL," +
        KEY_SCORES_GAME_TYPE_ID_FK + " INTEGER NOT NULL," +
        KEY_SCORES_GAME_LEVEL + " INTEGER NOT NULL," +
        KEY_SCORES_SCORE + " INTEGER NOT NULL)";

    // SQL statement to create the achievements type table
    public static final String CREATE_TABLE_ACHIEVEMENTS_TYPE = "CREATE TABLE " +
        TABLE_ACHIEVEMENTS_TYPE + "(" +
        KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        KEY_ACHIEVEMENTS_TYPE_ID + " INTEGER NOT NULL," +
        KEY_ACHIEVEMENTS_TYPE + " TEXT NOT NULL)";

    // SQL statement to create the user achievements table
    public static final String CREATE_TABLE_USER_ACHIEVEMENTS = "CREATE TABLE " +
        TABLE_USER_ACHIEVEMENTS + "(" +
        KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        KEY_USER_ACHIEVEMENTS_USER_ID_FK + " INTEGER NOT NULL," +
        KEY_USER_ACHIEVEMENTS_TYPE_ID_FK + " INTEGER NOT NULL)";

    // SQL statement to create the most missed questions table
    public static final String CREATE_TABLE_MOST_MISSED = "CREATE TABLE " +
        TABLE_MOST_MISSED + "(" +
        KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        KEY_MOST_MISSED_USER_ID_FK + " INTEGER NOT NULL," +
        KEY_MOST_MISSED_GAME_ID_FK + " INTEGER NOT NULL," +
        KEY_MOST_MISSED_USER_GAME_LEVEL + " INTEGER NOT NULL," +
        KEY_MOST_MISSED_USER_MISSED_QUESTION + " TEXT NOT NULL)";

    // Database helper and context for managing database creation and version management
    private DBHelper dbHelper;
    private final Context appContext;
    private SQLiteDatabase appDatabase;

    // Constructor for GameData, initializes context
    public GameData(Context c) {
        appContext = c;
    }
    public int getGameDataHighestCompleted(long key_id_user) {
        // SQL query to find the highest game level completed by a user
        String sql = "Select "+ KEY_SCORES_GAME_LEVEL + " From "+TABLE_SCORES+
                " Where  "+ KEY_SCORES_USER_ID_FK + " = " + key_id_user +
                " ORDER BY "+ KEY_SCORES_GAME_LEVEL + " DESC ";

        // Log the SQL query for debugging
        Log.i("sql = ",sql);

        // Execute the query
        Cursor c =  appDatabase.rawQuery(sql,null);

        // Log the number of rows returned
        Log.i("c.getCount"," = " + c.getCount());

        // Check if the query returned any rows
        if(c.getCount() > 0) {
            c.moveToFirst(); // Move to the first row
            // Log and return the highest game level completed
            Log.i("return c.getInt(c.getColumnIndex(KEY_SCORES_GAME_LEVEL)", " = " +c.getInt(c.getColumnIndex(KEY_SCORES_GAME_LEVEL)));
            return c.getInt(c.getColumnIndex(KEY_SCORES_GAME_LEVEL));
        }else{
            // Return 0 if no rows were returned (user has not completed any levels)
            return 0;
        }
    }
     
// DBHelper is a static inner class that extends SQLiteOpenHelper. It is used to manage database creation and version management.
private static class DBHelper extends SQLiteOpenHelper{

    // Constructor for DBHelper. It calls the superclass constructor with the context, database name, and database version.
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    // onCreate is called when the database is created for the first time. This is where the creation of tables and the initial population of the tables should happen.
    public void onCreate(SQLiteDatabase db) {
        // Execute SQL statements to create the user, game type, and scores tables.
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_GAME_TYPE);
        db.execSQL(CREATE_TABLE_SCORES);

        // Prepare initial values for the game types table.
        ContentValues initialValues = new ContentValues();
        // Insert "Multiplication" as a game type.
        initialValues.put(KEY_GAMES_TYPE_ID,1);
        initialValues.put(KEY_GAMES_TYPE_NAME,"Multiplication");
        db.insert(TABLE_GAME_TYPE,null,initialValues);
        
        initialValues.clear(); // Clear ContentValues for reuse.

        // Insert "Division" as a game type.
        initialValues.put(KEY_GAMES_TYPE_ID,2);
        initialValues.put(KEY_GAMES_TYPE_NAME,"Division");
        db.insert(TABLE_GAME_TYPE,null,initialValues);
        
        initialValues.clear(); // Clear ContentValues for reuse.

        // Insert "Comprehension" as a game type.
        initialValues.put(KEY_GAMES_TYPE_ID,3);
        initialValues.put(KEY_GAMES_TYPE_NAME,"Comprehension");
        db.insert(TABLE_GAME_TYPE,null,initialValues);
        // Note: initialValues.clear() is not necessary here as it's the last use.
    }

    @Override
    // onUpgrade is called when the database needs to be upgraded. This method will only be called if a database already exists on disk with the same DATABASE_NAME, but the DATABASE_VERSION is different.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing tables.
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_GAME_TYPE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SCORES);

        // Create new tables.
        onCreate(db);
    }
}
    // Opens the database connection.
    public GameData open(){
        // Initialize DBHelper with the application context.
        dbHelper = new DBHelper(appContext);
        // Get a writable database instance for data insertion or update.
        appDatabase = dbHelper.getWritableDatabase();
        // Return the current instance of GameData to allow for method chaining.
        return this;
    }

    // Closes the database connection.
    public void close(){
        // Close the DBHelper, which in turn closes the database connection.
        dbHelper.close();
    }

    // Creates a new entry in the users table.
    public long createEntry(String name) {
        // Create a new ContentValues object to hold the data to be inserted.
        ContentValues cv = new ContentValues();
        // Put the user's name into the ContentValues.
        cv.put(KEY_USERS_NAME, name);
        // Insert the data into the users table and return the row ID of the newly inserted row, or -1 if an error occurred.
        return appDatabase.insert(TABLE_USER,null,cv);
    }


public String[][] getUserArray() {
    // Define the columns to retrieve from the user table
    String[] columns = new String[]{KEY_ROW_ID, KEY_USERS_NAME};
    // Query the database to retrieve all rows from the user table
    Cursor c = appDatabase.query(TABLE_USER, columns, null, null, null, null, null);

    // Initialize a 2D array to hold the result of the query
    String[][] result = new String[c.getCount()][2];
    // Get the index of the ID and Name columns
    int iRow = c.getColumnIndex(KEY_ROW_ID);
    int iName = c.getColumnIndex(KEY_USERS_NAME);

    // Iterate over the cursor to populate the result array
    for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
        // Store the row ID in the first column of the result array
        result[c.getPosition()][0] = c.getString(iRow);
        // Store the user name in the second column of the result array
        result[c.getPosition()][1] = c.getString(iName);
    }
    // Return the result array
    return result;
}

public String getName(long id) {
        // Define the columns to retrieve from the user table
        String[] columns = new String[]{KEY_ROW_ID, KEY_USERS_NAME};
        // Query the database for a user with the specified ID
        Cursor c = appDatabase.query(TABLE_USER, columns, KEY_ROW_ID + " = " + id, null, null, null, null);
        
        // Check if the cursor is not null and has at least one row
        if (c != null && c.moveToFirst()) {
            // Retrieve the user's name from the second column of the cursor
            String name = c.getString(1);
            c.close(); // Close the cursor to release resources
            return name; // Return the retrieved name
        }
        // If the user was not found, return null
        return null;
    }

    public void updateUserName(long keyUserId, String name) {
        // Create a new ContentValues object to hold the updated user name
        ContentValues cvUpdate = new ContentValues();
        // Put the new name into the ContentValues
        cvUpdate.put(KEY_USERS_NAME, name);
        // Update the user's name in the database where the row ID matches the specified user ID
        appDatabase.update(TABLE_USER, cvUpdate, KEY_ROW_ID + " = " + keyUserId, null);
    }

    // Method to delete a user and all related data from the database
    public void deleteUser(long keyUserId) {
        // Delete the user from the user table where the row ID matches the specified user ID
        appDatabase.delete(TABLE_USER, KEY_ROW_ID + " = " + keyUserId, null);
        // Delete the user's scores from the scores table where the user ID matches the specified user ID
        appDatabase.delete(TABLE_SCORES, KEY_SCORES_USER_ID_FK + " = " + keyUserId, null);
    }


    // Method to save or update the game data score for a user
    public void saveGameDataScore(long userId, int gameType, int gameLevel, long score) {
        // Construct SQL query to find existing score for the given user, game type, and game level
        String sql = "SELECT " + KEY_ROW_ID + ", " + KEY_SCORES_SCORE + " FROM " + TABLE_SCORES +
                     " WHERE " + KEY_SCORES_GAME_TYPE_ID_FK + " = " + gameType +
                     " AND " + KEY_SCORES_GAME_LEVEL + " = " + gameLevel +
                     " AND " + KEY_SCORES_USER_ID_FK + " = " + userId;

        // Log the SQL query for debugging purposes
        Log.i("sql = ", sql);

        // Execute the query
        Cursor c = appDatabase.rawQuery(sql, null);

        // Check if there is an existing score for the criteria
        if (c.moveToFirst()) {
            // If the new score is less than the existing score, update the score
            if (score < c.getInt(c.getColumnIndex(KEY_SCORES_SCORE))) {
                Log.i("moveToFirst = ", "true");
                Log.i("Score ", "is less and should be updated @ ID = " + KEY_ROW_ID);

                // Prepare the new score for update
                ContentValues cvUpdate = new ContentValues();
                cvUpdate.put(KEY_SCORES_SCORE, score);

                // Update the score in the database
                appDatabase.update(TABLE_SCORES, cvUpdate, KEY_ROW_ID + " = " + c.getInt(c.getColumnIndex(KEY_ROW_ID)), null);
            }
        } else {
            // If there is no existing score, insert the new score
            Log.i("moveToFirst = ", "False");
            Log.i("Score ", "is new Score and should be insert");

            // Prepare the new score for insertion
            ContentValues cv = new ContentValues();
            cv.put(KEY_SCORES_USER_ID_FK, userId);
            cv.put(KEY_SCORES_GAME_TYPE_ID_FK, gameType);
            cv.put(KEY_SCORES_GAME_LEVEL, gameLevel);
            cv.put(KEY_SCORES_SCORE, score);

            // Insert the new score into the database
            appDatabase.insert(TABLE_SCORES, null, cv);
        }

        // Close the cursor to release resources
        c.close();
    }

    public String[][] getScoreArray(Long user_id) {
        // Log the user ID for debugging
        Log.i("getScoreArray User ID ", "" + user_id);

        // Construct SQL query to fetch scores, game levels, and game types for a specific user
        String sql = "SELECT GT." + KEY_GAMES_TYPE_NAME + ", S." + KEY_SCORES_GAME_LEVEL + ", S." + KEY_SCORES_SCORE +
                     " FROM " + TABLE_SCORES + " S INNER JOIN " + TABLE_GAME_TYPE + " GT" +
                     " ON S." + KEY_SCORES_GAME_TYPE_ID_FK + " = GT." + KEY_GAMES_TYPE_ID +
                     " WHERE S." + KEY_SCORES_USER_ID_FK + " = " + user_id +
                     " ORDER BY " + KEY_GAMES_TYPE_NAME + ", " + KEY_SCORES_GAME_LEVEL + " ASC";

        // Log the SQL query for debugging
        Log.i("sql = ", sql);

        // Execute the query
        Cursor c = appDatabase.rawQuery(sql, null);

        // Log the number of rows returned by the query
        Log.i("getScoreArray get count", "" + c.getCount());

        // Initialize a 2D array to hold the query results
        String[][] result = new String[c.getCount()][3];

        // Get column indexes
        int iGame = c.getColumnIndex(KEY_GAMES_TYPE_NAME);
        int iLevel = c.getColumnIndex(KEY_SCORES_GAME_LEVEL);
        int iScore = c.getColumnIndex(KEY_SCORES_SCORE);

        // Iterate through the cursor to populate the result array
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result[c.getPosition()][0] = c.getString(iGame);   // Game type name
            result[c.getPosition()][1] = c.getString(iLevel);  // Game level
            result[c.getPosition()][2] = c.getString(iScore);  // Score
        }

        // Return the populated result array
        return result;
    }

    public String[][] getLeadersArray() {
        // Log the start of the getLeadersArray method
        Log.i("getLeadersArray", "Started");

        // Construct SQL query to fetch game type name, game level, score, and user name
        // for all scores, joining the scores, game type, and user tables
        String sql = "SELECT GT." + KEY_GAMES_TYPE_NAME + ", S." + KEY_SCORES_GAME_LEVEL + ", S." + KEY_SCORES_SCORE + ", U." + KEY_USERS_NAME +
                     " FROM " + TABLE_SCORES + " S INNER JOIN " + TABLE_GAME_TYPE + " GT INNER JOIN " + TABLE_USER + " U " +
                     "WHERE GT." + KEY_GAMES_TYPE_ID + " = S." + KEY_SCORES_GAME_TYPE_ID_FK +
                     " AND S." + KEY_SCORES_USER_ID_FK + " = U." + KEY_ROW_ID +
                     " ORDER BY GT." + KEY_GAMES_TYPE_NAME + ", S." + KEY_SCORES_GAME_LEVEL + ", S." + KEY_SCORES_SCORE + " ASC";

        // Log the SQL query
        Log.i("sql = ", sql);

        // Execute the query
        Cursor c = appDatabase.rawQuery(sql, null);

        // Log the number of rows returned by the query
        Log.i("getScoreArray get count", "" + c.getCount());

        // Initialize a 2D array to hold the query results
        String[][] result = new String[c.getCount()][4];

        // Get column indexes
        int iGame = c.getColumnIndex(KEY_GAMES_TYPE_NAME);
        int iLevel = c.getColumnIndex(KEY_SCORES_GAME_LEVEL);
        int iScore = c.getColumnIndex(KEY_SCORES_SCORE);
        int iUser = c.getColumnIndex(KEY_USERS_NAME);

        // Iterate through the cursor to populate the result array
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result[c.getPosition()][0] = c.getString(iGame);   // Game type name
            result[c.getPosition()][1] = c.getString(iLevel);  // Game level
            result[c.getPosition()][2] = c.getString(iScore);  // Score
            result[c.getPosition()][3] = c.getString(iUser);   // User name
        }

        // Return the populated result array
        return result;
    }

}
