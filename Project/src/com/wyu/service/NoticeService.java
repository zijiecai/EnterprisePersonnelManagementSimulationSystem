package com.wyu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wyu.pojo.Notice;
import com.wyu.pojo.NoticeExample;

@Service
public interface NoticeService {
	
	int addNotice(Notice notice);
	public List<Notice> findNotices();
	public List<Notice> selectNoticeLike(Notice notice) ;
	public int updateNotice(Notice notice);
	public int deleteNotice(Integer id);
	public int countByExample(NoticeExample example) ;
}
