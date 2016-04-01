package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var08SecondaryActivity extends Activity {

    private Button verify_button = null;
    private Button cancel_button = null;
    private TextView textViewer = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.verify:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_secondary);

        textViewer = (TextView)findViewById(R.id.textView2);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("text")) {
            String text = intent.getStringExtra("text");
            textViewer.setText(text);
        }

        verify_button = (Button)findViewById(R.id.verify);
        verify_button.setOnClickListener(buttonClickListener);
        cancel_button = (Button)findViewById(R.id.cancel);
        cancel_button.setOnClickListener(buttonClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_practical_test01_var08_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
