public class SinhVien {
    private String Ten;
    private int Tuoi;
    private String gpa;

    public SinhVien(String ten, int tuoi, String gpa) {
        this.Ten = ten;
        this.Tuoi = tuoi;
        this.gpa = gpa;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "Ten='" + Ten + '\'' +
                ", Tuoi=" + Tuoi +
                ", gpa=" + gpa +
                '}';
    }
}
