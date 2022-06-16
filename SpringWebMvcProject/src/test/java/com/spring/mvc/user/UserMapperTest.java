package com.spring.mvc.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserMapperTest {
	
	@Autowired
	private IUserMapper mapper;
	
	//IUserMapper 타입의 객체를 자동 주입하세요. & xml설정도 하세요.
	
	//회원 가입을 아무 아이디로 진행해 보세요.(insert/regist test 해보세요)
	
	@Test
	public void registerTest() {
		UserVO vo = new UserVO();
		vo.setAccount("abc1234");
		vo.setName("홍길동");//not null이라 무조건 값을 줘야함
		vo.setPassword("aaa1111!");
		
		mapper.register(vo);
	}
	
	//위에서 회원 가입한 아이디로 중복 확인을 해서
	//COUNT(*)를 이용해서 1이 리턴이 되는지 확인하세요.
	
	@Test
	public void checkIdTest() {
		int result = mapper.checkId("abc1234");
		if(result == 1) {
			System.out.println("아이디가 이미 존재함!");
		} else {
			System.out.println("아이디 사용 가능!");
		}
	}
	
	//가입한 회원의 모든 정보를 얻어내서 출력해 보세요.
	
	@Test
	public void selectTest() {
		System.out.println(mapper.selectOne("abc1234"));
	}
	//위에서 가입한 계정의 탈퇴를 진행해 보세요.
	//탈퇴가 성공했는지의 여부를 정보를 얻어오는 메서드를 통해서
	//확인해 보세요. (null 체크)
	
	@Test
	public void deleteTest() {
		mapper.delete("abc1234");
		if(mapper.selectOne("abc1234")==null) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
	}

	
	
	
	
	
	
}
