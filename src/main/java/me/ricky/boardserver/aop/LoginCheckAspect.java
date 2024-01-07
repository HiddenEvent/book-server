package me.ricky.boardserver.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import me.ricky.boardserver.utils.SessionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@Component
@Slf4j
@Aspect
public class LoginCheckAspect {
    private final HttpServletRequest request;

    public LoginCheckAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("@annotation(me.ricky.boardserver.aop.LoginCheck) && @annotation(loginCheck)")
    public Object adminLoginCheck(ProceedingJoinPoint joinPoint, LoginCheck loginCheck) throws Throwable {
        log.info("LoginCheckAspect : {}", loginCheck.type());
        HttpSession session = request.getSession();
        String id = null;
        int idIndex = 0;

        switch (loginCheck.type()) {
            case USER -> {
                id = SessionUtil.getLoginMemberId(session);
            }
            case ADMIN -> {
                id = SessionUtil.getLoginAdminId(session);
            }
        }

        if (id == null) {
            log.error("{} accountName : {}", joinPoint.toString(), id);
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.") {
            };
        }
        Object[] modifiedArgs = joinPoint.getArgs();
        if (joinPoint != null) {
            modifiedArgs[idIndex] = id;
        }
        return joinPoint.proceed(modifiedArgs);
    }
}
