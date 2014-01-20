package ctjacobs.imperialscrmenu;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    }
}
