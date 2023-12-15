package com.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<CustomItem> {
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, ArrayList<CustomItem> items) {
        super(context, resource, items);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.custom_item_layout, null);
        }

        // Liên kết dữ liệu với các thành phần UI
        CustomItem customItem = getItem(position);
        if (customItem != null) {
            TextView productNameTextView = view.findViewById(R.id.productNameTextView);
            TextView priceTextView = view.findViewById(R.id.priceTextView);
            TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
            ImageView productImageView = view.findViewById(R.id.productImageView);

            productNameTextView.setText(customItem.getProductName());
            priceTextView.setText(String.valueOf(customItem.getPrice()));
            descriptionTextView.setText(customItem.getDescription());

            // Tải hình ảnh bằng thư viện Picasso
            //Picasso.get().load(customItem.getImageUrl()).into(productImageView);
            Picasso.with(getContext()).load(customItem.getImageUrl()).into(productImageView);
        }

        return view;
    }
}

