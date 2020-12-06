package com.xybbz.generator;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/table")
public class TableGenerator {


    @GetMapping()
    public void tableGenerator() {
        MpGenerator mpGenerator = new MpGenerator();

    }

}
