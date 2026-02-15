package com.rangga.aplikasigaji.model;

/**
 * Class Karyawan - menerapkan konsep Inheritance dan Polymorphism
 */
public class Karyawan extends Pegawai {
    
    /**
     * @param nama
     */
    public Karyawan(String nama) {
        super(nama, "KRY");
        
        this.gajiPokok = 1500000;
        this.transport = 250000;
        this.lemburPerJam = 5000;
        this.tunjanganIstri = 200000;
        this.tunjanganSatuAnak = 60000;
        this.tunjanganLebihDariSatuAnak = 130000;
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
