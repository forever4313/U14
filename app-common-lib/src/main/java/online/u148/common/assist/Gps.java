package online.u148.common.assist;

/**
 * Created by Kevin Dong on 2016/6/27.
 */
public class Gps {
    private double lat;
    private double lng;
    public Gps(double wgLat, double wgLon) {
        setLat(wgLat);
        setLng(wgLon);
    }
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
