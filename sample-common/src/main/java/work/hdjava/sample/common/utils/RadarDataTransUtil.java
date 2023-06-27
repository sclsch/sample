package work.hdjava.sample.common.utils;

public class RadarDataTransUtil {

    public String carTypeTrans(String carHexadecimal){
            switch (carHexadecimal){
                case "00":
                    return "not used";
                case "01":
                    return "motor";
                case "02":
                    return "non-motor";
                case "03":
                    return "pedestrian";
                case "04":
                    return "reserved";
                case "05":
                    return "car";
                case "06":
                    return "bus/truck";
                case "07":
                    return "bicycle";
                case "08":
                    return "animal";
                case "09":
                    return "school bus";
                case "0A":
                    return "police vehicle";
                case "0B":
                    return "ambulance";
                case "0C":
                    return "Road rescue vehicle";
                case "0D":
                    return "oad rescue vehicle";
                default:
                    return null;
            }
    }

}
