package com.companies.companiesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    //viis kuidas pääseda ligi springile. metatasandi direktiiv.
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/greeting")
    public String getGreeting() {
        return "Tere!!!";
    }

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        List<Company> companies = jdbcTemplate.query("select * from company", (row, count) -> {
            int companyId = row.getInt("id");
            String companyName = row.getString("name");
            String companyLogo = row.getString("logo");


            Company company = new Company();
            company.setId(companyId);
            company.setName(companyName);
            company.setLogo(companyLogo);


            return company;


        });

        return companies;
    }

    @GetMapping("/company/{id}")
    public Company getCompanyById(@PathVariable int id) {

        Company result = jdbcTemplate.queryForObject("select * from company where id = ?", new Object[]{id}, (row, count) -> {
            int companyId = row.getInt("id");
            String companyName = row.getString("name");
            String companyLogo = row.getString("logo");

            Company company = new Company();
            company.setId(companyId);
            company.setName(companyName);
            company.setLogo(companyLogo);
            return company;

//); //queryForObject võtab ühe konkr objekti
        });

        return result;

    }

    //annotatsioon - metatasandi direktiiv. deklaratiivselt. ma ei tea, kuidas, aga deklareerin. kuidas teed pole minu asi, spring boot nt teeb.
    // csharp atribuudid, pythonis decorator


    @DeleteMapping("/company/{id}")
    public void deleteCompany(@PathVariable int id) {

        jdbcTemplate.update("delete from company where id = ?", id); //küsimärk parameeter, mille annan, meil id

//urlil ei saagi, teha, see on hea. get method not allowed


    }

    @PostMapping("/company")
    public void addCompany(@RequestBody Company company) {
        jdbcTemplate.update("insert into company(name, logo) values (?, ?)", company.getName(), company.getLogo());


    }


    @PostMapping("/companies")
    public void addCompanies(@RequestBody Company[] companies) {
        for (Company company : companies) {
            addCompany(company);
        }




    }


    @PutMapping("/company")
    public void editCompany(@RequestBody Company company) {
        jdbcTemplate.update("update company set name = ?, logo = ? where id = ?",
                company.getName(), company.getLogo(), company.getId());
    }



//        Company company = new Company();
//        company.setName("Pingviin OÜ");
//        company.setLogo("https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/South_Shetland-2016-Deception_Island%E2%80%93Chinstrap_penguin_%28Pygoscelis_antarctica%29_04.jpg/600px-South_Shetland-2016-Deception_Island%E2%80%93Chinstrap_penguin_%28Pygoscelis_antarctica%29_04.jpg");
//
//        Company company2 = new Company();
//        company2.setName("AS Arco Vara");
//        company2.setLogo("https://www.nasdaqbaltic.com/market/logo.php?issuer=ARC");
//
//        List<Company> companies = new ArrayList<>();
//        companies.add(company);
//        companies.add(company2);
//
//        return companies;
//    }

}
