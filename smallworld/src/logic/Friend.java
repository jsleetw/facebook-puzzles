package logic;

import com.infomatiq.jsi.Point;
import com.infomatiq.jsi.Rectangle;

public class Friend {

    private String id;
    private float latitude;
    private float longitude;

    public Friend(String id, float latitude, float longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String toString() {
        return "Friend:" + getId() + ":" + getLatitude() + "lat;" + getLongitude() + "long!";
    }

    public Rectangle getRectangle() {
        return new Rectangle(latitude, longitude, latitude, longitude);
    }

    public Point getPoint() {
        return new Point(latitude, longitude);
    }
}
