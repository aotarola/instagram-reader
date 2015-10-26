package me.otarola.instagramreader;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by aotarolaalvarad on 10/24/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    private static class ViewHolder {
        TextView tvCaption;
        TextView tvUsername;
        TextView tvLocation;
        TextView tvCreatedTime;
        TextView tvLikesCount;
        ImageView ivPhoto;
        ImageView ivProfilePic;
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
            viewHolder.tvCreatedTime = (TextView) convertView.findViewById(R.id.tvCreatedTime);
            viewHolder.tvLocation = (TextView) convertView.findViewById(R.id.tvLocation);
            viewHolder.tvLikesCount = (TextView) convertView.findViewById(R.id.tvLikesCount);
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.ivProfilePic = (ImageView) convertView.findViewById(R.id.ivProfilePic);
            convertView.setTag(viewHolder);
        }
        else{
           viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvCaption.setText(photo.caption);
        viewHolder.tvLikesCount.setText(photo.getLikesCount());
        viewHolder.tvCreatedTime.setText(photo.getCreatedTime());
        viewHolder.tvLocation.setText(photo.location);
        viewHolder.tvUsername.setText("@" + photo.username);
        //viewHolder.ivPhoto.setImageResource(0);
        //viewHolder.ivProfilePic.setImageResource(0);

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(true)
                .build();

        Picasso.with(getContext())
                .load(photo.imageUrl)
                .into(viewHolder.ivPhoto);

        Picasso.with(getContext())
                .load(photo.profilePictureUrl)
                .transform(transformation)
                .resize(75, 75)
                .into(viewHolder.ivProfilePic);

        return convertView;
    }
}
