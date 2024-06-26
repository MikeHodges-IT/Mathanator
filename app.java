package app.math.com.mathanador;

import android.app.Application;

/**
 * The app class extends the Android Application class.
 * This class is used to maintain global application state and contains user ID related methods.
 */
public class app extends Application {
    // Variable to store the user ID
    private long user_id;

    /**
     * Gets the user ID.
     * @return The user ID.
     */
    public long getUserID() {
        return user_id;
    }

    /**
     * Sets the user ID.
     * @param l The new user ID to be set.
     */
    public void setUserID(long l) {
        user_id = l;
    }
}