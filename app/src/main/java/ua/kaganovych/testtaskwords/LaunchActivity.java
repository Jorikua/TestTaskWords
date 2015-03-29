package ua.kaganovych.testtaskwords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LaunchActivity extends ActionBarActivity {

    public static final String INPUT_TEXT = "input_text";
    private EditText mInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        mInputText = (EditText)findViewById(R.id.editText);
        Button button = (Button)findViewById(R.id.launchButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInputText.getText().toString().matches("")) {
                    Utils.showOkDialog(LaunchActivity.this, R.string.title_error_dialog, R.string.message_error_dialog);
                } else {
                    String text = mInputText.getText().toString();
                    Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                    intent.putExtra(INPUT_TEXT, text);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_launch, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_clear:
                mInputText.setText("");
        }

        return super.onOptionsItemSelected(item);
    }
}
