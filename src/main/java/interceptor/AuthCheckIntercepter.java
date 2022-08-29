package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthCheckIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession(false);
        if(httpSession!=null){
            Object authInfo = httpSession.getAttribute("authInfo");
            if(authInfo!=null){
                return true;
            }
        }
        response.sendRedirect(request.getContextPath()+"/login");
        return false;
    }
}
