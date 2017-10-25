package com.bwie.test.model.bean;

import java.util.ArrayList;

/**
 * 主题日报对应的实体类
 */

public class ThemesResult {

    public int limit;
    public Object[] subscribed;
    public ArrayList<Other> others;

    public static class Other{
        public int color;
        public String thumbnail;
        public String description;
        public int id;
        public String name;
    }

}
