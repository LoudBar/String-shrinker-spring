package ru.loudbar.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.exceptions.JedisConnectionException;
import ru.loudbar.services.StringService;

@Controller
@RequiredArgsConstructor
public class StringController {

    private final StringService stringService;

    private String error = "";

    @GetMapping("/")
    public String getMainPage() {
        return "main_page";
    }

    @PostMapping("/shrink")
    public String shrinkString(@RequestParam String string, Model model) {
        try {
            if (string.length() > 2048 | string.isBlank()) {
                error = "string length should be in between 1 and 2048 characters";
                model.addAttribute("error", error);
                return "main_page";
            }
            model.addAttribute("out", stringService.encode(string));
            return "main_page";
        } catch (JedisConnectionException e) {
            error = "wrong connection attributes";
            model.addAttribute("error", error);
            return "main_page";
        }
    }

    @PostMapping("/unshrink")
    public String unshrinkString(@RequestParam String string, Model model) {
        try {
            String out = stringService.decode(string);
            if (out == null | (out != null && out.isBlank())) {
                error = "incorrect short string";
                model.addAttribute("error", error);
                return "main_page";
            }
            model.addAttribute("out", out);
            return "main_page";
        } catch (JedisConnectionException e) {
            error = "wrong connection attributes";
            model.addAttribute("error", error);
            return "main_page";
        }
    }
}
