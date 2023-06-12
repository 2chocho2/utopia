package utopia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import utopia.dto.BrandDto;
import utopia.dto.MemberDto;
import utopia.dto.ReviewDto;

@Mapper
public interface ReviewMapper {

	// 리뷰 목록 조회
	public List<ReviewDto> selectReviewList() throws Exception;
	public List<BrandDto> openReviewBrand() throws Exception;
	public List<MemberDto> openReviewMember() throws Exception;

	// 검색 조건과 일치하는 게시판 개수를 반환
	int selectReviewListCount() throws Exception;

	// 검색 조건과 일치하는 게시판 중 offset에서부터 8개만 조회해서 반환
	List<ReviewDto> selectReviewListPage(int offset) throws Exception;

	// 리뷰 작성
	// 리뷰 사진 저장
	public void insertReview(ReviewDto reviewDto) throws Exception;
	String saveFile(MultipartFile file) throws Exception;
	
	// (사진 로드 시) 리뷰 아이디에 맞춰 리뷰 정보 조회
	public ReviewDto selectReviewByReviewId(int reviewId) throws Exception;

	// 리뷰 상세 조회
	public ReviewDto selectReviewDetail(int reviewId) throws Exception;

	// 리뷰 수정
	public void updateReview(ReviewDto reviewDto) throws Exception;

	// 리뷰 삭제
	public void deleteReview(int reviewId) throws Exception;

	
}
