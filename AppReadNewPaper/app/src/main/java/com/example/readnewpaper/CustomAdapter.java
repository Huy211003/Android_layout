package com.example.readnewpaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DocBao> {
    public CustomAdapter(Context context, int resource, List<DocBao> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.dong_layout_listview, null);
        }
        DocBao p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txttitle = (TextView) view.findViewById(R.id.textviewTitle);
            txttitle.setText(p.title);

            ImageView imageView = (ImageView) view.findViewById(R.id.image_item);
            Picasso.with(getContext()).load(p.image).into(imageView);
            TextView txtpubDate = (TextView) view.findViewById(R.id.textviewPubDate);
            txtpubDate.setText(p.pubDate);
        }
        return view;
    }
}