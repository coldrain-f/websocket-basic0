package me.websocket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChattController {

    @GetMapping("/chatt")
    public String chatt() {
        return "/chatt/chatt";
    }
}
