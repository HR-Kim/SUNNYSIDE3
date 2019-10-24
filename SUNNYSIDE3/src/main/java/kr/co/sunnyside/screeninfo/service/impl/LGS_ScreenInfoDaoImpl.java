package kr.co.sunnyside.screeninfo.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.screeninfo.service.LGS_ScreenInfoVO;

@Repository
public class LGS_ScreenInfoDaoImpl implements WorkDiv {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "screeninfo";
	
	public String do_save_NEW(DTO dto) {
		String statement = this.NAMESPACE + ".do_save";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_ScreenInfoVO screenInfo = (LGS_ScreenInfoVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("screenInfo = " + screenInfo);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.insert(statement, screenInfo);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return screenInfo.getScreenId();
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = this.NAMESPACE + ".do_delete";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_ScreenInfoVO screenInfo = (LGS_ScreenInfoVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("screenInfo = " + screenInfo);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.delete(statement, screenInfo);
		
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
		
		LGS_ScreenInfoVO screenInfo = (LGS_ScreenInfoVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("screenInfo = " + screenInfo);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.update(statement, screenInfo);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		String statement = this.NAMESPACE + ".do_retrieve";
	
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		SearchVO search = (SearchVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("search = " + search);
		LOG.debug("==================================");
		
		List<LGS_ScreenInfoVO> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==================================");
		LOG.debug("list = " + list);
		LOG.debug("==================================");
		
		return list;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = this.NAMESPACE + ".do_selectOne";

		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		LGS_ScreenInfoVO screenInfo = (LGS_ScreenInfoVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("screenInfo = " + screenInfo);
		LOG.debug("==================================");
		
		LGS_ScreenInfoVO outVO = (LGS_ScreenInfoVO) sqlSessionTemplate.selectOne(statement, screenInfo);
		
		LOG.debug("==================================");
		LOG.debug("outVO = " + outVO);
		LOG.debug("==================================");
		
		return outVO;
	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		return null;
	}

	public List<?> do_retrieve_forUser(DTO dto) {
		String statement = this.NAMESPACE + ".do_retrieve_forUser";
	
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		SearchVO search = (SearchVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("search = " + search);
		LOG.debug("==================================");
		
		List<LGS_ScreenInfoVO> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==================================");
		LOG.debug("list = " + list);
		LOG.debug("==================================");
		
		return list;
	}
	
	public List<?> do_retrieve_branch(DTO dto) {
		String statement = this.NAMESPACE + ".do_retrieve_branch";
	
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		SearchVO search = (SearchVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("search = " + search);
		LOG.debug("==================================");
		
		List<LGS_ScreenInfoVO> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==================================");
		LOG.debug("list = " + list);
		LOG.debug("==================================");
		
		return list;
	}

	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
