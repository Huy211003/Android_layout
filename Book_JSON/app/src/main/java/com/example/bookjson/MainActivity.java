package com.example.bookjson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Execute AsyncTask
        new JsonAsyncTask().execute();
    }

    private class JsonAsyncTask extends AsyncTask<Void, Void, List<CustomItem>> {

        private static final String JSON_URL = "https://api.npoint.io/40f2af37000cb74128f1";

        @Override
        protected List<CustomItem> doInBackground(Void... voids) {
            try {
                URL url = new URL(JSON_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return parseJson(stringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private List<CustomItem> parseJson(String json) {
            List<CustomItem> customItemList = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String author = jsonObject.getString("author");
                    String genre = jsonObject.getString("genre");
                    int year = jsonObject.getInt("year");
                    String imageUrl = jsonObject.getString("image");
                    customItemList.add(new CustomItem(title, author, genre, year, imageUrl));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return customItemList;
        }

        @Override
        protected void onPostExecute(List<CustomItem> customItems) {
            super.onPostExecute(customItems);
            if (customItems != null) {
                // Update ListView with the data using CustomAdapter
                CustomAdapter adapter = new CustomAdapter(MainActivity.this, customItems);
                listView.setAdapter(adapter);
            }
        }
    }

}
