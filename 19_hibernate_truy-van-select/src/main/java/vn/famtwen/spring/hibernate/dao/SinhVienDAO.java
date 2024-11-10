package vn.famtwen.spring.hibernate.dao;

import vn.famtwen.spring.hibernate.entity.SinhVien;

public interface SinhVienDAO {
    public void save(SinhVien sinhVien);

    public SinhVien getById(int id);
}
