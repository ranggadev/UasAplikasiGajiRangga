package com.rangga.aplikasigaji.model;

/**
 * Class Teknisi - menerapkan konsep Inheritance dan Polymorphism
 */
public class Teknisi extends Pegawai {
    
    /**
     * @param nama
     */
    public Teknisi(String nama) {
        super(nama, "TKN");
        
        // Set komponen gaji sesuai ketentuan
        this.gajiPokok = 3000000;
        this.transport = 500000;
        this.lemburPerJam = 5000;
        this.tunjanganIstri = 200000;
        this.tunjanganSatuAnak = 75000;
        this.tunjanganLebihDariSatuAnak = 150000;
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
