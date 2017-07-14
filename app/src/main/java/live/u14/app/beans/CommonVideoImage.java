package live.u14.app.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Dong on 2017/4/10.
 */
public class CommonVideoImage {
    private String imgUrl;
    private String description;
    private String title;

    public CommonVideoImage(){

    }
    public CommonVideoImage(String title){
        this.title = title;
    }

    public static List<CommonVideoImage> fake(){

        List<CommonVideoImage> list = new ArrayList<>();
        CommonVideoImage img1 = new CommonVideoImage();
        img1.setTitle("【20170311】难忘今宵");
        img1.setImgUrl("https://r1.ykimg.com/0541040858C91BE600000192DC0919E8");
        img1.setDescription("「20170311 北展剧场 德云社20周年闭幕式 岳云鹏郭德纲于谦 难忘今宵");

        list.add(img1);

        CommonVideoImage img2 = new CommonVideoImage();
        img2.setTitle("定格 MV：堆砌人生");
        img2.setImgUrl("http://img.kaiyanapp.com/809c35dbc7f5b60422b581d3f2c51ee8.jpeg?imageMogr2/quality/60/format/jpg");
        img2.setDescription("Gotye 比利时裔澳大利亚人创作歌手这首「Easy Way Out」用定格动画和创意转场的方式，表现出歌曲中百无聊赖的日常。「Somebody That I Used To Know」是「Making Mirrors」这张专辑中最著名的一首。From Gotye");

        list.add(img2);
        CommonVideoImage img3 = new CommonVideoImage();
        img3.setTitle("超级英雄为什么要穿披风？");
        img3.setImgUrl("http://img.kaiyanapp.com/3d724851c59dd0e517a4fc42b5da7858.jpeg?imageMogr2/quality/60/format/jpg");
        img3.setDescription("说到超级英雄，除了过分贴身的紧身衣外，就是披风了。它能成为大部分英雄的心头之好。你以为它只是为了好看？！欢迎走进本期《脑子有洞看片会》，带你解开超级英雄和披风的不为人知的真相。");

        list.add(img3);
        CommonVideoImage img4 = new CommonVideoImage();
        img4.setTitle("楼上晾满衣裤，也无法阻挡这家店的人气");
        img4.setImgUrl("http://img.kaiyanapp.com/6c273df123c134d0aca611677d1cbd35.png?imageMogr2/quality/60/format/jpg");
        img4.setDescription("广州一个老旧的居民区里，藏着一家小清新咖啡店。店面设计北欧风，白墙加点木材点缀，一桌一椅简单有质感。老板也超有范儿，不卖拿铁、摩卡和卡普奇诺，只做黑咖和奶咖。这家咖啡馆很多人慕名前来，都是为了拍拍拍。");

        list.add(img4);

        return list;


    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
