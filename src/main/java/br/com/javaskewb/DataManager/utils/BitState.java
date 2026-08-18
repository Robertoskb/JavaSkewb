package br.com.javaskewb.DataManager.utils;

public class BitState {
    private final int cornersRankings; // 24 bits (3! < 2^3)
    private final int cornersIdRank; // 16 bits (8! < 2^16)
    private final int centersRank; // 10 bits (6! < 2^10)

    private long id; // 50 bits

    public BitState(long id){
        this.id = id;

        cornersRankings = getFirstBits(id, 24);
        id >>= 24;

        cornersIdRank = getFirstBits(id, 16);
        id >>= 16;

        centersRank = getFirstBits(id, 10);
    }

    public BitState(int centersRank, int cornersIdRank, int[] cornersRankings){
        int baseCornersRankings = 0;
        for (int i = 0; i < 8; i++) {
            int rank = cornersRankings[i];
            baseCornersRankings |= ( rank << (3*i));
        }
        this.cornersRankings = baseCornersRankings;
        this.cornersIdRank = cornersIdRank;
        this.centersRank = centersRank;

        id = 0;
        id |= this.cornersRankings;
        id |= ((long) this.cornersIdRank << 24);
        id |= ((long) this.centersRank << 40);
    }

    public int getCornerRank(int id){
        return (cornersRankings >> (id * 3)) & 0b111;
    }

    private static int getFirstBits(long number, int size){
        return (int) (number & getMask(size));
    }

    private static long getMask(int size) {
        return (1L << size) - 1;
    }

    public int getCornersRankings() {
        return cornersRankings;
    }

    public int getCornersIdRank() {
        return cornersIdRank;
    }

    public int getCentersRank() {
        return centersRank;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s %s", id, cornersRankings, cornersIdRank, centersRank);
    }
}
