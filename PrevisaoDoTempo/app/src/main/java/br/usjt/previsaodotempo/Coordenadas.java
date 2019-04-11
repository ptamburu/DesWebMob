package br.usjt.previsaodotempo;

public class Coordenadas {

        private double Lat;
        private double Long;

        public Coordenadas(){

        }

        public Coordenadas(double Lat, double Long){
            this.Lat = Lat;
            this.Long = Long;
        }

        public double getLong(){
            return Long;
        }
        public double getLat(){
            return Lat;
        }

        public void setLat(double Lat){
            this.Lat = Lat;
        }
        public void setLong(double Long){
            this.Long = Long;
        }

        public String toString(){
            return "Lat:" + Lat + " Long:" + Long;
        }
    }

}
