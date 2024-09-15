package com.aftab.FindIT;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WikiPedia extends AppCompatActivity {

    public String newWord = new Classify.Query().getQuery();
    /** URL to query the wikipedia dataset for information */
    private static String WIKI_REQUEST_URL =
            "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";

    private String WIKI_URL = WIKI_REQUEST_URL + newWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_pedia);

        TextView titleView = findViewById(R.id.title);
        TextView contentView = findViewById(R.id.content);

        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // doInBackground
                String result = Utils.fetchData(WIKI_URL);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // postExecute
                        titleView.setText(newWord);
                        contentView.setText(result);
                    }
                });
            }
        });
    }
}