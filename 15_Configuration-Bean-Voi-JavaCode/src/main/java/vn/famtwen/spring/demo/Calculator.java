package vn.famtwen.spring.demo;


// Day la mot class cua don vi khac, khong the viet code o day
public class Calculator {

    public double canBacHai(double value)
    {
        return Math.sqrt(value);
    }

    public double binhPhuong(double value)
    {
        return Math.pow(value,2);
    }

    public double soSanh(double value1, double value2)
    {
        return Math.max(value1,value2);
    }
}
