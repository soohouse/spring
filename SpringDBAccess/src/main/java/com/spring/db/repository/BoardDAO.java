package com.spring.db.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.db.model.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {
	
	@Autowired
	private JdbcTemplate template;
	
	//내부 클래스 선언
	class BoardMapper implements RowMapper<BoardVO> {
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {		 
			return new BoardVO(
					rs.getInt("board_no"),
					rs.getString("writer"),
					rs.getString("title"),
					rs.getString("content")
				);
		}
	}
	

	@Override
	public void insertArticle(BoardVO vo) {
		String sql = "INSERT INTO jdbc_board "
				+ "VALUES(bid_seq.NEXTVAL,?,?,?)";
		template.update(sql, vo.getWriter(), vo.getTitle(), vo.getContent());
	}

	@Override
	public List<BoardVO> getArticles() {
		String sql = "SELECT * FROM jdbc_board ORDER BY board_no DESC";
		return template.query(sql, new BoardMapper());
	}

	@Override
	public BoardVO getArticle(int bId) {
		String sql = "SELECT * FROM jdbc_board WHERE board_no=?";
		return template.queryForObject(sql, new BoardMapper(), bId);
	}

	@Override
	public void deleteArticle(int bId) {
		String sql = "DELETE FROM jdbc_board WHERE board_no=?";
		template.update(sql, bId);
	}

	@Override
	public void updateArticle(BoardVO vo) {
		String sql = "UPDATE jdbc_board "
				+ "SET writer=?, title=?, content=? "
				+ "WHERE board_no=?";
		template.update(sql, vo.getWriter(),vo.getTitle(), 
				vo.getContent(), vo.getBoardNo());
	}
	
	@Override
	public List<BoardVO> searchList(String keyword) {
		String sql = "SELECT * FROM jdbc_board WHERE writer LIKE ?";
		return template.query(sql, new BoardMapper(), keyword);
	}
	

}













