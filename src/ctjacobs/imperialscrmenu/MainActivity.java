package ctjacobs.imperialscrmenu;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.*;
import java.io.*;

import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /* Update the title text with the current date. */
        TextView textTitle = (TextView) this.findViewById(R.id.textTitle);
        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMM");
        Date date = new Date();
        textTitle.setText("Menu for " + dateFormat.format(date));
     
        return;
    }

    /** Retrieve the menu from the SCR's webpage. */
    public void getMenu()
    {
        try
        {
            URL url = new URL(this.getString(R.string.menu_url));
            InputStreamReader stream = new InputStreamReader(url.openStream());
        } 
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return;
    }
}
