package cool.kar.neteasecloudmusic.demo.controller;

import cool.kar.neteasecloudmusic.demo.vo.SuccessVO;
import cool.kar.neteasecloudmusic.demo.vo.VO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @PostMapping("/logout")
    public VO logout() {
        return new SuccessVO("success");
    }

    @PostMapping("/login")
    public VO login() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", "admin-token");
        return new SuccessVO(map);
    }

    @GetMapping("/info")
    public VO info(String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("introduction", "I am a super administrator");
        map.put("name", "Super Admin");
        map.put("roles", new String[]{"admin"});
        return new SuccessVO(map);
    }


}
