package me.otarola.instagramreader;
import org.ocpsoft.prettytime.PrettyTime;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * Created by aotarolaalvarad on 10/24/15.
 */
public class InstagramPhoto {
    public String username;
    public String profilePictureUrl;
    public String location;
    public ArrayList<InstagramPhotoComment> comments;
    public String imageUrl;
    public int imageHeight;
    public long createdTime;
    public int likesCount;

    public InstagramPhoto(){
        comments = new ArrayList<InstagramPhotoComment>();
    }

    public String getCreatedTime(){
        PrettyTime p = new PrettyTime();

        return p.format(new Date(this.createdTime*1000));
    }

    public String getLikesCount(){
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);
        return nf.format(this.likesCount) + " " +(this.likesCount == 1 ? "Like" : "Likes");
    }
}
