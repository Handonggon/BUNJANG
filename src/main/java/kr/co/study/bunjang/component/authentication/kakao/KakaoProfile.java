package kr.co.study.bunjang.component.authentication.kakao;

import com.querydsl.core.annotations.Generated;

import lombok.Data;

@Data
@Generated("jsonschema2pojo")
public class KakaoProfile {
	public Long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;

	@Data
	@Generated("jsonschema2pojo")
	public class Properties {
		public String nickname;
	}
	
	@Data
	@Generated("jsonschema2pojo")
	public class KakaoAccount {
		public Boolean profile_nickname_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
		
		@Data
		@Generated("jsonschema2pojo")
		public class Profile {
			public String nickname;
		}
	}
}

