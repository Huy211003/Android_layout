package com.example.json;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        customAdapter = new CustomAdapter(this, R.layout.custom_item_layout, new ArrayList<>());
        listView.setAdapter(customAdapter);

        String jsonUrl = "https://api.npoint.io/7be3e5e366b82e165cf4";
        new JsonAsyncTask().execute(jsonUrl);
    }

    private class JsonAsyncTask extends AsyncTask<String, Void, ArrayList<CustomItem>> {

        @Override
        protected ArrayList<CustomItem> doInBackground(String... params) {
            ArrayList<CustomItem> customItemList = new ArrayList<>();

            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                connection.disconnect();

                // Parse JSON data
                JSONArray jsonArray = new JSONArray(result.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String productName = jsonObject.getString("productName");
                    int price = jsonObject.getInt("price");
                    String description = jsonObject.getString("description");
                    String imageUrl = jsonObject.getString("image");

                    CustomItem customItem = new CustomItem(productName, price, description, imageUrl);
                    customItemList.add(customItem);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return customItemList;
        }

        @Override
        protected void onPostExecute(ArrayList<CustomItem> customItems) {
            customAdapter.addAll(customItems);
            customAdapter.notifyDataSetChanged();
        }
    }

}
