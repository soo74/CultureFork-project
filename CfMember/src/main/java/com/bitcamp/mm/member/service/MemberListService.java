package com.bitcamp.mm.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bitcamp.mm.member.dao.MemberDaoInterface;
import com.bitcamp.mm.member.domain.ListViewData;

import com.bitcamp.mm.member.domain.MemberInfo;
import com.bitcamp.mm.member.domain.SearchParam;

@Service("listServcie")
public class MemberListService implements MemberService {


	
	private MemberDaoInterface dao;
	
	@Inject
	private SqlSessionTemplate template;
	

	
	final int MEMBER_CNT_List = 3;
	
	public ListViewData getListData(int currentPageNumber, SearchParam searchParam) {
		

		dao = template.getMapper(MemberDaoInterface.class);
		
		
		ListViewData listData = new ListViewData();
		

			listData.setCurrentPageNumber(currentPageNumber);
			

			int totalCnt = dao.selectTotalCount(searchParam);
			
			int totalPageCnt = 0;

			if(totalCnt>0) {
				totalPageCnt = totalCnt/MEMBER_CNT_List;
				if(totalCnt%MEMBER_CNT_List>0) {
					totalPageCnt++;
				}
			}
			listData.setPageTotalCount(totalPageCnt);
			
			

			int index = (currentPageNumber-1)*MEMBER_CNT_List;

			List<MemberInfo> memberList = null;

			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("search", searchParam);
			params.put("index", index);
			params.put("count", MEMBER_CNT_List);
			
			memberList = dao.selectList(params);
			System.out.println("사이즈 : : : : " + totalCnt);
			System.out.println("리스트 사이즈 : : : : " + memberList.size());
			
			listData.setMemberList(memberList);
			for(MemberInfo m : memberList) {
				System.out.println(m);
			}
			
			int no = totalCnt - index;
			listData.setNo(no);
			
			listData.setTotalCount(totalCnt);
			

		return listData;
		
	}
	
	
	
	
	public List<MemberInfo> getAllList(){
		
		dao = template.getMapper(MemberDaoInterface.class);
		
		List<MemberInfo> list = dao.selectAllList();
		
		return list;
		
	}
	

	
	
	
	
	
	
	
	
	
	
}
 