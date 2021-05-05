package nhatphong.ps11601.ps11601_phongdtn_asm.Model;

public class KhoanChi {
    private int maLoai;
    private String tenLoai;
    private String TrangThai;

    public KhoanChi() {
    }

    public KhoanChi(int maLoai, String tenLoai, String trangThai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.TrangThai = trangThai;
    }


    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
