package org.example.controller;

import com.google.common.util.concurrent.ListenableFuture;
import org.example.entity.User;
import org.example.repo.UserRepo;
import org.example.reponse.UsersRP;
import org.example.reponse.dto.UsersDtoRp;
import org.example.request.PostUserRQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/users")
public class UserController extends BaseResource {

    @Autowired
    UserRepo userRepo;

    @GetMapping()
    public DeferredResult<?> get() {
        ListenableFuture<?> future = asyncExecute(() -> {
            log.info("test");
            List<User> users = (List<User>) userRepo.findAll();
            List<UsersDtoRp> usersDTOPR = new ArrayList<>();
            users.stream().forEach(user -> {
                UsersDtoRp usersDtoRp = new UsersDtoRp();
                BeanUtils.copyProperties(user, usersDtoRp);
                usersDTOPR.add(usersDtoRp);
            });
            UsersRP usersRP = new UsersRP();
            usersRP.setData(usersDTOPR);
            usersRP.setStatus(200);
            return usersRP;
        });
        return callBackResponse(future);

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public DeferredResult<?> post(@Valid @RequestBody() PostUserRQ userRQ) {
        ListenableFuture<?> future = asyncExecute(() -> {
            log.info("test");
            User user = new User();
            user.setFirstName(userRQ.getFirstName());
            user.setLastName(userRQ.getFirstName());
            return userRepo.save(user);
        });
        return callBackResponse(future);

    }

}
