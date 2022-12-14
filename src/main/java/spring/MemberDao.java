package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.result.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	@Autowired
	private DataSource dataSource ;
	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<Member> selectByRegdate(LocalDateTime from,LocalDateTime today){
		List<Member> results = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement("select * from MEMBER where REGDATE between ? and ? order by REGDATE desc;");
				pstmt.setTimestamp(1, Timestamp.valueOf(from));
				pstmt.setTimestamp(2, Timestamp.valueOf(today));
				System.out.println(pstmt.toString());
				return pstmt;
			}
		}, new memRowMapper());

		return results;
	}
	public Member selectById(Long id){
		try {
			Member member = jdbcTemplate.queryForObject("select * from MEMBER where id=?", new memRowMapper(), id);
			return member;
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?",
				new memRowMapper(), email);

		return results.isEmpty() ? null : results.get(0);
	}
	public void deleteMember(String member){
		System.out.println(member);
		System.out.println("A");
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("Delete from MEMBER where EMAIL = ?");
				ps.setString(1,member);
				return ps;
			}
		});
	}
	public void updatePwd(Member member,String newPwd){
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement("UPDATE FROM MEMBER WHERE EMAIL=? SET PASSWORD = ?;");
				pstmt.setString(1,member.getEmail());
				pstmt.setString(2,newPwd);
				return pstmt;
			}
		});
	}
	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// ??????????????? ???????????? Connection??? ???????????? PreparedStatement ??????
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " +
						"values (?, ?, ?, ?)",
						new String[] { "ID" });
				// ????????? ???????????? ??? ??????
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4,
						Timestamp.valueOf(member.getRegisterDateTime()));
				// ????????? PreparedStatement ?????? ??????
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}

	public void update(Member member) {
		jdbcTemplate.update(
				"update MEMBER WHERE EMAIL = ? set NAME = ?, PASSWORD = ? where EMAIL = ?",
				member.getEmail(),member.getName(), member.getPassword(), member.getEmail());
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER",
				(ResultSet rs, int rowNum) -> {
					Member member = new Member(
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							null);
					member.setId(rs.getLong("ID"));
					if(rs.getTimestamp("REGDATE")!=null){
						member.setRegdate(rs.getTimestamp("REGDATE").toLocalDateTime());

					}
					return member;
				});
		return results;
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from MEMBER", Integer.class);
		return count;
	}

}
