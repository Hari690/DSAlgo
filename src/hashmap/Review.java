package hashmap;

import java.util.*;
import java.util.stream.Collectors;

class Interaction {
    // A single interaction with a business
    String interactionType;
    int businessId;

    public Interaction(String interactionType, int businessId) {
        this.interactionType = interactionType;
        this.businessId = businessId;
    }

    public String getInteractionType() { return this.interactionType; }
    public int getBusinessId() { return this.businessId; }
}


class BusinessInteraction {

    public static final Map<String, Float> WEIGHTS = new HashMap<String, Float>() {{
        put("review draft", 2.0f);
        put("photo", 1.0f);
        put("check-in", 0.8f);
        put("bookmark", 0.25f);
    }};

    public static List<Integer> interactionsToSortedBusinesses(List<Interaction> interactions) {
        Map<Integer,Float> businessMap = new HashMap<>();

        for(Interaction interaction : interactions) {
            Float value = businessMap.get(interaction.businessId);
            if(value==null) {
                value = WEIGHTS.get(interaction.interactionType);
            } else {
                value+=WEIGHTS.get(interaction.interactionType);
            }
            businessMap.put(interaction.businessId, value);
        }
        List<Map.Entry<Integer,Float>> sortedList = new ArrayList<>();
        sortedList.addAll(businessMap.entrySet());
        Collections.sort(sortedList, (a,b)-> b.getValue().compareTo(a.getValue()));

        return sortedList.stream().map(s-> s.getKey()).collect(Collectors.toList());
    }
}
