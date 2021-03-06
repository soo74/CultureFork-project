
  package com.bitcamp.mm.member.controller;
  
  import java.util.List;
  
  import javax.servlet.http.HttpServletRequest; import
  javax.servlet.http.HttpSession;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.CrossOrigin; 
  import org.springframework.web.bind.annotation.DeleteMapping;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PathVariable; 
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.PutMapping;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestParam; 
  import org.springframework.web.bind.annotation.RestController; 
  import org.springframework.web.multipart.MultipartHttpServletRequest;
  
  import com.bitcamp.mm.member.domain.MemberInfo;
  import com.bitcamp.mm.member.domain.RequestMemberEdit;
  import
  com.bitcamp.mm.member.domain.RequestMemberRegist; import
  com.bitcamp.mm.member.service.MemberDeleteService; import
  com.bitcamp.mm.member.service.MemberEditService; import
  com.bitcamp.mm.member.service.MemberListService; import
  com.bitcamp.mm.member.service.MemberLoginService; import
  com.bitcamp.mm.member.service.MemberRegService;
  
  @RestController // @ResponseBody 생략한다.
  @RequestMapping("/rest/members") public class MemberRestFulController {
  
  @Autowired 
  private MemberLoginService loginService;
  
  @Autowired 
  private MemberListService listService;
  
  @Autowired 
  private MemberDeleteService deleteService;
  
  @Autowired 
  private MemberRegService regService;
  
  @Autowired 
  private MemberEditService editService;
  
  
	  //로그인
	  @CrossOrigin
	  @GetMapping("/login/login") //PathVariable이 아닌 RequestParam public
	  ResponseEntity<String> loginMember(@RequestParam("u_id") String id, @RequestParam("u_pw") String pw,HttpServletRequest request){
	  
		  int cnt = loginService.login(id, pw, request);
	  
		  System.out.println("controller :"+cnt); return new ResponseEntity<String>(cnt>0 ? "SUCCESS" : "FAIL", HttpStatus.OK);
		  }
	  
	  
  
  
	  @CrossOrigin
	  @GetMapping 
	  public ResponseEntity<List<MemberInfo>> getAllList(){
	  
		  List<MemberInfo> list = listService.getAllList();
		  
		  ResponseEntity<List<MemberInfo>> entity = new ResponseEntity<List<MemberInfo>>(list,HttpStatus.OK);
		  
		  		return entity; 
	  }
  
  
  
  
	  @CrossOrigin
	  @DeleteMapping("/{id}") 
	  public ResponseEntity<String> deleteMember(@PathVariable("id") int idx) {
	  
	  return new ResponseEntity<String>(deleteService.memberDelete(idx)>0?"SUCCESS":"FAIL",HttpStatus.OK);
	  
	  }
	  
  
  
	  @CrossOrigin
	  @PostMapping public ResponseEntity<String> regMember(RequestMemberRegist regRequest,MultipartHttpServletRequest request) {
	  
	  
	  int cnt = regService.memberInsert(request, regRequest);
	  
	  return new ResponseEntity<String>(cnt>0 ? "SUCCESS" : "FAIL" ,HttpStatus.OK);
	  
	  }
	  
	  
  
  
	  @CrossOrigin
	  @GetMapping("/{id}") public ResponseEntity<MemberInfo>
	  getMemberInfo(@PathVariable("id") int idx){
	  
	  MemberInfo info = editService.getEditFormData(idx);
	  
	  return new ResponseEntity<MemberInfo>(info, HttpStatus.OK);
	  
	  }
  
	  @CrossOrigin
	  @PutMapping("/edit/{id}") public ResponseEntity<String> editMember(@PathVariable("id") int id,RequestMemberEdit editRequest,MultipartHttpServletRequest request){
	  
	  
	  editRequest.setIdx(id);
	  
	  int cnt = editService.edit(editRequest, null, request);
	  
	  return new ResponseEntity<String>(cnt>0?"success":"fail" , HttpStatus.OK);
	  
	  }
  
  
  
  
  
  
  }
 