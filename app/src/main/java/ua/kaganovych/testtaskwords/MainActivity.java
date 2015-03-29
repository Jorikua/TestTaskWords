package ua.kaganovych.testtaskwords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Map;
import java.util.TreeMap;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView wordLabel = (TextView) findViewById(R.id.wordLabel);
        TextView count = (TextView) findViewById(R.id.count);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String inputText = intent.getStringExtra(LaunchActivity.INPUT_TEXT);

        String clearedText = inputText.replaceAll("[\\-\\+\\.\\^:,]", " ");
        String lowerCasedText = clearedText.toLowerCase();

        String[] myStringArray = lowerCasedText.split(" ");

        Map<String, Integer> map = new TreeMap<>();
        for (String word : myStringArray) {
            if (word.length() >= 2) {
                map.put(word, (map.get(word) == null ? 1 : (map.get(word) + 1)));
            }
        }

        StringBuilder wordBuilder = new StringBuilder();
        StringBuilder countBuilder = new StringBuilder();
        for (String word : map.keySet()) {
            if (map.get(word) >= 2)
            {
                Log.d("TAG", word + " => " + map.get(word));
                wordBuilder.append(word);
                wordBuilder.append(": ");
                wordBuilder.append("\n");
                String str = wordBuilder.substring(0, wordBuilder.length());
                wordLabel.setText(str);

                countBuilder.append(map.get(word));
                countBuilder.append("\n");
                String str1 = countBuilder.substring(0, countBuilder.length());
                count.setText(str1);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
