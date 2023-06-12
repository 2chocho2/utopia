package utopia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import utopia.dto.BrandDto;
import utopia.dto.PerfumeDto;


@Mapper
public interface PerfumeMapper {
	// 검색 조건과 일치하는 게시물의 개수 조회 기능
	int selectPerfumeListCount() throws Exception;
	
	// 검색 조건과 일치하는 게시판 중 offset부터 30개를 조회하여 반납하는 기능
	List<PerfumeDto> selectPerfumeList(int offset) throws Exception;
	
	// 브랜드 선택 기능
	List<BrandDto> selectBrandList() throws Exception;
	
	// 사용자가 선택한 브랜드의 제품 목록을 출력하는 기능
	List<PerfumeDto> selectBrandPerfumeList(int brandId) throws Exception;
	
	// 좋아요 수 업데이트 기능
	public void updateThumbsCount(int perfumeId) throws Exception;
	
}