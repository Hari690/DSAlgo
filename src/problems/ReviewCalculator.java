package problems;


// <-- EXPAND to see the data classes
import java.util.*;

class PositiveReview {
    Integer userId;
    Integer businessId;

    public PositiveReview(Integer userId, Integer businessId) {
        this.userId = userId;
        this.businessId = businessId;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public Integer getBusinessId() {
        return this.businessId;
    }
}

class ReviewCalculator {

    /*
    Sample Input
        {
            "businessOfInterestId": 10,
            "positiveReviews": [
                PositiveReview(
                    "userId": 1,
                    "businessId": 10
                ),
                PositiveReview(
                    "userId": 2,
                    "businessId": 10
                ),
                PositiveReview(
                    "userId": 1,
                    "businessId": 11
                ),
                PositiveReview(
                    "userId": 2,
                    "businessId": 11
                ),
                PositiveReview(
                    "userId": 1,
                    "businessId": 12
                ),
                PositiveReview(
                    "userId": 2,
                    "businessId": 12
                ),
                PositiveReview(
                    "userId": 3,
                    "businessId": 12
                )
            ]
        }
    Sample Output
        11
    Business Similarity Score against business 10:
        11: 2 / (2 + 2 - 2) = 1.0
        12: 2 / (2 + 3 - 2) = 0.667
    */
    public static Integer findMostSimilarBusiness(
        Integer businessOfInterestId,
        List<PositiveReview> positiveReviews
    ) {
        // users who reviewed dept of interest
        Set<Integer> users = new HashSet<>();
        for (PositiveReview positiveReview : positiveReviews) {
            if (positiveReview.getBusinessId() == businessOfInterestId) {
                users.add(positiveReview.getUserId());
            }
        }

        Map<Integer, Integer> deptBothMap = new HashMap<>();
        Map<Integer, Integer> deptEachMap = new HashMap<>();
        for (PositiveReview positiveReview : positiveReviews) {
            if(positiveReview.getBusinessId()!=businessOfInterestId) {
                if (users.contains(positiveReview.getUserId())) {
                    deptBothMap.put(positiveReview.getBusinessId(), deptBothMap.getOrDefault(positiveReview.getBusinessId(), 0) + 1);
                } else {
                    deptEachMap.put(positiveReview.getBusinessId(), deptEachMap.getOrDefault(positiveReview.getBusinessId(), 0) + 1);
                }
            }
        }

        int deptId = 0;
        float highest = 0;
        for (Map.Entry<Integer, Integer> entry : deptBothMap.entrySet()) {
            float current = (float) entry.getValue() / (entry.getValue() + deptEachMap.getOrDefault(entry.getKey(), 0));
            System.out.println(entry.getKey()+ " "+current);
            if (current > highest) {
                deptId = entry.getKey();
                highest = current;
            }
        }

        return deptId;
    }

    public static void main(String[] args) {

        List<PositiveReview> list = new ArrayList<>();

        list.add(new PositiveReview(1,10));
        list.add(new PositiveReview(2,10));
        list.add(new PositiveReview(1,11));
        list.add(new PositiveReview(2,11));
        list.add(new PositiveReview(1,12));
        list.add(new PositiveReview(2,12));
        list.add(new PositiveReview(3,12));

        System.out.println(ReviewCalculator.findMostSimilarBusiness(10, list));
    }
}