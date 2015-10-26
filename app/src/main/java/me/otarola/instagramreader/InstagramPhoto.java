package me.otarola.instagramreader;
import org.ocpsoft.prettytime.PrettyTime;
import java.util.Date;
/**
 * Created by aotarolaalvarad on 10/24/15.
 */
public class InstagramPhoto {
    public String username;
    public String profilePictureUrl;
    public String caption;
    public String location;
    public String imageUrl;
    public int imageHeight;
    public long createdTime;
    public int likesCount;

    public String getCreatedTime(){
        PrettyTime p = new PrettyTime();

        return p.format(new Date(this.createdTime*1000));
    }
}
