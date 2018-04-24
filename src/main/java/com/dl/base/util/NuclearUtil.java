package com.dl.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NuclearUtil {
	
	/**
     * 获取两个List的不同元素
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        Map<String,Integer> map = new HashMap<String,Integer>(list1.size()+list2.size());
        List<String> diff = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if(list2.size()>list1.size())
        {
            maxList = list2;
            minList = list1;
        }
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            Integer cc = map.get(string);
            if(cc!=null)
            {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for(Map.Entry<String, Integer> entry:map.entrySet())
        {
            if(entry.getValue()==1)
            {
                diff.add(entry.getKey());
            }
        }
        System.out.println("getDiffrent4 total times "+(System.nanoTime()-st));
        return diff;
    }
    
	/**
     * 获取两个List的相同的元素
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getSame(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        Map<String,Integer> map = new HashMap<String,Integer>(list1.size()+list2.size());
        List<String> same = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if(list2.size()>list1.size())
        {
            maxList = list2;
            minList = list1;
        }
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            Integer cc = map.get(string);
            if(cc!=null)
            {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for(Map.Entry<String, Integer> entry:map.entrySet())
        {
            if(entry.getValue() > 1)
            {
            	same.add(entry.getKey());
            }
        }
        System.out.println("getsSame total times "+(System.nanoTime()-st));
        return same;
    }
    
    
	/**
	 * 使用的前提示两个集合数量相同
     * 判断两个集合是否完全相同, list1 是否与  list2 相同
     * @param list1
     * @param list2
     * @return
     */
    public static Boolean isSame(List<String> list1, List<String> list2) {
        Boolean sameBz = true;
        Map<String,Integer> map = new HashMap<String,Integer>(list1.size()+list2.size());
        for (String string : list1) {
            map.put(string, 1);
        }
        
        for (String string : list2) {
            Integer cc = map.get(string);
            if(cc == null)
            {
            	sameBz = false;
                break;
            }
        }
        
        return sameBz;
    }

}
