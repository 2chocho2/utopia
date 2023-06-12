package utopia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utopia.dto.BrandDto;
import utopia.dto.PerfumeDto;
import utopia.mapper.PerfumeMapper;



@Service
public class PerfumeServiceImpl implements PerfumeService {
   
   @Autowired
   private PerfumeMapper perfumeMapper;

   // 검색 조건과 일치하는 게시물의 개수 조회 기능	
   @Override
   public int selectPerfumeListCount() throws Exception {
       return perfumeMapper.selectPerfumeListCount();
   }
   
   // 검색 조건과 일치하는 게시판 중 offset부터 30개를 조회하여 반납하는 기능
   @Override
   public List<PerfumeDto> selectPerfumeList(int offset) throws Exception {
      return perfumeMapper.selectPerfumeList(offset);
   }
   
   // 브랜드 선택 기능
   @Override
   public List<BrandDto> selectBrandList() throws Exception {
      return perfumeMapper.selectBrandList();
   }

   // 사용자가 선택한 브랜드의 제품 목록을 출력하는 기능
   @Override
   public List<PerfumeDto> selectBrandPerfumeList(int brandId) throws Exception {
      return perfumeMapper.selectBrandPerfumeList(brandId);
   }

   // 좋아요 수 업데이트 기능
   @Override
   public void updateThumbsCount(int perfumeId) throws Exception {
      perfumeMapper.updateThumbsCount(perfumeId);
   }





}
