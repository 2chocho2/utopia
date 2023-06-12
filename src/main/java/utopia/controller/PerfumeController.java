package utopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import utopia.dto.BrandDto;
import utopia.dto.PerfumeDto;
import utopia.service.PerfumeService;

@Controller
@Slf4j
public class PerfumeController {

	@Autowired
	private PerfumeService perfumeService ;
	
		// 제품 목록 버튼을 클릭했을 때, 전체 향수 리스트를 출력
		// 각각의(5개) 브랜드명 버튼을 클릭했을 때, 해당 브랜드의 향수 리스트를 출력 
	   @GetMapping("/utopia/perfume")   
	   public ModelAndView perfume(
	         @RequestParam(value = "brandId", required = false, defaultValue = "0") int brandId, 
	         @RequestParam(value = "currentPage", required = false, defaultValue="1") int currentPage) throws Exception {
	      
	         ModelAndView mv = new ModelAndView("perfume.html");
	            
	      if (brandId == 0) {
	         List<PerfumeDto> list = perfumeService.selectPerfumeList((currentPage - 1) *30 );
	         mv.addObject("perfumeList", list);
	         
	         mv.addObject("pageCount", Math.ceil(perfumeService.selectPerfumeListCount() / 30.0));
	         mv.addObject("currentPage", currentPage);
	         
	      } else {
	         List<PerfumeDto> list = perfumeService.selectBrandPerfumeList(brandId);
	         mv.addObject("perfumeList", list);
	         
	      }
	      
	      List<BrandDto> brandDto = perfumeService.selectBrandList();
	      mv.addObject("brandList", brandDto);
	      
	      return mv;   
	   }

	   // 좋아요 수 업데이트 기능
	  @RequestMapping("/utopia/updateThumbsCount")
	  public String UpdateThumbsCount(int perfumeId) throws Exception { 
		  perfumeService.updateThumbsCount(perfumeId); 
		  return "redirect:/utopia/perfume";
	 }
}

