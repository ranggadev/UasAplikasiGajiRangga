package com.rangga.aplikasigaji.model;

/**
 * Class Supervisor - menerapkan konsep Inheritance dan Polymorphism
 */
public class Supervisor extends Pegawai {
    
    /**
     * @param nama
     */
    public Supervisor(String nama) {
        super(nama, "SPV");
        
        // Set komponen gaji sesuai ketentuan
        this.gajiPokok = 10000000;
        this.transport = 1000000;
        this.lemburPerJam = 10000;
        this.tunjanganIstri = 300000;
        this.tunjanganSatuAnak = 100000;
        this.tunjanganLebihDariSatuAnak = 200000;
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
