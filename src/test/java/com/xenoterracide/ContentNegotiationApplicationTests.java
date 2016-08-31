package com.xenoterracide;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
public class ContentNegotiationApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void testpasses() throws Exception {
        String json = mapper.writeValueAsString( new ContentNegotiationApplication.MyDto( "hello" ) );
        this.mvc.perform(
                post( "/authentication/password" ).contentType( MediaType.APPLICATION_JSON ).content( json ) )
                .andExpect( status().is2xxSuccessful() )
                .andExpect( content().string( "hello" ) );
    }

}
