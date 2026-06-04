//AudienceQueryService.java

package project.query;

import java.util.HashSet;
import java.util.Set;

import project.index.AudienceIndex;
import project.model.DeviceType;

public class AudienceQueryService {
    private final AudienceIndex index;

    public AudienceQueryService(AudienceIndex index){
        this.index = index;
    }

    public Set<Long> execute(String query){
        String[] clauses = query.split("AND");
        Set<Long> candidate = null;
        Set<Long> result = null;

        for(String clause : clauses){
            clause = clause.trim();   // IMPORTANT
            candidate = null;

            if(clause.equalsIgnoreCase("premium")){
                candidate = index.getPremiumUsers();
            }
            else if(clause.startsWith("city=")){
                String city = clause.substring(5).trim();
                candidate = index.getUsersByCity(city);
            }
            else if(clause.startsWith("device=")){
                String device = clause.substring(7).trim();

                DeviceType type =
                        DeviceType.valueOf(device.toUpperCase());

                candidate = index.getUsersByDeviceType(type);
            }

            if(candidate == null){
                continue;
            }

            if(result == null){
                result = new HashSet<>(candidate);
            }
            else{
                result.retainAll(candidate);
            }
        }
        return (result == null) ? new HashSet<>() : result;
    }

    public String buildQuery(boolean isPremium, String city, DeviceType type){
        StringBuilder sb = new StringBuilder();
        if(isPremium){
            sb.append("premium");
        }
        if(city!=null){
            if(sb.length()>0){
                sb.append(" AND ");
            }
            sb.append("city=");
            sb.append(city);
        }
        if(type != null){
            if(sb.length()>0){
                sb.append(" AND ");
            }
            sb.append("device=");
            sb.append(type);
        }
        return sb.toString();
    }
}
