package com.example;

import com.example.model.Employee;
import com.example.service.AccountRepository;
import com.example.service.EmployeeRepository;
import com.example.service.EmployeeServiceImpl;
import com.squareup.okhttp.*;
import com.squareup.okhttp.RequestBody;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by helangovan on 10/5/16.
 */

@Controller
@EnableAutoConfiguration
public class TestRequestServiceHandler {

//    @Autowired
//    EmployeeServiceImpl employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AccountRepository accountRepository;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    @ResponseBody
    String home() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.google.com").build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().toString());
        System.out.println("Got request");
        return "{\n" +
                "    \"color\": \"green\",\n" +
                "    \"message\": \"It's going to be sunny tomorrow! (yey)\",\n" +
                "    \"notify\": false,\n" +
                "    \"message_format\": \"text\"\n" +
                "}\n";
    }

    @RequestMapping(value = "/myhipchat",method = RequestMethod.GET)
    @ResponseBody
    String postHipChat() throws IOException{
        org.json.JSONObject json = new JSONObject();
        json.put("color","red");
        json.put("message","My second API notification (thumbsup)");
        json.put("notify",false);
        json.put("message_format","text");

        String postMsg = "{'color':'red','message':'My second API notification (thumbsup)','notify':false,'message_format':'text'}";
        String url = "https://lendingclub.hipchat.com/v2/room/3180558/notification?auth_token=tPyqVY9TJAsoE9tImchgb9Vc5xMGeXWRrBahlZ8G";
        OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder().url(url).addHeader("Content-Type","application/json").post(body).build();
        Response response = client.newCall(request).execute();
        return response.message();

    }

    @RequestMapping(value = "/employeeMail",method = RequestMethod.GET)
    @ResponseBody
    public String getEmployeeEmail(){
       //return employeeService.findEmployee(2).getEmail();
        Instant now = Instant.now();
        System.out.println(employeeRepository.findAll());
        Instant end = Instant.now();
        System.out.println(Duration.between(now,end).toMillis());
        return employeeRepository.findOne(2).getEmail();
    }

    @RequestMapping(value ="/saveEmp",method = RequestMethod.POST)
    @ResponseBody
    public Employee saveEmployee(@org.springframework.web.bind.annotation.RequestBody  Employee employee){
        return employeeRepository.save(employee);

    }


    @RequestMapping(value = "/accName",method = RequestMethod.GET)
    @ResponseBody
    public String getAccountName(){
        //return employeeService.findEmployee(2).getEmail();
        System.out.println(accountRepository.findAll());
        return accountRepository.findOne(1).getAccNameForRecord();
    }


}
