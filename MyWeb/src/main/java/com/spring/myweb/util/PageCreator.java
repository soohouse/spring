package com.spring.myweb.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageCreator {

	   private PageVO paging;
	   private int articleTotalCount;
	   private int endPage;
	   private int beginPage;
	   private boolean prev;
	   private boolean next;
	   
	   private final int buttonNum = 5;
	   
	   
	   private void calcDataOfPage() {
	      
	      endPage = (int) (Math.ceil(paging.getPageNum() / (double) buttonNum) * buttonNum);
	      
	      beginPage = (endPage - buttonNum) + 1;
	      
	      prev = (beginPage == 1) ? false : true;
	      
	      next = articleTotalCount <= (endPage * paging.getCpp()) ? false : true;
	      
	      if(!next) {
	         endPage = (int) Math.ceil(articleTotalCount / (double) paging.getCpp()); 
	      }
	      
	   }
}
