package com.example.bookjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<CustomItem> {

    private LayoutInflater inflater;

    public CustomAdapter(Context context, List<CustomItem> items) {
        super(context, 0, items);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.titleTextView = convertView.findViewById(R.id.titleTextView);
            viewHolder.authorTextView = convertView.findViewById(R.id.authorTextView);
            viewHolder.genreTextView = convertView.findViewById(R.id.genreTextView);
            viewHolder.yearTextView = convertView.findViewById(R.id.yearTextView);
            viewHolder.bookImageView = convertView.findViewById(R.id.bookImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CustomItem item = getItem(position);

        if (item != null) {
            viewHolder.titleTextView.setText(item.getTitle());
            viewHolder.authorTextView.setText(item.getAuthor());
            viewHolder.genreTextView.setText(item.getGenre());
            viewHolder.yearTextView.setText(String.valueOf(item.getYear()));

            // Load image using Picasso
            Picasso.with(getContext()).load(item.getImageUrl()).into(viewHolder.bookImageView);
        }

        return convertView;
    }

    private static class ViewHolder {
        ImageView bookImageView;
        TextView titleTextView;
        TextView authorTextView;
        TextView genreTextView;
        TextView yearTextView;
    }
}

