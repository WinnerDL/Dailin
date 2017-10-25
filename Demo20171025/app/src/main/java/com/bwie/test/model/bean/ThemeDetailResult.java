package com.bwie.test.model.bean;

import java.util.ArrayList;

/**
 * 主题日报内容对应的实体类
 */

public class ThemeDetailResult {

    public int limit;
    public ArrayList<LatestResult.Story> stories;
    public String description;
    public String background;
    public int color;
    public String name;
    public String image;
    public ArrayList<Editors> editors;
    public String image_source;

    public static class Editors extends MultiType{
        public String url;
        public String bio;
        public int id;
        public String avatar;
        public String name;
    }

}
