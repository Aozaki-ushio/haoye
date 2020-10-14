package dmzsmos.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Scope(value = "prototype")
public class HttpRequestInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(HttpRequestInterceptor.class);

    private static final PathMatcher matcher = new AntPathMatcher();
    private static final String TOKEN = "token";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();

//        if (matcher.match("/**/mdos.api/**", uri)) {
//            if (logger.isDebugEnabled()) {
//                logger.debug(String.format(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> Request intercepted, uri=[%s]", uri));
//            }
//
//            String token = getCookieValue(request, TOKEN);
//            if (token == null) {
//                sendResponse(response, HttpStatus.UNAUTHORIZED.value(), "{\"code\": \"401\", \"message\": \"未登录或会话已失效\"}");
//                return false;
//            } else {
//                String userId = checkToken(request, token);
//                if (userId == null) {
//                    sendResponse(response, HttpStatus.UNAUTHORIZED.value(), "{\"code\": \"403\", \"message\": \"非法请求！\"}");
//                    return false;
//                }
//            }
//        }

        return true;
    }

    private String checkToken(HttpServletRequest request, String token) {
        String url = String.join("", dmzsmos.utils.ConfigParam.getInstance().getBaseUrl(), "tenant/serviceapi/mdos.api/v1/user/verify?token=", token);
        String rsp = restTemplate.getForObject(url, String.class);
        JsonObject json = new Gson().fromJson(rsp, JsonObject.class);

        if (json.get("errCode") == null || "null".equals(json.get("errCode").toString())) {
            JsonObject jsonObject = json.getAsJsonObject("data");
            String tenantId = jsonObject.get("tenantId").getAsString();
            String userId = jsonObject.get("userId").getAsString();
            String realname = jsonObject.get("realname").getAsString();
            String apikey = jsonObject.getAsJsonArray("apiKeys").get(0).getAsString();

            dmzsmos.utils.RequestParamsHolder.setParameter("tenantId", tenantId);
            dmzsmos.utils.RequestParamsHolder.setParameter("userId", userId);
            dmzsmos.utils.RequestParamsHolder.setParameter("realname", realname);
            dmzsmos.utils.RequestParamsHolder.setParameter("apikey", apikey);

            return userId;
        }

        return null;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }

    private String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (key.equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }

    private void sendResponse(HttpServletResponse response, int statusCode, String content) throws IOException {
        response.setStatus(statusCode);
        response.setHeader("Cache-Control", "max-age=0, no-cache, no-store, must-revalidate");
        OutputStream out = response.getOutputStream();
        out.write(content.getBytes());
        out.flush();
        out.close();
    }

}