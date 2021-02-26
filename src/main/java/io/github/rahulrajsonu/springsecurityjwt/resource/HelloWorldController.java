package io.github.rahulrajsonu.springsecurityjwt.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import io.github.rahulrajsonu.springsecurityjwt.MyUserDetailsService;
import io.github.rahulrajsonu.springsecurityjwt.json.MetadataSchema;
import io.github.rahulrajsonu.springsecurityjwt.models.AuthenticationRequest;
import io.github.rahulrajsonu.springsecurityjwt.models.AuthenticationResponse;
import io.github.rahulrajsonu.springsecurityjwt.util.JwtUtil;
import io.github.rahulrajsonu.springsecurityjwt.util.Validator;
import io.github.rahulrajsonu.springsecurityjwt.util.ValidatorConfig;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
class HelloWorldController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private Validator validator;

    @RequestMapping(value ="/sanitize", method = RequestMethod.GET)
    public String firstPage(@RequestParam("plain-text") String text) {
        String response = StringEscapeUtils.escapeHtml4(text);
        System.out.println("------->"+response);
        return response;
    }

    @RequestMapping(value ="/config", method = RequestMethod.GET)
    public String getConfig() {
        System.out.println(validator.validate(Validator.Fields.segment,"valid1"));
        System.out.println(validator.validate(Validator.Fields.segment,"in_valid1"));
        return "Check console" ;
    }

    @RequestMapping(value ="/validate-json", method = RequestMethod.GET)
    public String validateJson() {

        final Type METADATA_TYPE = new TypeToken<MetadataSchema>() {}.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("5892_test-sid-865.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MetadataSchema data = gson.fromJson(reader, METADATA_TYPE); // contains the whole reviews list
        System.out.println(data);
        return "Check console" ;
    }

    @RequestMapping(value ="/extract", method = RequestMethod.GET)
    public List<String> extractUrlComponent(){
        String url = "http://localhost:8080/extract/url/components/77?first=1&second=2";
        List<String> response = new ArrayList<>();
        UriComponents uri = UriComponentsBuilder.fromUriString(url.trim()).build();
        MultiValueMap<String, String> queryParams = uri.getQueryParams();
        System.out.println(queryParams);
        System.out.println(uri.getPath());
        uri.getQueryParams().values().forEach(values -> {
            for (String value : values) {
                response.add(value);
            }
        });
        return response;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
