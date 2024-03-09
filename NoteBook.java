package Task1_DZ;

import java.util.Objects;

public class NoteBook {
    private String company;
    private int freqCPU;
    private int sizeMemoryGb;
    private int sizeHDDGb;
    private double sizeMonitor;

    public NoteBook(String company, int freqCPU, int sizeMemoryGb, int sizeHDDGb, double sizeMonitor) {
        this.company = company;
        this.freqCPU = freqCPU;
        this.sizeMemoryGb = sizeMemoryGb;
        this.sizeHDDGb = sizeHDDGb;
        this.sizeMonitor = sizeMonitor;
    }

    public String getCompany() { return company; }

    public int getFreqCPU() { return freqCPU; }

    public int getSizeMemory() { return sizeMemoryGb; }

    public int getSizeHDD() { return sizeHDDGb; }

    public double getSizeMonitor() { return sizeMonitor; }

    public void setCompany(String company) { this.company = company; }

    public void setFreqCPU(int freqCPU) { this.freqCPU = freqCPU; }

    public void setSizeMemory(int sizeMemoryGb) { this.sizeMemoryGb = sizeMemoryGb; }

    public void setSizeHDD(int sizeHDDGb) { this.sizeHDDGb = sizeHDDGb; }

    public void setSizeMonitor(int sizeMonitor) { this.sizeMonitor = sizeHDDGb; }

    @Override
    public int hashCode() {
        return Objects.hash(company, freqCPU, sizeMemoryGb, sizeHDDGb, sizeMonitor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        NoteBook noteBook = (NoteBook) obj;

        return company.equals(noteBook.company) &&
                freqCPU == noteBook.freqCPU &&
                sizeMemoryGb == noteBook.sizeMemoryGb &&
                sizeHDDGb == noteBook.sizeHDDGb &&
                sizeMonitor == noteBook.sizeMonitor;
    }

    @Override
    public String toString() {
        return String.format("Производитель: %s | " +
                "Частота процессора: %d Мгц | " +
                "Объем ОЗУ(RAM): %d Гб | " +
                "Размер HDD: %d Гб | " +
                "Размер монитора: %.1f дюймов |",
                company, freqCPU, sizeMemoryGb, sizeHDDGb, sizeMonitor);
    }
}
