package me.otarola.instagramreader;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by aotarolaalvarad on 10/24/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    private static class ViewHolder {
        TextView tvCaption;
        ImageView ivPhoto;
    }

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        InstagramPhoto photo = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            viewHolder.tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
            convertView.setTag(viewHolder);
        }
        else{
           viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvCaption.setText(photo.caption);
        viewHolder.ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageUrl).into(viewHolder.ivPhoto);
        return convertView;
    }
}
