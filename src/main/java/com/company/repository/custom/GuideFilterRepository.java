package com.company.repository.custom;

import com.company.dto.GuideFilterDTO;
import com.company.mapper.GuideFilterMapper;
import com.company.mapper.GuideRateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GuideFilterRepository {

    private final EntityManager entityManager;


    public List<GuideFilterMapper> filter(GuideFilterDTO dto) {

        StringBuilder sql = new StringBuilder(
//        String sql =
                "select new com.company.mapper.GuideFilterMapper (g.id as guideId, " +
                        "       p.id        as profileId," +
                        "       p.phoneNumber  as phone," +
                        "       g.biography     as bio," +
                        "       g.isHiring     as hiring," +
                        "       pr.id           as priceId," +
                        "       pr.cost         as price_cost," +
                        "       pr.currency     as price_currency," +
                        "       pr.type         as price_type," +
                        "       g.languageList as language," +
                        "       g.locationList as locations," +
                        "       g.rate          as rate," +
                        "       g.createdDate  as createDate)  " +
                        "from GuideEntity as g " +
                        "         inner join  g.profile as p" +
                        "         inner join  g.price as pr " +
                        "where g.isHiring = false ");
//        Query query = entityManager.createQuery(sql, GuideFilterMapper.class);

        Map<String, Object> params = new HashMap<>();

        if (Optional.ofNullable(dto.getMaxPrice()).isPresent() &&
                Optional.ofNullable(dto.getMinPrice()).isPresent()) {
            sql.append("and pr.cost between :minCost and :maxCost ");
            params.put("minCost", dto.getMinPrice());
            params.put("maxCost", dto.getMaxPrice());
        } else if (Optional.ofNullable(dto.getMaxPrice()).isPresent()) {
            sql.append("and pr.cost <= :maxCost ");
            params.put("maxCost", dto.getMaxPrice());
        } else if (Optional.ofNullable(dto.getMinPrice()).isPresent()) {
            sql.append("and pr.cost >= :minCost ");
            params.put("minCost", dto.getMinPrice());
        }

        if (Optional.ofNullable(dto.getMaxRating()).isPresent() &&
                Optional.ofNullable(dto.getMinRating()).isPresent()) {
            sql.append("and g.rate between  :minRating and :maxRating ");
            params.put("maxRating", dto.getMaxRating());
            params.put("minRating", dto.getMinRating());
        } else if (Optional.ofNullable(dto.getMaxRating()).isPresent()) {
            sql.append("and :maxRating <= g.rate ");
            params.put("maxRating", dto.getMaxRating());
        } else if (Optional.ofNullable(dto.getMinRating()).isPresent()) {
            sql.append("and :minRating >= g.rate ");
            params.put("minRating", dto.getMinRating());
        }

        if (Optional.ofNullable(dto.getGender()).isPresent()) {
            sql.append("and p.gender = :gender ");
        }

//        Query nativeQuery = entityManager.createNativeQuery(sql.toString(), GuideFilterMapper.class);
        Query query = entityManager.createQuery(sql.toString(), GuideFilterMapper.class);

        params.forEach(query::setParameter);

        return query.getResultList();
    }
}
