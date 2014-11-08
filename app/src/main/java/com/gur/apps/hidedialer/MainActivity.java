package com.gur.apps.hidedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity
{
    public static final String PHONE_NUM_KEY = "com.gur.apps.hidedialer.PHONE_NUMBER";
    private EditText phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.phoneNum = (EditText) findViewById(R.id.main_phonenum_text);

    }

    public void dial(View view)
    {
        if (phoneNum != null && phoneNum.getText().length() > 0)
        {
            Intent intent = new Intent(this, IncallActivity.class);
            intent.putExtra(PHONE_NUM_KEY, this.phoneNum.getText().toString());
            startActivity(intent);

            return;
        }

        Toast.makeText(this, getString(R.string.main_nonumber_error), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
