package com.xenoterracide;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith( SpringRunner.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class ContentNegotiationApplicationIntegrationTests {

    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testRequest() throws Exception {
        String json = mapper.writeValueAsString( new ContentNegotiationApplication.MyDto( "hello" ) );
        headers.setContentType( MediaType.APPLICATION_JSON );

        HttpEntity<String> entity = new HttpEntity<>( json, headers );

        ResponseEntity<String> response = template.postForEntity( "/unrelated", entity, String.class );
        assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.OK );
        assertThat( response.getBody() ).isEqualTo( "hello" );
    }

}
