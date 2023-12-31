package test.com.idle.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import test.com.idle.dao.MemberDAO;
import test.com.idle.vo.MemberVO;

@Slf4j
@Repository
public class MemberDAOimpl implements MemberDAO {
	

	@Autowired
	SqlSession sqlSession;
	
	
	public MemberDAOimpl() {
		log.info("MemberDAOimpl()...");
	}
	
	@Override
	public int insert(MemberVO vo) {
		log.info("insert()...{}", vo);

		return sqlSession.insert("MEMBER_INSERT", vo);
	}

	@Override
	public MemberVO idCheck(MemberVO vo) {
		log.info("idCheck()..." + vo);

		MemberVO vo2 = sqlSession.selectOne("ID_CHECK", vo);

		return vo2;
	}//end idCheck

	@Override
	public MemberVO login(MemberVO vo) {
		log.info("login()...{}", vo);
		return sqlSession.selectOne("LOGIN", vo);
	}

	@Override
	public MemberVO selectOne(MemberVO vo) {
		log.info("selectOne()...{}" + vo);
		MemberVO vo2 = sqlSession.selectOne("MEMBER_SELECT_ONE", vo);
		return vo2;
	}

	@Override
	public int buyCount(MemberVO vo) {
		log.info("buyCount()...{}" + vo);
		int count = sqlSession.selectOne("MEMBER_BUY_COUNT",vo);
		log.info("count...{}",count);
		return count;
	}

	@Override
	public int sellCount(MemberVO vo) {
		log.info("sellCount()...{}" + vo);
		int count = sqlSession.selectOne("MEMBER_SELL_COUNT",vo);
		log.info("count...{}",count);
		return count;
	}

	@Override
	public int update(MemberVO vo) {
		log.info("update()...{}", vo);
		return sqlSession.update("MEMBER_UPDATE", vo);
	}

	@Override
	public int delete(MemberVO vo) {
		log.info("delete()...{}", vo);
		return sqlSession.delete("MEMBER_DELETE", vo);
	}


}
