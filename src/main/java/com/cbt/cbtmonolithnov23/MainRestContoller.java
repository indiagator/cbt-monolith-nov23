package com.cbt.cbtmonolithnov23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class MainRestContoller
{

    @Autowired
    private CredentialRepository credentialRepository;

    @GetMapping("sayhello")
    public ResponseEntity<String> sayHello()
    {
        return ResponseEntity.ok("Hello we are learning Microservices using Spring");
    }

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody Credential credential)
    {
        credentialRepository.save(credential);
        return ResponseEntity.ok("User Signed Up Successfully");
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Credential credential)
    {
        Optional<Credential> tempCredential = credentialRepository.findById(credential.getUsername());
        if(tempCredential.isPresent())
        {
            if(tempCredential.get().getPassword().equals(credential.getPassword()))
            {
                return ResponseEntity.ok("AGHGHSG568586258JHGHG"); //Security Token
            }
            else{
                return ResponseEntity.badRequest().body("Invalid Password");
            }
        }
        else {
            return ResponseEntity.badRequest().body("Invalid Username");
        }
    }

}
