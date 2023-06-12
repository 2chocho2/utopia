package utopia.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
	
	private int MemberId;

	@NotBlank(message = "이메일은 필수 입력값입니다.")
	private String MemberEmail;
	
	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
	private String MemberPw;
	
	@NotBlank(message = "닉네임은 필수 입력값입니다.")
	private String MemberName;
	
	
}
