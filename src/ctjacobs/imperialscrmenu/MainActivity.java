package ctjacobs.imperialscrmenu;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.util.*;

import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        textTitle.setTextAppearance(this, android.R.style.TextAppearance_Large);
        textTitle.setText("Menu for " + dateFormat.format(date));

        /* Retrieve and parse the menu */
        String menu = (String) this.getMenu();
        TextView textMenu = (TextView) this.findViewById(R.id.textMenu);
        String day = new String("Monday");
        
        /* Display the menu to the user */
        List<String> foodChoices = this.parseMenu(menu, day);
        for(int i = 0; i < foodChoices.size(); i++) 
        {
            String choice = foodChoices.get(i);
            textMenu.append(choice);
            textMenu.append("\n\n");
        }
        
        if(isEarly())
        {
           AlertDialog.Builder builder = new AlertDialog.Builder(this);
           builder.setMessage("You are accessing the menu before 10 am. It might therefore be out-of-date.");
           builder.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() 
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) 
                                        {
                                            dialog.dismiss();
                                        }
                                    });
           AlertDialog dialog = builder.create();
           dialog.show();
        }
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
            String menuPageTitle = this.getString(R.string.menu_page_title);
            while((line = reader.readLine()) != null)
            {
                if(line.contains(menuPageTitle))
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
    
    private List<String> parseMenu(String menu, String day)
    {
        /** Grab all the food choices from the current day's menu and strip all the HTML tags */
        String regex = Pattern.quote("Monday") + "(.*?)" + Pattern.quote("Tuesday");
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(menu);
        String menuOfTheDay = "";
        List<String> foodChoices = new ArrayList<String>();
        if(m.find()) 
        {
           menuOfTheDay = m.group(1);
           System.out.println(menuOfTheDay);
           regex = Pattern.quote("<li>") + "(.*?)" + Pattern.quote("</li>");
           p = Pattern.compile(regex);
           m = p.matcher(menuOfTheDay);
           while(m.find())
           {
               System.out.println(m.group(1));
               foodChoices.add(Html.fromHtml(m.group(1)).toString());
           }
        }
        return foodChoices;
    }
    
    private Boolean isEarly()
    {
        /** Warn the user that the menu might not be up-to-date yet, if it is before 10 am. */
        Calendar present = Calendar.getInstance();
        if(present.get(Calendar.HOUR_OF_DAY) < 10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
