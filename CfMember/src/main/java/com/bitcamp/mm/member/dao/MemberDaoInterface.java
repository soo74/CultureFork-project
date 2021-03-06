package com.bitcamp.mm.member.dao;

import java.util.List;
import java.util.Map;

import com.bitcamp.mm.member.domain.MemberInfo;
import com.bitcamp.mm.member.domain.SearchParam;

public interface MemberDaoInterface {
	
	//로그인
	public MemberInfo selectMemberById(String userId);
	// 아이디 중복 체크
	public MemberInfo selectMemberById2(String userId);
	
	//회원가입
	public int insertMember(MemberInfo memberInfo);
	
	//회원정보 수정
	public MemberInfo selectMemberByIdx(int id);
	public int memberUpdate(MemberInfo memberInfo);
	
	// 회원 정보 삭제
	public int memberDelete(int id);
	

	public int selectTotalCount(SearchParam searchParam);
	public List<MemberInfo> selectList(Map<String, Object> params);
	// 회원의 전체 리스트 
	public List<MemberInfo> selectAllList();
	// 회원 이메일 인증 처리
	public int verify(String id, String code);

	
	
	
	
	
	
	
	
	
	
	
}