package nhatphong.ps11601.ps11601_phongdtn_asm.Model;

public class LoaiThu {
    private int maGD;
    private String tieuDe;
    private String ngay;
    private float tien;
    private String moTa;
    private int maLoai;

    public LoaiThu() {
    }

    public LoaiThu(int maGD, String tieuDe, String ngay, float tien, String moTa, int maLoai) {
        this.maGD = maGD;
        this.tieuDe = tieuDe;
        this.ngay = ngay;
        this.tien = tien;
        this.moTa = moTa;
        this.maLoai = maLoai;
    }

    public int getMaGD() {
        return maGD;
    }

    public void setMaGD(int maGD) {
        this.maGD = maGD;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public float getTien() {
        return tien;
    }

    public void setTien(float tien) {
        this.tien = tien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
