package com.cbt.cbtmonolithnov23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController // Spring Managed Bean of type Controller for creating Rest API
@RequestMapping("api/v1")
public class MainRestContoller
{
    @Autowired // One way of Dependency Injection | We are consuming a Bean of type CredentialRepository
    private CredentialRepository credentialRepository;

    @Autowired
    private UserdetailRepository userDetailsRepository;

    @Autowired
    private ProductofferRepository productOfferRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FullProductOfferService fullProductOfferService;

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

    @GetMapping("get/userdetails")
    public ResponseEntity<Userdetail> getUserDetails(@RequestParam("username") String username) {

        if (userDetailsRepository.findById(username).isPresent())
        {

            return ResponseEntity.ok(userDetailsRepository.findById(username).get());

        }
        else
        {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping("save/userdetails")
    public ResponseEntity<String> saveUserdetails(@RequestBody Userdetail userdetail)
    {
        userDetailsRepository.save(userdetail);
        return ResponseEntity.ok("User Details Saved Successfully");
    }

    @PostMapping("save/offer")
    public ResponseEntity<String> saveProductOffer(@RequestBody Productoffer productoffer)
    {
        productoffer.setId(String.valueOf((int) (Math.random()*10000)));
        productOfferRepository.save(productoffer);
        return ResponseEntity.ok("New Offer Saved");
    }

    @GetMapping("get/products")
    public ResponseEntity<List<Product>> getProducts()
    {

        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping("save/order")
    public ResponseEntity<String> saveOrder(@RequestBody Order order)
    {
        order.setOrderid(String.valueOf((int) (Math.random()*10000)));
        orderRepository.save(order);

        return ResponseEntity.ok("New Order Saved");
    }

    @GetMapping("get/offers")
    public ResponseEntity<List<Productoffer>> getOffers()
    {
        return ResponseEntity.ok(productOfferRepository.findAll());
    }

    @GetMapping("get/offers/full")
    public ResponseEntity<List<FullProductOffer>> getFullOffers()
    {
       return  ResponseEntity.ok(productOfferRepository.findAll().stream().
                map(productoffer -> fullProductOfferService.composeFullOffer(productoffer.getId())).
                collect(Collectors.toList()));
    }

}
