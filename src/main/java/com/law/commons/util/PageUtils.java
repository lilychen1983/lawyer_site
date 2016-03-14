package com.law.commons.util;

/**
 * 分页工具类
 * @author l
 * @email weilong@chuchuchina.com
 * @date 2015-1-9
 */
public class PageUtils {

	/**
	 * 获取总页数
	 * @param total
	 * @param pagesize
	 * @return
	 */
	public static Integer getTotalPage(Integer total, Integer pagesize) {
		
        if (total % pagesize == 0) {
            return total / pagesize;
        } else {
            return total / pagesize + 1;
        }
	}	
	
	/**
	 * 获取开始项
	 * @param pno
	 * @param pagesize
	 * @return
	 */
	public static Integer getStartIndex(Integer pno, Integer pagesize){
		
		if(pno - 1 < 0){
			return 0;
		} else {
			return (pno - 1) * pagesize;
		}
	}
}
