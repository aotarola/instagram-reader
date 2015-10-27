package me.otarola.instagramreader;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        TextView tvUsername;
        TextView tvLocation;
        TextView tvCreatedTime;
        TextView tvLikesCount;
        ImageView ivPhoto;
        ImageView ivProfilePic;
        LinearLayout commentList;

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
            viewHolder.tvCreatedTime = (TextView) convertView.findViewById(R.id.tvCreatedTime);
            viewHolder.tvLocation = (TextView) convertView.findViewById(R.id.tvLocation);
            viewHolder.tvLikesCount = (TextView) convertView.findViewById(R.id.tvLikesCount);
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.ivProfilePic = (ImageView) convertView.findViewById(R.id.ivProfilePic);

            viewHolder.commentList = (LinearLayout) convertView.findViewById(R.id.layoutComments);
            viewHolder.commentList.removeAllViews();

            for (int i = 0; i < 2; i++){
                View line = LayoutInflater.from(getContext()).inflate(R.layout.comment, null);
                viewHolder.commentList.addView(line);
            }

            convertView.setTag(viewHolder);
        }
        else{
           viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvLikesCount.setText(Html.fromHtml("\u2665" + " " + photo.getLikesCount()));
        viewHolder.tvCreatedTime.setText(photo.getCreatedTime());
        String satellite = new String(Character.toChars(0x1F4E1));
        if(photo.location != "") {
            viewHolder.tvLocation.setText(Html.fromHtml(satellite + " " + photo.location));
        }
        viewHolder.tvUsername.setText("@" + photo.username);

        //viewHolder.ivPhoto.setImageResource(0);
        //viewHolder.ivProfilePic.setImageResource(0);
        for(int i=0; i<viewHolder.commentList.getChildCount(); i++) {
            TextView tvComment = (TextView)viewHolder.commentList.getChildAt(i);
            InstagramPhotoComment ipc =  photo.comments.get(i);
            String username = "<font color='#140E81'>"+ipc.username+"</font>";
            tvComment.setText(Html.fromHtml(username + " " +ipc.comment));
        }


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
                .placeholder(R.drawable.loading_animation)
                .into(viewHolder.ivProfilePic);

        return convertView;
    }
}
