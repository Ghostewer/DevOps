package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class DiceController {
        @GetMapping("/roll")
    public int rollDice() {
        
        int diceResult = ThreadLocalRandom.current().nextInt(1, 7);
        return diceResult;
    }
}
