package pt.api.stock_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping("")
    public ResponseEntity<String> am_i_alive() {
       return new ResponseEntity<>("I am alive and kicking!", HttpStatus.OK);
    }

}
