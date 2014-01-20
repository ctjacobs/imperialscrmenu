package ctjacobs.imperialscrmenu;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.*;
import java.io.*;
import java.util.regex.*;

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

        String text = (String) this.getMenu();
        System.out.println(text);
        
        return;
    }

    /** Retrieve the menu from the SCR's webpage. */
    public String getMenu()
    {
        try
        {
            /* Open a connection to the SCR menu's website */
            URL url = new URL(this.getString(R.string.menu_page_url));
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible)");
            connection.setRequestProperty("Accept", "*/*");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            /* Searches for the portion of the HTML that contains the menu title. */
            String line;
            Pattern pattern = Pattern.compile(this.getString(R.string.menu_page_url));
            Matcher matcher;
            while((line = reader.readLine()) != null)
            {
                matcher = pattern.matcher(line);
                if(matcher.find())
                {
                    return line.toString();
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
