package com.rangga.aplikasigaji.model;

/**
 * Class HRD - menerapkan konsep Inheritance dan Polymorphism
 */
public class HRD extends Pegawai {
    
    /**
     * @param nama
     */
    public HRD(String nama) {
        super(nama, "HRD");
        
        this.gajiPokok = 5000000;
        this.transport = 750000;
        this.lemburPerJam = 7500;
        this.tunjanganIstri = 250000;
        this.tunjanganSatuAnak = 90000;
        this.tunjanganLebihDariSatuAnak = 190000;
    }
    
    /**
     * Implementasi polymorphism - override method dari interface
     */
    @Override
    public double hitungGajiPokok() {
        return gajiPokok;
    }
    
    @Override
    public double hitungTransport() {
        return transport;
    }
    
    @Override
    public double hitungLembur(int jamKeluar) {
        int jamLembur = hitungJamLembur(jamKeluar);
        return jamLembur * lemburPerJam;
    }
    
    @Override
    public double hitungTunjanganKeluarga(boolean statusMenikah, int jumlahAnak) {
        double tunjangan = 0;
        
        // Tunjangan istri
        if (statusMenikah) {
            tunjangan += tunjanganIstri;
        }
        
        // Tunjangan anak
        if (jumlahAnak == 1) {
            tunjangan += tunjanganSatuAnak;
        } else if (jumlahAnak > 1) {
            tunjangan += tunjanganLebihDariSatuAnak;
        }
        
        return tunjangan;
    }
}
