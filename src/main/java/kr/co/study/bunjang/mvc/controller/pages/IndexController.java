package kr.co.study.bunjang.mvc.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.study.bunjang.component.properties.KakaoOAuthProperties;

@Controller
public class IndexController {

	@RequestMapping(value = "/")
	public String index() {
		return "pages/index";
	}

	@RequestMapping(value = "/splash")
	public String splash() {
		return "pages/splash";
	}

	@RequestMapping(value = "/signup")
	public String signup() {
		return "pages/signup";
	}

	@RequestMapping(value = "/login/kakao")
	public String kakaoLogin(RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("client_id", KakaoOAuthProperties.CLIENT_ID);
		redirectAttributes.addAttribute("redirect_uri", KakaoOAuthProperties.REDIRECT_URL);
		redirectAttributes.addAttribute("response_type", "code");
		// redirectAttributes.addAttribute("scope", "");			//사용자에게 동의 요청할 동의 항목 ID 목록
		// redirectAttributes.addAttribute("prompt", "");			//카카오톡에서 자동 로그인, 기존 로그인 여부와 상관없이 로그인 요청 시 사용
		// redirectAttributes.addAttribute("service_terms", "");	//약관 선택해 동의 받기 요청 시 사용
		// redirectAttributes.addAttribute("state", "");			//카카오 로그인 과정 중 동일한 값을 유지하는 임의의 문자열
		// redirectAttributes.addAttribute("nonce", "");			//OpenID Connect를 통해 ID 토큰을 함께 발급받을 경우, ID 토큰 재생 공격을 방지하기 위해 사용

		return "redirect:" + KakaoOAuthProperties.BASE_URL + "/oauth/authorize";
	}
}