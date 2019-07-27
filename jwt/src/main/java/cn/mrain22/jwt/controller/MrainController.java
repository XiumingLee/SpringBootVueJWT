package cn.mrain22.jwt.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class MrainController {
    @GetMapping("/hello")
    public String main(){
        return "hello";
    }

    /**
     *  /test1 和 /test2 需要登录后才能访问
     */
    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }

    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }

    @GetMapping("/needAdminRole")
    public String needAdminRole(){
        return "needAdminRole";
    }

    @RequestMapping("/loginInfo")
    public String loginInfo(){
        return "请先登录！";
    }

    /**
     * 注销登录成功后跳转的路由
     * @return
     */
    @RequestMapping("/logoutSuccess")
    public String logoutSuccess(){
        return "退出成功！";
    }
}
