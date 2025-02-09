package com.scaler.productservice.commons;

import com.scaler.productservice.DTO.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommons {

    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO ValidateToken(String token) {
        // Here i will send a request to the user service to validate the token
        ResponseEntity<UserDTO> userDTOResponse = restTemplate.postForEntity(
                "http://localhost:9001/users/validate/" + token,
                null,
                UserDTO.class
        );
        if(userDTOResponse.getBody()==null){
            return null;
        }else{
            return userDTOResponse.getBody();
        }
    }

    public UserDTO getUserById(Long id){
        UserDTO userDTO = restTemplate.getForEntity("http://localhost:8181/users/"+id, UserDTO.class).getBody();
            return userDTO;
    }
}
