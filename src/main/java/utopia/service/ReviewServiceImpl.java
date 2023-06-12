package utopia.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import utopia.dto.BrandDto;
import utopia.dto.MemberDto;
import utopia.dto.ReviewDto;
import utopia.mapper.ReviewMapper;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewMapper reviewMapper;

	// 설정 파일에서 업로드 파일이 저장되는 경로를 가져와서 가지고 있는 변수
	@Value("${application.upload-path}")
	private String uploadPath;

	// 리뷰 목록 조회
	@Override
	public List<ReviewDto> selectReviewList() throws Exception {
		return reviewMapper.selectReviewList();
	}
	
	public List<MemberDto> openReviewMember() throws Exception {
		return reviewMapper.openReviewMember();
	}

	public List<BrandDto> openReviewBrand() throws Exception {
		return reviewMapper.openReviewBrand();
	}

	// 검색 조건과 일치하는 게시판 개수를 반환
	@Override
	public int selectReviewListCount() throws Exception {
		return reviewMapper.selectReviewListCount();
	}

	// 검색 조건과 일치하는 게시판 중 offset에서부터 8개만 조회해서 반환
	@Override
	public List<ReviewDto> selectReviewListPage(int offset) throws Exception {
		return reviewMapper.selectReviewListPage(offset);
	}

	// 리뷰 작성
	@Override
	public void insertReview(ReviewDto reviewDto, MultipartFile file) throws Exception {
		String savedFilePath = saveFile(file);
		reviewDto.setReviewImg(savedFilePath);

		reviewMapper.insertReview(reviewDto);
	}

	// 파일을 저장하고 저장 경로를 반환하는 메서드
	// 리뷰 사진 저장
	@Override
	public String saveFile(MultipartFile file) throws Exception {
		String savedFilePath = uploadPath + file.getOriginalFilename();

		File uploadFile = new File(savedFilePath);
		file.transferTo(uploadFile);

		return savedFilePath;
	}

	// (사진 로드 시) 리뷰 아이디에 맞춰 리뷰 정보 조회
	@Override
	public ReviewDto selectReviewByReviewId(int reviewId) throws Exception {
		return reviewMapper.selectReviewByReviewId(reviewId);
	}

	// 리뷰 상세 조회
	@Override
	public ReviewDto selectReviewDetail(int reviewId) throws Exception {
		return reviewMapper.selectReviewDetail(reviewId); // 게시판 상세 내용을 조회
	}
		
	// 리뷰 수정
	@Override
	public void updateReview(ReviewDto reviewDto) throws Exception {
		reviewMapper.updateReview(reviewDto);
	}

	// 리뷰 삭제
	@Override
	public void deleteReview(int reviewId) throws Exception {
		reviewMapper.deleteReview(reviewId);
	}
}
