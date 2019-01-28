package com.companies.companiesapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyController {

    @GetMapping("/greeting")
    public String getGreeting() {
        return "Tere!!!";
    }

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        Company company = new Company();
        company.setName("Pingviin OÜ");
        company.setLogo("https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/South_Shetland-2016-Deception_Island%E2%80%93Chinstrap_penguin_%28Pygoscelis_antarctica%29_04.jpg/600px-South_Shetland-2016-Deception_Island%E2%80%93Chinstrap_penguin_%28Pygoscelis_antarctica%29_04.jpg");

        Company company2 = new Company();
        company2.setName("AS Arco Vara");
        company2.setLogo("https://www.nasdaqbaltic.com/market/logo.php?issuer=ARC");

        List<Company> companies = new ArrayList<>();
        companies.add(company);
        companies.add(company2);

        return companies;
    }

}
