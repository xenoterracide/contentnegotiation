package com.xenoterracide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@SpringBootApplication
public class ContentNegotiationApplication {

    public static void main( String[] args ) {
        SpringApplication.run( ContentNegotiationApplication.class, args );
    }


    public static class MyDto {
        private String name;

        public MyDto() {
        }

        public MyDto( String name ) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }
    }

    @RestController( "/mypath" )
    static class MyController {
        @RequestMapping( method = RequestMethod.POST )
        ResponseEntity<?> myroute( @Valid @RequestBody final MyDto dto ) {
            return ResponseEntity.ok( dto.getName() );
        }

    }
}
