package aitlindia.com.ibmiot;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import aitlindia.com.ibmiot.iot.*;
import aitlindia.com.ibmiot.utils.*;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MainActivity extends AppCompatActivity {

    final String  TAG = "MainActivity";

    MyIoTActionListener myIOTActionListner;
    IoTCallbacks myIOTCallBack;
    IoTClient ibmIOTClient;
    IoTDevice myIOTDevice;
    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        myContext = getApplicationContext();
        myIOTActionListner = new MyIoTActionListener(myContext, Constants.ActionStateStatus.PUBLISH);
        ibmIOTClient = IoTClient.getInstance(myContext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //ibmIOTClient.connectDevice()

        String messageData = "{\n" +
                "    \"desired\": {\n" +
                "        \"light\": \"on\"\n" +
                "    },\n" +
                "    \"reported\": {\n" +
                "        \"light\": \"on\"\n" +
                "    }";

        try {
        ibmIOTClient.publishEvent(Constants.AITL_EVENT, "json", messageData, 0, false, myIOTActionListner);
        } catch (MqttException e) {
            Log.e(TAG,"MqttException:",e);
            // Publish failed
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
