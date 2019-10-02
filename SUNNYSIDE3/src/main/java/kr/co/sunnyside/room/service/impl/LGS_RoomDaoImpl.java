package kr.co.sunnyside.room.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.room.service.LGS_RoomVO;

@Repository
public class LGS_RoomDaoImpl implements WorkDiv {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "room";
	
	@Override
	public int do_save(DTO dto) {
		String statement = this.NAMESPACE + ".do_save";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_RoomVO room = (LGS_RoomVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("room = " + room);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.insert(statement, room);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = this.NAMESPACE + ".do_delete";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_RoomVO room = (LGS_RoomVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("room = " + room);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.delete(statement, room);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	@Override
	public int do_update(DTO dto) {
		String statement = this.NAMESPACE + ".do_update";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_RoomVO room = (LGS_RoomVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("room = " + room);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.update(statement, room);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		String statement = this.NAMESPACE + ".get_retrieve";
	
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		SearchVO search = (SearchVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("search = " + search);
		LOG.debug("==================================");
		
		List<LGS_RoomVO> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==================================");
		LOG.debug("list = " + list);
		LOG.debug("==================================");
		
		return list;
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		String statement = this.NAMESPACE + ".get_selectOne";

		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_RoomVO room = (LGS_RoomVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("room = " + room);
		LOG.debug("==================================");
		
		LGS_RoomVO outVO = (LGS_RoomVO) sqlSessionTemplate.selectOne(statement, room);
		
		LOG.debug("==================================");
		LOG.debug("outVO = " + outVO);
		LOG.debug("==================================");
		
		return outVO;
	}

	@Override
	public List<?> get_excelDown(DTO dto) {
		return null;
	}

}
