package utopia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utopia.dto.LoginDto;
import utopia.dto.MemberDto;
import utopia.dto.PerfumeDto;
import utopia.mapper.LoginMapper;


@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginMapper loginMapper;
	
	// 로그인에 대한 요청
	@Override
	public MemberDto login(LoginDto loginDto) throws Exception {
		return loginMapper.login(loginDto);
	}
	
	// 회원가입에 대한 요청
	@Override
	public void memberInsert(MemberDto memberDto) throws Exception {
		loginMapper.memberInsert(memberDto);
	}
	
	// 메인 화면에 베스트셀러 출력
	@Override
	public List<PerfumeDto> selectPerfumeBest() throws Exception {
		return loginMapper.selectPerfumeBest();
	}
}
