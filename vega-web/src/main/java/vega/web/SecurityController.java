package vega.web;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Security")
public class SecurityController {

    @RequestMapping(value={"/login"},method = {RequestMethod.POST})
    public @ResponseBody
    String login(@RequestParam String login,@RequestParam String password){
        return "crypted_login";
    }
}
