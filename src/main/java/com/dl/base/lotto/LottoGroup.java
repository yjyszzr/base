package com.dl.base.lotto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;

/**
 * 乐透数组分组算法
 * @date 2019.01.15
 */
public class LottoGroup {
	private List<Integer> mList;
	private List<List<Integer>> rList;
	private int num;
	
	/**
	 * @param list 数组
	 * @param num  多少个为一组
	 */
	public LottoGroup(List<Integer> list,int num) {
		this.mList = list;
		rList = new ArrayList<List<Integer>>();
		this.num = num;
	}
	
	public List<List<Integer>> cal() {
		Integer[] array = this.mList.toArray(new Integer[this.mList.size()]);
		String str = "";
		count(0,str,array,this.num);
		return rList;
	}

	private List<Integer> getList(String str){
		List<Integer> list = new ArrayList<Integer>();
		if(str != null && str.length() > 0) {
			String[] array = str.split(",");
			for(String strItem : array) {
				if(!StringUtils.isEmpty(strItem)) {
					list.add(Integer.valueOf(strItem));
				}
			}
		}
		return list;
	}
	
	private void count(int i, String str, Integer[] num,int n) {
        if(n==0){
            List<Integer> list = getList(str);
            rList.add(list);
            System.out.println(str);
            return;
        }
        if(i==num.length){
            return;
        }
        count(i+1,str+num[i]+",",num,n-1);
        count(i+1,str,num,n);
    }
}
