package online.u148.common.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;


/**
 * Created by liurui on 2016/6/21.
 */
public class TrafficStatsUtils {

//    private boolean hasRestart = false;

    private static TrafficStatsUtils trafficStatsUtils;

    public static TrafficStatsUtils getInstance(){
        if (trafficStatsUtils == null){
            trafficStatsUtils = new TrafficStatsUtils();
        }
        return trafficStatsUtils;
    }

//    public boolean isHasRestart() {
//        return hasRestart;
//    }
//
//    public void setHasRestart(boolean hasRestart) {
//        this.hasRestart = hasRestart;
//    }

    public static long getAppid(){
        try {
            PackageManager pm = ApplicationUtil.getContext().getPackageManager();
            ApplicationInfo ai = null;
            ai = pm.getApplicationInfo(
                    "com.cloudeye.ddktest", PackageManager.GET_META_DATA);

            String app = Integer.toString(ai.uid, 10);
            return Long.parseLong(app);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }


    //获取接受数据
    public static long getUidRxBytes(){
        int uid = (int) getAppid();
        return TrafficStats.getUidRxBytes(uid);
    }

    //获取发送数据
    public static long getUidTxBytes(){
        int uid = (int) getAppid();
        return TrafficStats.getUidTxBytes(uid);
    }

    public static long calculateDataUsage(){
        long rxdata = getUidRxBytes();
        rxdata = rxdata / 1024;

        long txdata = getUidTxBytes();
        txdata = txdata / 1024;

        return rxdata + txdata;
    }

    public static String getDataUsageStr(long currentData){
        if (currentData <= 0){
            return null;
        }else {
            if (currentData >= 1024){
                double result;
                result=(currentData*100/1024)/(double)100;
                return result+"MB";
            }else {
                return currentData+"KB";
            }
        }
    }

    public static String getDataUsageStrByUploadDataCount(long currentData){
        if (currentData <= 0){
            return null;
        }else {
            if (currentData >= 1024){
                return currentData/1024+"M";
            }else {
                return "1M";
            }
        }
    }

}
