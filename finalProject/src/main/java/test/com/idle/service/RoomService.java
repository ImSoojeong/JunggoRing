package test.com.idle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import test.com.idle.dao.RoomDAO;
import test.com.idle.vo.RoomVO;

@Service
@Slf4j
public class RoomService {
	
	@Autowired
	RoomDAO dao;
	
	public RoomService() {
		log.info("RoomService()...");
	}

	public List<RoomVO> selectAll(RoomVO vo) {
		return dao.selectAll(vo);
	}

	public int insert(RoomVO vo) {
		return dao.insert(vo);
	}

	public RoomVO roomCheck(RoomVO vo) {
		return dao.roomCheck(vo);
	}

	public int delete(RoomVO vo) {
		return dao.delete(vo);
	}

	public RoomVO selectOne(RoomVO vo) {
		return dao.selectOne(vo);
	}
	
	public String getRecipient(int roomNum, String sender) {
		RoomVO vo = new RoomVO();
		vo.setRoom_num(roomNum);
		
		RoomVO vo2 = dao.selectOne(vo);
		
		if (vo2.getBuyer().equals(sender)) {
			return vo2.getSeller();
		} else {
			return vo2.getBuyer();
		}
	}

}
