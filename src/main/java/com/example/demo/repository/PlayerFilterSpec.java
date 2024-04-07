package com.example.demo.repository;

import com.example.demo.api.request.PlayerFilter;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

public class PlayerFilterSpec implements Specification<Player> {
    private final PlayerFilter playerFilter;

    public PlayerFilterSpec(PlayerFilter playerFilter) {
        this.playerFilter = playerFilter;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        ArrayList<Predicate> predicates = new ArrayList<>();
        if (playerFilter.getName() != null){
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + playerFilter.getName() + "%"));
        }
        if (playerFilter.getTitle() != null){
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + playerFilter.getTitle() + "%"));
        }
        if (playerFilter.getRace() != null){
            Join<Player, Race> join = root.join("race", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(join.get("name"), playerFilter.getRace().name()));
        }
        if (playerFilter.getProfession() != null){
            Join<Player, Profession> join = root.join("profession", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(join.get("name"), playerFilter.getProfession().name()));
        }

        if (playerFilter.getMaxLevel() != null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("level"), playerFilter.getMaxLevel()));
        }
        if (playerFilter.getMinLevel() != null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("level"), playerFilter.getMinLevel()));
        }
        if (playerFilter.getMaxExperience() != null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("experience"), playerFilter.getMaxExperience()));
        }
        if (playerFilter.getMinExperience() != null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), playerFilter.getMinExperience()));
        }
        if (playerFilter.getBanned() != null){
            predicates.add(criteriaBuilder.equal(root.get("banned"), playerFilter.getBanned()));
        }
        if (playerFilter.getBefore() != null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), playerFilter.getBefore()));
        }
        if (playerFilter.getAfter() != null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), playerFilter.getAfter()));
        }
        Predicate[] predicatesArray = predicates.toArray(predicates.toArray(new Predicate[0]));
        return criteriaBuilder.and(predicatesArray);
    }
}
