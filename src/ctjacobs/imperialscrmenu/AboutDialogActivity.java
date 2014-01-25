/*    
    Copyright (C) 2014 Christian Jacobs.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. 
*/

package ctjacobs.imperialscrmenu;

import java.net.*;
import java.io.*;
import java.util.*;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.graphics.Color;
import android.graphics.Typeface;

public class AboutDialogActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about);

        /* Update the title of the About dialog. */
        TextView textAbout = (TextView) this.findViewById(R.id.textAbout);
        textAbout.setTextAppearance(this, android.R.style.TextAppearance_Large);
        textAbout.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        
        /* Update the application's description. */
        TextView textAboutDescription = (TextView) this.findViewById(R.id.textAboutDescription);
        textAboutDescription.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        textAboutDescription.setText("ImperialSCRMenu is an application for checking the daily menu at Imperial's SCR restaurant.");

        /* Update the author information. */
        TextView textAboutAuthor = (TextView) this.findViewById(R.id.textAboutAuthor);
        textAboutAuthor.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        textAboutAuthor.setText("Written by Christian Jacobs <c.jacobs10@imperial.ac.uk>, and released under the GNU General Public License (Version 3).");
      
        /* Update the disclaimer. */
        TextView textAboutDisclaimer = (TextView) this.findViewById(R.id.textAboutDisclaimer);
        textAboutDisclaimer.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        textAboutDisclaimer.setText("The ImperialSCRMenu project is not affiliated with Imperial College London nor with its catering services. The author is not responsible for any inaccuracies in the content provided by the SCR restaurant's website.");
          
        return;
    }
    
    public void onCloseClicked(View v) 
    {
        /* Close the About dialog if the Close button is clicked. */
        finish();
    }

}
