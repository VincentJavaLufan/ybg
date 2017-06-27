package com.ybg.component.jwt.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ybg.component.jwt.AccessToken;
import com.ybg.component.jwt.Audience;
import com.ybg.component.jwt.JwtHelper;
import com.ybg.component.jwt.LoginPara;
import com.ybg.component.jwt.MyUtils;
import com.ybg.component.jwt.ResultMsg;
import com.ybg.component.jwt.ResultStatusCode;
import com.ybg.rbac.user.domain.UserVO;
import com.ybg.rbac.user.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("jwt校验API")
@RestController
public class JsonWebToken {
	
	// @Autowired
	// private UserInfoRepository userRepositoy;
	@Autowired
	private Audience	audienceEntity;
	@Autowired
	LoginService		loginService;
	
	@ApiOperation(value = "token校验", notes = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = { "oauth/token" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Object getAccessToken(@RequestBody LoginPara loginPara) {
		ResultMsg resultMsg;
		try {
			if (loginPara.getClientId() == null || loginPara.getClientId().compareTo(audienceEntity.getClientId()) != 0) {
				resultMsg = new ResultMsg(ResultStatusCode.INVALID_CLIENTID.getErrcode(), ResultStatusCode.INVALID_CLIENTID.getErrmsg(), null);
				return resultMsg;
			}
			// 验证码校验在后面章节添加
			// 验证用户名密码
			UserVO user = (UserVO) loginService.loadUserByUsername(loginPara.getUserName());
			// UserInfo user = userRepositoy.findUserInfoByName(loginPara.getUserName());
			if (user == null) {
				resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(), ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
				return resultMsg;
			}
			else {
				String md5Password = MyUtils.getMD5(loginPara.getPassword() + user.getCredentialssalt());
				if (md5Password.compareTo(user.getPassword()) != 0) {
					resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(), ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
					return resultMsg;
				}
			}
			// 拼装accessToken
			String accessToken = JwtHelper.createJWT(loginPara.getUserName(), String.valueOf(user.getUsername()), user.getRoleid(), audienceEntity.getClientId(), audienceEntity.getName(), audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());
			// 返回accessToken
			AccessToken accessTokenEntity = new AccessToken();
			accessTokenEntity.setAccess_token(accessToken);
			accessTokenEntity.setExpires_in(audienceEntity.getExpiresSecond());
			accessTokenEntity.setToken_type("bearer");
			resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), accessTokenEntity);
			return resultMsg;
		} catch (Exception ex) {
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrcode(), ResultStatusCode.SYSTEM_ERR.getErrmsg(), null);
			return resultMsg;
		}
	}
}