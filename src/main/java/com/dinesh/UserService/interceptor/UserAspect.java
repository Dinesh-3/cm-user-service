package com.dinesh.UserService.interceptor;

import com.dinesh.UserService.entity.User;
import com.dinesh.UserService.entity.UserLog;
import com.dinesh.UserService.repository.UserLogRepository;
import com.dinesh.UserService.util.ResponseBody;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {

    @Autowired
    private UserLogRepository userLogRepository;

    @AfterReturning(
            pointcut = "execution(public * com.dinesh.UserService.service.UserService.create(com.dinesh.UserService.entity.User))",
            returning = "result"
    )
    public void log(JoinPoint joinPoint, ResponseEntity<ResponseBody> result) {
        Object data = result.getBody().getData();
        if(data instanceof User) {
            User user = (User) data;
            userLogRepository.save(new UserLog(200, user.getId(), user.getDepartment_id()));
        }
    }
}
