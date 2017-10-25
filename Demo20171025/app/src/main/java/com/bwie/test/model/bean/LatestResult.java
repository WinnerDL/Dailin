package com.bwie.test.model.bean;

import java.util.ArrayList;

/**
 * 最新日报用到的实体类
 */

public class LatestResult {

    public String date;
    public ArrayList<Story> stories;
    public ArrayList<TopStory> top_stories;

    public static class Story extends MultiType{
        public String[] images;
        public int type;
        public int id;
        public String ga_prefix;
        public String title;
    }

    public static class TopStory{
        public String image;
        public int type;
        public int id;
        public String ga_prefix;
        public String title;
    }

}
