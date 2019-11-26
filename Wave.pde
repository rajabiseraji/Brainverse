class Wave implements Comparable<Wave> {
        int waveValue = 0;
        String waveName = "";
        int waveIndex = 0;
        float waveTransparency = 0;

        Wave(int waveValue ,String waveName , int waveIndex) {
            this.waveValue = waveValue;
            this.waveName = waveName;
            this.waveIndex = waveIndex;
        }

        void updateWave(int newWaveValue) {
            this.waveValue = newWaveValue;
        }

        void setTransparency(float newT) {
            this.waveTransparency = newT;
        }

        public int compareTo(Wave anotherWave) {
            return anotherWave.waveValue - this.waveValue;
        }
    }
