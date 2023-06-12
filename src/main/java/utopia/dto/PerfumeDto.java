package utopia.dto;

import lombok.Data;

@Data
public class PerfumeDto {
   private int brandId;
   private int perfumeId;
   private String perfumeImage;
   private int perfumePrice;
   private String categoryName;
   private int thumbsCt;
   private String perfumeName;
   private String brandName;
}
