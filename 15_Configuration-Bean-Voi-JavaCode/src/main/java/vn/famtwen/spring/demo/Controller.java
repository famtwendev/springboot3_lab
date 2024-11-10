package vn.famtwen.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private Calculator myCalculator;

    @Autowired
    public Controller(@Qualifier("getCalculator") Calculator myCalculator)
    {
        this.myCalculator = myCalculator;
    }

    @GetMapping("/canBacHai")
    public String tinhCanBacHai(@RequestParam("value") double x)
    {
        return "SQRT("+x+") = "+myCalculator.canBacHai(x);
    }

    @GetMapping("/binhPhuong")
    public String binhPhuong(@RequestParam("value") double x)
    {
        return "("+x+"^2) = "+myCalculator.binhPhuong(x);
    }

    @GetMapping("/soSanh")
    public String soSanh(@RequestParam("value") double x)
    {
        double binhPhuongResult = (double)myCalculator.binhPhuong(x);
        double canBacHaiResult = (double)myCalculator.canBacHai(x);
        double maxResult = myCalculator.soSanh(binhPhuongResult, canBacHaiResult);

        if (maxResult == binhPhuongResult) {
            return "("+x+"^2)="+ binhPhuongResult +" >= " + "SQRT("+x+")="+ canBacHaiResult;
        } else if (maxResult == canBacHaiResult) {
            return "SQRT("+x+")="+ canBacHaiResult +" >=" + "("+x+"^2)="+ binhPhuongResult;
        } else {
            return "No Define";
        }
    }
}
