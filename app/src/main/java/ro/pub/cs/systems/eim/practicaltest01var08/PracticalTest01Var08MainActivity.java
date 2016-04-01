package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends Activity {

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private Button navigateToSecondaryActivityButton = null;

    private TextView textViewer = null;
    private Button topLeft = null;
    private Button topRight = null;
    private Button center = null;
    private Button bottomLeft = null;
    private Button bottomRight = null;
    private int noOfTries = 0;
    private int noOfSuccess = 0;
    private int noOfFailure = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String textV = textViewer.getText().toString();
            switch(view.getId()) {
                case R.id.top_left:
                    textV += "Top Left, "; break;
                case R.id.top_right:
                    textV += "Top Right, "; break;
                case R.id.center:
                    textV += "Center, "; break;
                case R.id.bottom_left:
                    textV += "Bottom Left, "; break;
                case R.id.bottom_right:
                    textV += "Bottom Right, "; break;
            }
            textViewer.setText(textV.toString());

            switch(view.getId()) {
                case R.id.navigate_to_second_activity:
                    noOfTries++;
                    textViewer = (TextView) findViewById(R.id.textView);
                    textViewer.setText(" ");
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08SecondaryActivity.class);
                    String text = textViewer.getText().toString();
                    intent.putExtra("text", text);
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        textViewer = (TextView) findViewById(R.id.textView);
        textViewer.setText(" ");

        topLeft = (Button)findViewById(R.id.top_left);
        topRight = (Button)findViewById(R.id.top_right);
        center = (Button)findViewById(R.id.center);
        bottomLeft = (Button)findViewById(R.id.bottom_left);
        bottomRight = (Button)findViewById(R.id.bottom_right);

        topLeft.setOnClickListener(buttonClickListener);
        topRight.setOnClickListener(buttonClickListener);
        center.setOnClickListener(buttonClickListener);
        bottomLeft.setOnClickListener(buttonClickListener);
        bottomRight.setOnClickListener(buttonClickListener);

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_second_activity);
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            if (resultCode == RESULT_OK) {
                noOfSuccess++;
            } else if (resultCode == RESULT_CANCELED) {
                noOfFailure++;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("noOfTries", String.valueOf(noOfTries));
        savedInstanceState.putString("noOfSuccess", String.valueOf(noOfSuccess));
        savedInstanceState.putString("noOfFailure", String.valueOf(noOfFailure));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("noOfTries")) {
            Toast.makeText(this, "noOfTries: " + savedInstanceState.getString("noOfTries"), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "noOfTries: 0", Toast.LENGTH_LONG).show();
        }
        if (savedInstanceState.containsKey("noOfSuccess")) {
            Toast.makeText(this, "noOfSuccess: " + savedInstanceState.getString("noOfSuccess"), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "noOfSuccess: 0", Toast.LENGTH_LONG).show();
        }
        if (savedInstanceState.containsKey("noOfFailure")) {
            Toast.makeText(this, "noOfFailure: " + savedInstanceState.getString("noOfFailure"), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "noOfFailure: 0", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var08_main, menu);
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
}
