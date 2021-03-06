package com.spring.myweb.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor // 생성자~ 필요하다면 선언하기
public class SnsBoardVO {
	
	private int bno;
	private String writer;
	private String uploadpath;
	private String fileloca;
	private String filename;
	private String filerealname;
	private String content;
	private Timestamp regdate;
	
	//좋아요 개수가 몇 개인지를 알려주는 변수 추가.
	private int likeCnt;

}
