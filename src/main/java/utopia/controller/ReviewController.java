package utopia.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import utopia.dto.BrandDto;
import utopia.dto.MemberDto;
import utopia.dto.ReviewDto;
import utopia.service.ReviewService;

@Slf4j
@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	   // 리뷰 목록 조회 & 페이징
	   @GetMapping("/utopia/reviewList.do")
	   public ModelAndView openReviewList(
	         @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) throws Exception {
	      ModelAndView mv = new ModelAndView("/reviewList");
	      
	      List<MemberDto> reviewMember = reviewService.openReviewMember();
	      mv.addObject("reviewMember", reviewMember);
	      
	      List<BrandDto> reviewBrand = reviewService.openReviewBrand();
	      mv.addObject("reviewBrand", reviewBrand);
	      
	      List<ReviewDto> reviewList = reviewService.selectReviewListPage((currentPage - 1) * 8);
	      mv.addObject("reviewList", reviewList);
	      
	      // 페이징 정보 출력에 사용되는 변수
	      mv.addObject("pageCount", Math.ceil(reviewService.selectReviewListCount() / 8.0));
	      mv.addObject("currentPage", currentPage);
	      
	      return mv;
	   }
	
	// 리뷰 작성화면
	@GetMapping("/utopia/writeReview.do")
	public String writeReview() throws Exception {
		return "/writeReview";
	}
	
	// 리뷰 작성 제출(이미지 파일과 나머지 정보 Dto에 추가) - DB 저장
	@PostMapping("/utopia/insertReview.do")
	public String insertReview(@RequestParam("file") MultipartFile file, ReviewDto reviewDto) throws Exception {
		reviewService.insertReview(reviewDto, file);
		
		return "redirect:/utopia/reviewList.do";
	}
	
	// 사진 다운로드
	@GetMapping("/utopia/download.do")
	public void downloadFile(@RequestParam int reviewId, HttpServletResponse response) throws Exception {
		
		// ReviewDto에서 해당하는 Review 조회
		ReviewDto reviewDto = reviewService.selectReviewByReviewId(reviewId);
		
		// Review에서 reviewImg 정보 추출
		String reviewImg = reviewDto.getReviewImg();
		
		// reviewImage를 읽어서 전달
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setHeader("Content-Disposition", "inline;");
			
			byte[] buf = new byte[1024];
			fis = new FileInputStream(reviewImg);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			int read;
			while((read = bis.read(buf, 0, 1024)) != -1) {
				bos.write(buf, 0, read);
			}
		} finally {
			bos.close();
			bis.close();
			fis.close();
		}
	}
	
	// 리뷰 상세 조회
	@GetMapping("/utopia/openReviewDetail.do")
	public ModelAndView openReviewDetail(@RequestParam int reviewId) throws Exception {
		ModelAndView mv = new ModelAndView("/reviewDetail");
		
		ReviewDto reviewDto = reviewService.selectReviewDetail(reviewId);
		mv.addObject("review", reviewDto);
		
		return mv;
	}
	

	// 리뷰 수정 작성 화면
	@GetMapping("/utopia/openUpdateReview.do")
	public ModelAndView openUpdateReview(@RequestParam int reviewId) throws Exception {
		ModelAndView mv = new ModelAndView("/updateReview");
		
		ReviewDto reviewDto = reviewService.selectReviewByReviewId(reviewId);
		
		System.out.println(">>>>>>>>>>>>>>" + reviewId + " : " + reviewDto.getReviewTitle());
		
		mv.addObject("review", reviewDto);
		
		return mv;
	}
	
	// 리뷰 수정
	@PostMapping("/utopia/updateReview.do")
	public String updateReview(ReviewDto reviewDto) throws Exception {
		reviewService.updateReview(reviewDto);
		return "redirect:/utopia/reviewList.do";
	}
	
	// 리뷰 삭제
	@PostMapping("/utopia/deleteReview.do")
	public String deleteReview(ReviewDto reviewDto) throws Exception {
		reviewService.deleteReview(reviewDto.getReviewId());;
		return "redirect:/utopia/reviewList.do";
	}
}
