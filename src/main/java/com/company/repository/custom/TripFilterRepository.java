package com.company.repository.custom;

import com.company.dto.request.TripFilterDTO;
import com.company.entity.TripEntity;
import com.company.mapper.TripFilterInfoMapper;
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
public class TripFilterRepository {

    private final EntityManager entityManager;


    public List<TripFilterInfoMapper> filter(TripFilterDTO dto) {

        StringBuilder sql = new StringBuilder(
                "select new com.company.mapper.TripFilterInfoMapper( t.id as t_id, t.name as t_name, t.description as t_desc, " +
                        " t.minPeople as t_min, t.maxPeople as t_max, t.phoneNumber as t_phone, t.rate as t_rate, " +
                        " p.id as p_id, p.cost as p_cost, p.currency as p_currency, p.type as p_type, " +
                        " g.id as g_id, g.phoneNumber as g_phone, g.biography as g_bio, g.isHiring as g_ishiring, g.rate as g_rate, " +
                        " gp.id as gp_id, gp.name as gp_name, gp.surname as gp_surname, gp.photo as gp_photo) " +
                        " from TripEntity t " +
                        " inner join t.price p " +
                        " inner join t.guideProfile g " +
                        " inner join g.profile gp " +
                        " where g.isHiring = false ");

        Map<String, Object> params = new HashMap<>();

        if (Optional.ofNullable(dto.getMaxCost()).isPresent() &&
                Optional.ofNullable(dto.getMinCost()).isPresent()) {
            sql.append("and p.cost between :minCost and :maxCost ");
            params.put("minCost", dto.getMinCost());
            params.put("maxCost", dto.getMaxCost());
        } else if (Optional.ofNullable(dto.getMaxCost()).isPresent()) {
            sql.append("and p.cost <= :maxCost ");
            params.put("maxCost", dto.getMaxCost());
        } else if (Optional.ofNullable(dto.getMinCost()).isPresent()) {
            sql.append("and p.cost >= :minCost ");
            params.put("minCost", dto.getMinCost());
        }

        if (Optional.ofNullable(dto.getMaxPeople()).isPresent() &&
                Optional.ofNullable(dto.getMinPeople()).isPresent()) {
            sql.append("and :minPeople between t.minPeople and t.maxPeople " +
                    "and :maxPeople between t.minPeople and t.maxPeople ");
            params.put("maxPeople", dto.getMaxPeople());
            params.put("minPeople", dto.getMinPeople());
        } else if (Optional.ofNullable(dto.getMaxPeople()).isPresent()) {
            sql.append("and :maxPeople <= t.maxPeople ");
            params.put("maxPeople", dto.getMaxPeople());
        } else if (Optional.ofNullable(dto.getMinPeople()).isPresent()) {
            sql.append("and :minPeople >= t.minPeople ");
            params.put("minPeople", dto.getMinPeople());
        }

        if (Optional.ofNullable(dto.getMaxRate()).isPresent() &&
                Optional.ofNullable(dto.getMinRate()).isPresent()) {
            sql.append("and t.rate between :minRate and :maxRate ");
            params.put("maxRate", dto.getMaxRate());
            params.put("minRate", dto.getMinRate());
        } else if (Optional.ofNullable(dto.getMaxRate()).isPresent()) {
            sql.append("and t.rate <= :maxRate ");
            params.put("maxRate", dto.getMaxRate());
        } else if (Optional.ofNullable(dto.getMinRate()).isPresent()) {
            sql.append("and t.rate >= :minRate ");
            params.put("minRate", dto.getMinRate());
        }

        Query query = entityManager.createQuery(sql.toString(), TripFilterInfoMapper.class);

        params.forEach(query::setParameter);

        return query.getResultList();
    }
}
