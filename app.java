package app.math.com.mathanador;

import android.app.Application;

/**
 * Created by Mike on 11/26/2014.
 */
public class app extends Application{
private long user_id;
    public long getUserID(){
        return user_id;
    }
    public void  setUserID(long l){
        user_id = l;
    }
}

