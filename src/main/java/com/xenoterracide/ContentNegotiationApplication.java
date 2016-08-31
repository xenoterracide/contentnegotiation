package com.xenoterracide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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


    @Configuration /// class is completely optional to the problem
    static class SecConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure( HttpSecurity http ) throws Exception {
            http.authorizeRequests().mvcMatchers( "/mypath" ).denyAll()
                    .and().csrf().disable();
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
