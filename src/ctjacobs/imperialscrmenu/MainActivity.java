package ctjacobs.imperialscrmenu;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.*;
import java.io.*;
import java.util.regex.*;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;

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

        /* Retrieve and parse the menu */
        String menu = (String) this.getMenu();
        TextView textMenu = (TextView) this.findViewById(R.id.textMenu);
        String day = new String("Monday");
        
        /* Display the menu to the user */
        textMenu.setText(this.parseMenu(menu, day));

        return;
    }

    /** Retrieve the menu from the SCR's webpage. */
    private String getMenu()
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
            String menu_page_title = this.getString(R.string.menu_page_title);
            while((line = reader.readLine()) != null)
            {
                if(line.contains(menu_page_title))
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
    
    private String parseMenu(String menu, String day)
    {
        return Html.fromHtml(menu).toString();
    }
}
