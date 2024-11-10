package vn.famtwen.spring.__RestController_Postman.entity;

public class SinhVien {
    private int id;
    private String hoVaTen;
    private int Tuoi;
    private String nganhHoc;
    private double diemTB;

    public SinhVien() {
    }

    public SinhVien(int id, String hoVaTen, int tuoi, String nganhHoc, double diemTB) {
        this.id = id;
        this.hoVaTen = hoVaTen;
        Tuoi = tuoi;
        this.nganhHoc = nganhHoc;
        this.diemTB = diemTB;
    }

    //Jackson

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }

    public String getNganhHoc() {
        return nganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        this.nganhHoc = nganhHoc;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }


}
