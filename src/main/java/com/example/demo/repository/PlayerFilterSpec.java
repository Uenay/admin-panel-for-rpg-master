package com.example.demo.repository;

import com.example.demo.api.request.PlayerFilter;
import com.example.demo.entity.Player;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
            predicates.add(criteriaBuilder.like(root.get("name"), playerFilter.getName()));
        }
        if (playerFilter.getTitle() != null){
            predicates.add(criteriaBuilder.like(root.get("title"), playerFilter.getTitle()));
        }
        if (playerFilter.getRace() != null){
            predicates.add(criteriaBuilder.equal(root.get("race"), playerFilter.getRace()));
        }
        if (playerFilter.getProfession() != null){
            predicates.add(criteriaBuilder.equal(root.get("profession"), playerFilter.getProfession()));
        }
        if (playerFilter.getMaxLevel() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("level"), playerFilter.getMaxLevel()));
        }
        if (playerFilter.getMinLevel() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("level"), playerFilter.getMinLevel()));
        }
        if (playerFilter.getMaxExperience() != null){
            predicates.add(criteriaBuilder.lessThan(root.get("experience"), playerFilter.getMaxExperience()));
        }
        if (playerFilter.getMinExperience() != null){
            predicates.add(criteriaBuilder.greaterThan(root.get("experience"), playerFilter.getMinExperience()));
        }
        if (playerFilter.getBanned() != null){
            predicates.add(criteriaBuilder.equal(root.get("banned"), playerFilter.getBanned()));
        }
        Predicate[] predicatesArray = predicates.toArray(predicates.toArray(new Predicate[0]));
        return criteriaBuilder.and(predicatesArray);
    }
}
