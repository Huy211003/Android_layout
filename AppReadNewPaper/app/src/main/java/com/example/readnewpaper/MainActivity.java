package com.example.readnewpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    //ArrayList<String> arrayTitle, arrayLink, arrayDescription;
    //ArrayAdapter adapter;
    CustomAdapter customAdapter;
    ArrayList<DocBao> mangdocbao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        mangdocbao = new ArrayList<DocBao>();
        //arrayTitle = new ArrayList<>();
        //arrayLink = new ArrayList<>();
        //arrayDescription = new ArrayList<>();
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayTitle);
        //listView.setAdapter(adapter);

        new ReadRSS().execute("https://vnexpress.net/rss/the-thao.rss");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                //intent.putExtra("linkTinTuc", arrayLink.get(position));
                intent.putExtra("linkTinTuc",mangdocbao.get(position).link);
                startActivity(intent);
            }
        });
    }

    private class ReadRSS extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {;
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }

                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document  = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title = "";
            String link = "";
            String pubDate = "";
            String hinhanh = "";
            for (int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                //arrayTitle.add(title);
                link = parser.getValue(element, "link");
                //arrayLink.add(link);
                //arrayLink.add(parser.getValue(element, "link"));
                pubDate = parser.getValue(element, "pubDate");
                //arrayDescription.add(pubDate);
                hinhanh = element.getTextContent().split("src=\"")[1].split("\"")[0];
                mangdocbao.add(new DocBao(title,link,pubDate,hinhanh));
            }
            customAdapter = new CustomAdapter(MainActivity.this, android.R.layout.simple_list_item_1,mangdocbao);
            listView.setAdapter(customAdapter);
            //adapter.notifyDataSetChanged();
        }
    }

}