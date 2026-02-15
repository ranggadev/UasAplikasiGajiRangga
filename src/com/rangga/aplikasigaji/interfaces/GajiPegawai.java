package com.rangga.aplikasigaji.interfaces;

/**
 * Interface untuk kontrak perhitungan gaji pegawai
 */
public interface GajiPegawai {
    
    /**
     * @return
     */
    double hitungGajiPokok();
    
    /**
     * @return
     */
    double hitungTransport();
    
    /**
     * @param jamKeluar jam pulang kerja (1-24)
     * @return
     */
    double hitungLembur(int jamKeluar);
    
    /**
     * @param statusMenikah true jika menikah
     * @param jumlahAnak jumlah anak
     * @return
     */
    double hitungTunjanganKeluarga(boolean statusMenikah, int jumlahAnak);
    
    /**
     * @param jamMasuk jam masuk kerja (1-24)
     * @param jamKeluar jam keluar kerja (1-24)
     * @return
     */
    double hitungPotongan(int jamMasuk, int jamKeluar);
    
    /**
     * @param jamMasuk
     * @param jamKeluar
     * @param statusMenikah
     * @param jumlahAnak
     * @return
     */
    double hitungTotalGaji(int jamMasuk, int jamKeluar, boolean statusMenikah, int jumlahAnak);
}
