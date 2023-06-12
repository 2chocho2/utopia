package utopia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import utopia.dto.LoginDto;
import utopia.dto.MemberDto;
import utopia.dto.PerfumeDto;

@Mapper
public interface LoginMapper {
	// 로그인에 대한 요청
	public MemberDto login(LoginDto loginDto) throws Exception;
	
	// 회원가입에 대한 요청
	void memberInsert(MemberDto memberDto) throws Exception;
	
	// 메인 화면에 베스트셀러 출력
	List<PerfumeDto> selectPerfumeBest() throws Exception;
}
