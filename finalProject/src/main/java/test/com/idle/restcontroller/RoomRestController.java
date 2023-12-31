package test.com.idle.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import test.com.idle.service.RoomService;
import test.com.idle.vo.RoomVO;

@Controller
@Slf4j
public class RoomRestController {
	
	@Autowired
	RoomService service;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	SimpMessagingTemplate template;
	
	@RequestMapping(value = "/jsonRoomInsert.do", method = RequestMethod.GET)
	@ResponseBody
	public int jsonRoomInsert(RoomVO vo) {
		log.info("/jsonChatInsert.do...{}",vo);
		
		int result = 0;
		
		RoomVO vo2 = service.roomCheck(vo);
		log.info("{}",vo2);
		
		if(vo2!=null) {
			result = 1;
			return result;
		}
		
		result = service.insert(vo);
		log.info("{}",result);
		
		return result;
	}
	
	@RequestMapping(value = "/jsonRoomDelete.do", method = RequestMethod.GET)
	@ResponseBody
	public int jsonRoomDelete(RoomVO vo) {
		log.info("/jsonRoomDelete.do...{}",vo);
		
		int result = service.delete(vo);
		log.info("{}",result);
		
		if(result == 1) {
			template.convertAndSend("/sub/chat/roomDeleted"+vo.getRoom_num(),result);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/jsonRoomCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public RoomVO jsonRoomCheck(RoomVO vo) {
		log.info("/jsonRoomCheck.do...{}",vo);
		
		RoomVO vo2 = service.selectOne(vo);
		log.info("{}",vo2);
		
		return vo2;
	}
	
	@RequestMapping(value = "/jsonRoomSelectOne.do", method = RequestMethod.GET)
	@ResponseBody
	public RoomVO jsonRoomSelectOne(RoomVO vo) {
		log.info("/jsonRoomSelectOne.do...{}",vo);
		
		RoomVO vo2 = service.selectOne(vo);
		log.info("vo2:{}",vo2);
		
		return vo2;
	}
}
