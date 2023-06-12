package utopia.dto;

import lombok.Data;

@Data
public class ReviewDto {
	private int reviewId;
	private String reviewTitle;
	private String reviewContents;
	private String reviewImg;
	private String usePerfume;
	private String reviewDt;
	private String perfumeBrand;
	private String memberName;
}
