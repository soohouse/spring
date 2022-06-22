package com.spring.myweb.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.myweb.command.UserVO;

public interface IUserMapper {

	//아이디 중복 확인
	int idCheck(String id);
	
	//회원가입
	void join(UserVO vo);
	
	//로그인
	UserVO login(@Param("id") String id, @Param("pw") String pw);
	//UserVo로 리턴하는 이유 : 조회했을 때 null오면 그냥 로그인 실패
	
	
	//회원 정보 얻어오기
	UserVO getInfo(String id);
	
	//회원 정보 수정
	void updateUser(UserVO vo);
	
	//회원 정보 삭제
	void deleteUser(@Param("id") String id, @Param("pw") String pw);
	//UserVO로 해도되는데 그러려면 객체로 잘 포장해서 가져오면 됌
}
