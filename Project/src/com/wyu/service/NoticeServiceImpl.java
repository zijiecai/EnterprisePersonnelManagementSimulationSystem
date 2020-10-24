package com.wyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyu.mapper.NoticeMapper;
import com.wyu.pojo.Notice;
import com.wyu.pojo.NoticeExample;
import com.wyu.pojo.Role;
import com.wyu.pojo.RoleExample;
import com.wyu.pojo.NoticeExample.Criteria;


@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper mapper;
	@Override
	public int addNotice(Notice notice)
	{
		if(mapper.selectByPrimaryKey( notice.getId() ) !=null) {
			return 0;
		}
		else {
			return mapper.insert(notice);
		}
	}
	@Override
	public List<Notice> selectNoticeLike(Notice notice) {
		NoticeExample example = new NoticeExample();
		Criteria criteria = example.createCriteria();
		Integer nid = notice.getId();
		String username = notice.getUsername();
		String name=notice.getName();
		if(nid!=null) {
			criteria.andIdEqualTo(nid);
		}
		if(username!=""&&username!=null)
		{
			criteria.andUsernameLike("%"+username+"%");
		}
		if(name!=""&&name!=null)
		{
			criteria.andNameLike("%"+name+"%");
		}
		List<Notice> noticeList =mapper.selectByExampleWithBLOBs(example);
		return noticeList;
	}
	@Override
	public List<Notice> findNotices(){
		//return mapper.selectByExample(new NoticeExample()); //该方法无法查询出longtext类型的数据
		return mapper.selectByExampleWithBLOBs(new NoticeExample());
	}
	@Override
	public int updateNotice(Notice notice) {
		return mapper.updateByPrimaryKey(notice);
	}
	@Override
	public int deleteNotice(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	@Override
	public int countByExample(NoticeExample example) {
		return mapper.countByExample(example);
	}
}
