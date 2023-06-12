package utopia.service;


import java.util.List;

import org.springframework.stereotype.Service;

import utopia.dto.LoginDto;
import utopia.dto.MemberDto;
import utopia.dto.PerfumeDto;

@Service
public interface LoginService {
	// 로그인에 대한 요청
	public MemberDto login(LoginDto loginDto) throws Exception;

	// 회원가입에 대한 요청
	public void memberInsert(MemberDto memberDto)throws Exception;
	
	// 메인 화면에 베스트셀러 출력
	List<PerfumeDto> selectPerfumeBest() throws Exception;
}
