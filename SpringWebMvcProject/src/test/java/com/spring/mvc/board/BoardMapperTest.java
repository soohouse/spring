package com.spring.mvc.board;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.repository.IBoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
//테스트 환경에서 Mapper 객체 활용을 위해 빈 등록 설정이 있는 xml 파일을 로딩하는 아노테이션.
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardMapperTest {
	
	@Autowired
	private IBoardMapper mapper;

}
